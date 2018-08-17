package com.pharmavet.imperial.pharmavetdist.ViewModels;

import android.app.Application;
import android.os.AsyncTask;

import com.pharmavet.imperial.pharmavetdist.Database.ItemsDatabase;
import com.pharmavet.imperial.pharmavetdist.Database.Models.Company;
import com.pharmavet.imperial.pharmavetdist.Database.Queries.CompanyDao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

public class CompanyViewModel {
    private CompanyDao companyDao;
    private Single<List<Company>> companies;

    @Inject
    public CompanyViewModel(Application application) {
        ItemsDatabase db = ItemsDatabase.getInstance(application);
        companyDao = db.companyDao();
        companies = companyDao.getAllCompanies();
    }

    public Single<List<Company>> getCompanies() {
        return companies;
    }

    public void insert(Company... companies) {
        new InsertAllAsync(companyDao).execute(companies);
    }

    private static class InsertAllAsync extends AsyncTask<Company, Void, Void> {
        private CompanyDao dao;

        InsertAllAsync(CompanyDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(final Company... params) {
            dao.insert(params[0]);
            return null;
        }
    }
}
