package edu.cnm.deepdive.mealplanit.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Restriction {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "restriction_id")
    private long restrictionId;

    @ColumnInfo(name = "allergy") // FIXME
    private String allergy;


    public long getRestrictionId() {
        return restrictionId;
    }

    public void setRestrictionId(long restrictionId) {
        this.restrictionId = restrictionId;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    @Override
    public String toString() {
        return allergy;
    }
}
