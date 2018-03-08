package edu.cnm.deepdive.mealplanit.models;


import android.arch.persistence.room.*;
import android.support.annotation.NonNull;
import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(indices = {@Index(value = {"username"}, unique = true)},
        foreignKeys = {@ForeignKey(entity = Diet.class,
                parentColumns = "diet_id",
                childColumns = "diet_id",
                onDelete = CASCADE)})


public class Person {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "person_id")
    private long personId;

    @NonNull
    @ColumnInfo(name = "first_name", collate = ColumnInfo.NOCASE)
    private String firstName;

    @NonNull
    @ColumnInfo(name = "last_name", collate = ColumnInfo.NOCASE)
    private String lastName;

    @NonNull
    @ColumnInfo(name = "username", collate = ColumnInfo.NOCASE)
    private String username;

    @ColumnInfo(name = "calories_per_day")
    private long caloriesPerDay;

    @ColumnInfo(name = "diet_id")
    private long dietId;

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getCaloriesPerDay() {
        return caloriesPerDay;
    }

    public void setCaloriesPerDay(long caloriesPerDay) {
        this.caloriesPerDay = caloriesPerDay;
    }

    public long getDietId() {
        return dietId;
    }

    public void setDietId(long dietId) {
        this.dietId = dietId;
    }

    @Override
    public String toString() {
        return personId+ " " + firstName + " " + lastName + " " + username + " " + caloriesPerDay + " " + dietId;
    }

}
