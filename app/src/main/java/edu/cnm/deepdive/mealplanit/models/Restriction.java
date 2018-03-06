package edu.cnm.deepdive.mealplanit.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class Restriction {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "restriction_id")
    private long restrictionId;


    @ColumnInfo(name = "excluded_ingredients")
    private String excludedIngredients;


    public long getRestrictionId() {
        return restrictionId;
    }

    public void setRestrictionId(long restrictionId) {
        this.restrictionId = restrictionId;
    }

    public String getExcludedIngredients() {
        return excludedIngredients;
    }

    public void setExcludedIngredients(String excludedIngredients) {
        this.excludedIngredients = excludedIngredients;
    }

    @Override
    public String toString() {
        return excludedIngredients + " " + excludedIngredients;
    }
}
