package com.pharmavet.imperial.pharmavetdist.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.pharmavet.imperial.pharmavetdist.Database.Models.Company;
import com.pharmavet.imperial.pharmavetdist.Database.Models.DisplayItems;
import com.pharmavet.imperial.pharmavetdist.Database.Queries.CompanyDao;
import com.pharmavet.imperial.pharmavetdist.Database.Queries.DisplayItemsDao;
import com.pharmavet.imperial.pharmavetdist.Services.ImageRetriever;

@Database(entities = {DisplayItems.class, Company.class}, version = 3)
public abstract class ItemsDatabase extends RoomDatabase {

    private static volatile ItemsDatabase INSTANCE;

    public abstract DisplayItemsDao displayItemsDao();
    public abstract CompanyDao companyDao();

    public static ItemsDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (ItemsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ItemsDatabase.class, "Products.db")
                            .addCallback(new Callback() {
                                @Override
                                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                    super.onOpen(db);
                                    new PopulateDbAsync(INSTANCE).execute();
                                }
                            })
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final DisplayItemsDao displayItemsDao;
        private final CompanyDao companyDao;

        private PopulateDbAsync(ItemsDatabase db) {
            displayItemsDao = db.displayItemsDao();
            companyDao = db.companyDao();
        }

        @Override
        protected Void doInBackground(Void... params) {
            displayItemsDao.deleteAll();
            companyDao.deleteAllCompanies();;
            companyDao.insert(new Company("BioCare", ImageRetriever.createUrl("biocare_logo")));
            companyDao.insert(new Company("Vogel", ImageRetriever.createUrl("vogel_logo")));
            displayItemsDao.insert(new DisplayItems("acetylcarnitinealphalipoicacidcapules", null,
                    null, 0, 10, null, null,
                    Uri.parse("android.resource://com.pharmavet.imperial.pharmavetdist/mipmap/" + "acetylcarnitinealphalipoicacidcapules").toString(), "Acetyl Carnitine Alpha Lipoic Acid Capules", "BioCare"));
            return null;
        }
    }
}

