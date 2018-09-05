package com.pharmavet.imperial.pharmavetdist.ViewModels;

import android.app.Application;
import android.os.AsyncTask;

import com.pharmavet.imperial.pharmavetdist.Database.ItemsDatabase;
import com.pharmavet.imperial.pharmavetdist.Database.Models.DisplayItems;
import com.pharmavet.imperial.pharmavetdist.Database.Queries.DisplayItemsDao;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class DisplayItemsViewModel {

    private DisplayItemsDao displayItemsDao;
    // todo: Make this observable instead of single to avoid recreating activity when item is deleted or edited

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

    public void delete(DisplayItems... displayItems) {
        new deleteAsyncItem(displayItemsDao).execute(displayItems);
    }
    public void insert(DisplayItems... displayItems) {
        new insertAsyncAll(displayItemsDao).execute(displayItems);
    }
    public Single<DisplayItems> getSingle(String itemId, String companyId) {
        return displayItemsDao.getSingle(itemId, companyId);
    }
    public Single<List<DisplayItems>> findAllProductsForCompany(String companyName) {
        return displayItemsDao.findAllProductsForCompany(companyName);
    }
    public Single<List<DisplayItems>> findAllProductsForCompanyWithProductName(String companyName, String query) {
        return displayItemsDao.findAllProductsForCompanyThatContain(companyName, query);
    }

    public void changeItemWithId(DisplayItems editedItem) {
        new updateAllAsync(displayItemsDao).execute(editedItem);
    }

    private static class updateAllAsync extends AsyncTask<DisplayItems, Void, Void> {
        private DisplayItemsDao dao;
        updateAllAsync(DisplayItemsDao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(final DisplayItems... params) {
            dao.update(params[0]);
            return null;
        }
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
    private static class deleteAsyncItem extends AsyncTask<DisplayItems, Void, Void> {
        private DisplayItemsDao dao;

        deleteAsyncItem(DisplayItemsDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(final DisplayItems... params) {
            dao.delete(params[0]);
            return null;
        }
    }
}
