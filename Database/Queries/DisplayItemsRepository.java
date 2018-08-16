package com.pharmavet.imperial.pharmavetdist.Database.Queries;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.pharmavet.imperial.pharmavetdist.Database.ItemsDatabase;
import com.pharmavet.imperial.pharmavetdist.Database.Models.DisplayItems;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class DisplayItemsRepository {

    private DisplayItemsDao displayItemsDao;
    private Single<List<DisplayItems>> items;

    @Inject
    public DisplayItemsRepository(Application application) {
        ItemsDatabase db = ItemsDatabase.getInstance(application);
        displayItemsDao = db.displayItemsDao();
        items = displayItemsDao.getAll();
        Log.d("Bla", items.toString());
    }

    public Single<List<DisplayItems>> getAll() {
        return items;
    }

    public void insert(DisplayItems... displayItems) {
        new insertAsyncAll(displayItemsDao).execute(displayItems);

    }

    private static class insertAsyncAll extends AsyncTask<DisplayItems, Void, Void> {
        private DisplayItemsDao dao;

        insertAsyncAll(DisplayItemsDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(final DisplayItems... params) {
            dao.insert(params[0]);
            return null;
        }
    }
}
