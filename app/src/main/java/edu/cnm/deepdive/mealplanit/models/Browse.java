package edu.cnm.deepdive.mealplanit.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
//        (foreignKeys = @ForeignKey(entity = Person.class,
//        parentColumns = "food_restrictions_id",
//        childColumns = "",
//        onDelete = CASCADE))
public class Browse {

    @PrimaryKey(autoGenerate = true)
    private long browseId;

//    @ColumnInfo(name = "date")
//    private Date date;

    @ColumnInfo(name = "meal_name")
    private String mealName;

    @ColumnInfo(name = "type")
    private String mealType;


    public long getBrowseId() {
        return browseId;
    }

    public void setBrowseId(long browseId) {
        this.browseId = browseId;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    @Override
    public String toString() {
        return " " + mealName + " " + mealType;
    }
}
