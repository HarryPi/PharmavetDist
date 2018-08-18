package com.pharmavet.imperial.pharmavetdist.Database.Queries;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.pharmavet.imperial.pharmavetdist.Database.Models.DisplayItems;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface DisplayItemsDao {
    @Query("SELECT * FROM displayItems_table")
    Single<List<DisplayItems>> getAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DisplayItems displayItems);
    @Delete
    void delete(DisplayItems displayItems);
    @Query("DELETE FROM DISPLAYITEMS_TABLE")
    void deleteAll();
    @Query("SELECT * FROM displayItems_table WHERE companyName=:companyName")
    Single<List<DisplayItems>> findAllProductsForCompany(final String companyName);
}
