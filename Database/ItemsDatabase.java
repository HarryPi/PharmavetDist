package com.pharmavet.imperial.pharmavetdist.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.pharmavet.imperial.pharmavetdist.Database.Models.DisplayItems;
import com.pharmavet.imperial.pharmavetdist.Database.Queries.DisplayItemsDao;
import com.pharmavet.imperial.pharmavetdist.Services.ImageRetriever;

@Database(entities = {DisplayItems.class}, version = 1)
public abstract class ItemsDatabase extends RoomDatabase {

    private static volatile ItemsDatabase INSTANCE;

    public abstract DisplayItemsDao displayItemsDao();

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
        private final DisplayItemsDao dao;

        private PopulateDbAsync(ItemsDatabase db) {
            dao = db.displayItemsDao();
        }

        @Override
        protected Void doInBackground(Void... params) {
           /* dao.deleteAll();
            dao.insert(new DisplayItems("acetylcarnitinealphalipoicacidcapules", null,
                    null, 0, 10, null, null,
                    Uri.parse("android.resource://com.pharmavet.imperial.pharmavetdist/mipmap/" + "acetylcarnitinealphalipoicacidcapules").toString(), "Acetyl Carnitine Alpha Lipoic Acid Capules"));*/
            return null;
        }
    }
}

