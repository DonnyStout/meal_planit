package edu.cnm.deepdive.mealplanit.models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Diet {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "diet_id")
    private long dietId;


    @ColumnInfo(name = "diet_type")
    private String dietType;


    public long getDietId() {
        return dietId;
    }

    public void setDietId(long dietId) {
        this.dietId = dietId;
    }

    public String getDietType() {
        return dietType;
    }

    public void setDietType(String dietType) {
        this.dietType = dietType;
    }

    @Override
    public String toString() {
        return dietId + " " + dietType;
    }
}
