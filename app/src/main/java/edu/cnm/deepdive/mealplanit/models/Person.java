package edu.cnm.deepdive.mealplanit.models;


import android.arch.persistence.room.*;

@Entity(indices = {@Index(value = {"username"}, unique = true)})

public class Person {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "person_id")
    private long personId;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "calories_per_day")
    private long caloriesPerDay;

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

    @Override
    public String toString() {
        return personId+ " " + firstName + " " + lastName + " " + username + " " + caloriesPerDay;
    }

}
