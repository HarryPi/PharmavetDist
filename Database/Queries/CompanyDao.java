package com.pharmavet.imperial.pharmavetdist.Database.Queries;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.pharmavet.imperial.pharmavetdist.Database.Models.Company;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface CompanyDao {
    @Query("SELECT * FROM company_table")
    Single<List<Company>> getAllCompanies();
    @Query("DELETE FROM company_table")
    void deleteAllCompanies();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Company company);
}
