package com.pharmavet.imperial.pharmavetdist.Database.Queries;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.pharmavet.imperial.pharmavetdist.Database.Models.DisplayItems;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface DisplayItemsDao {
    @Query("SELECT * FROM displayItems_table WHERE name =:itemId AND companyName =:companyId")
    Single<DisplayItems> getSingle(String itemId, String companyId);
    @Query("SELECT * FROM displayItems_table")
    Single<List<DisplayItems>> getAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DisplayItems displayItems);
    @Delete
    void delete(DisplayItems displayItems);
    @Query("DELETE FROM DISPLAYITEMS_TABLE")
    void deleteAll();
    @Query("SELECT * FROM displayItems_table WHERE companyName=:companyName ORDER BY productName")
    Single<List<DisplayItems>> findAllProductsForCompany(final String companyName);
    @Query("SELECT * FROM displayItems_table WHERE companyName=:companyName AND productName LIKE :query OR ingredientDescription like :query  ORDER BY productName")
    Single<List<DisplayItems>> findAllProductsForCompanyThatContain(final String companyName,final String query );
    @Update
    void update(DisplayItems... editedItems);
}
