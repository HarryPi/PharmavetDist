package com.pharmavet.imperial.pharmavetdist.Database.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "company_table")
public class Company {
    @PrimaryKey
    @NonNull
    private String name;
    @ColumnInfo
    private String imageUrl;

    public Company(@NonNull String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
