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

    @ColumnInfo(name = "allergies")
    private String allergies;


    public long getRestrictionId() {
        return restrictionId;
    }

    public void setRestrictionId(long restrictionId) {
        this.restrictionId = restrictionId;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    @Override
    public String toString() {
        return allergies;
    }
}
