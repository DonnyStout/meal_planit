package edu.cnm.deepdive.mealplanit.models;


import android.arch.persistence.room.*;

@Entity(indices = {@Index(value = {"username"}, unique = true)})
public class Person {


    @PrimaryKey(autoGenerate = true)
    private long personId;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "food_restrictions_id")
    private long foodRestrictionsId;

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

    public long getFoodRestrictionsId() {
        return foodRestrictionsId;
    }

    public void setFoodRestrictionsId(long foodRestrictionsId) {
        this.foodRestrictionsId = foodRestrictionsId;
    }


    @Override
    public String toString() {
        return firstName + " " + lastName + " " + username + " " + foodRestrictionsId;
    }

}
