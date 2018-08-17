package com.pharmavet.imperial.pharmavetdist.Database.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static android.arch.persistence.room.ForeignKey.CASCADE;


@Entity(tableName = "displayItems_table",
        foreignKeys = @ForeignKey(entity = Company.class,
                parentColumns = "name",
                childColumns = "companyName",
                onDelete = CASCADE
        ))
public class DisplayItems {

    @NonNull
    @PrimaryKey
    private String name;
    @NonNull
    @ColumnInfo
    private String productName;
    @ColumnInfo
    @Nullable
    private String description;
    @ColumnInfo
    @Nullable
    private String productType;
    @ColumnInfo
    private int size;
    @ColumnInfo
    private int price;
    @ColumnInfo
    @Nullable
    private String ingredientDescription;
    @ColumnInfo
    @Nullable
    private String additionalInfo;
    @ColumnInfo
    @NonNull
    private String imageUrl;
    @ColumnInfo
    @NonNull
    private String companyName;

    public DisplayItems(@NonNull String name, @Nullable String description,
                        @Nullable String productType, int size, int price,
                        @Nullable String ingredientDescription,
                        @Nullable String additionalInfo, @NonNull String imageUrl,
                        @NonNull String productName, @NonNull String companyName) {
        this.productName = productName;
        this.name = name;
        this.description = description;
        this.productType = productType;
        this.size = size;
        this.price = price;
        this.ingredientDescription = ingredientDescription;
        this.additionalInfo = additionalInfo;
        this.imageUrl = imageUrl;
        this.companyName = companyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getIngredientDescription() {
        return ingredientDescription;
    }

    public void setIngredientDescription(String ingredientDescription) {
        this.ingredientDescription = ingredientDescription;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NonNull
    public String getProductName() {
        return productName;
    }

    public void setProductName(@NonNull String productName) {
        this.productName = productName;
    }

    @NonNull
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(@NonNull String companyName) {
        this.companyName = companyName;
    }
}
