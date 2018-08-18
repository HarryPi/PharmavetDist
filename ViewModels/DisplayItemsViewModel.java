package com.pharmavet.imperial.pharmavetdist.ViewModels;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.pharmavet.imperial.pharmavetdist.Database.ItemsDatabase;
import com.pharmavet.imperial.pharmavetdist.Database.Models.DisplayItems;
import com.pharmavet.imperial.pharmavetdist.Database.Queries.DisplayItemsDao;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class DisplayItemsViewModel {

    private DisplayItemsDao displayItemsDao;
    private Single<List<DisplayItems>> items;

    @Inject
    public DisplayItemsViewModel(Application application) {
        ItemsDatabase db = ItemsDatabase.getInstance(application);
        displayItemsDao = db.displayItemsDao();
        items = displayItemsDao.getAll();
    }

    public Single<List<DisplayItems>> getAll() {
        return items;
    }

    public void insert(DisplayItems... displayItems) {
        new insertAsyncAll(displayItemsDao).execute(displayItems);

    }
    public Single<List<DisplayItems>> findAllProductsForCompany(String companyName) {
        return displayItemsDao.findAllProductsForCompany(companyName);
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
