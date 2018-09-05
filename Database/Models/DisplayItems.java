package com.pharmavet.imperial.pharmavetdist.Database.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
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
    private String size;
    @ColumnInfo
    private int price;
    @ColumnInfo
    @Nullable
    private String ingredientDescription;
    @ColumnInfo
    @Nullable
    private String additionalInfo;
    @ColumnInfo
    @Nullable
    private String recommendedIntake;
    @ColumnInfo
    @Nullable
    private String warnings;
    @ColumnInfo
    @NonNull
    private String imageUrl;
    @ColumnInfo
    @NonNull
    private String companyName;

    @Ignore
    public DisplayItems() {

    }

    public DisplayItems(@NonNull String name, @NonNull String productName, String description, String productType, String size, int price, String ingredientDescription, String additionalInfo, String recommendedIntake, String warnings, @NonNull String imageUrl, @NonNull String companyName) {
        this.name = name;
        this.productName = productName;
        this.description = description;
        this.productType = productType;
        this.size = size;
        this.price = price;
        this.ingredientDescription = ingredientDescription;
        this.additionalInfo = additionalInfo;
        this.recommendedIntake = recommendedIntake;
        this.warnings = warnings;
        this.imageUrl = imageUrl;
        this.companyName = companyName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Nullable
    public String getRecommendedIntake() {
        return recommendedIntake;
    }

    public void setRecommendedIntake(@Nullable String recommendedIntake) {
        this.recommendedIntake = recommendedIntake;
    }

    @Nullable
    public String getWarnings() {
        return warnings;
    }

    public void setWarnings(@Nullable String warnings) {
        this.warnings = warnings;
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
