package edu.cnm.deepdive.mealplanit.models;


import android.arch.persistence.room.*;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(primaryKeys = {"person_id", "restriction_id"},
        foreignKeys = {@ForeignKey(entity = Person.class,
        parentColumns = "person_id",
        childColumns = "person_id",
        onDelete = CASCADE), @ForeignKey(entity = Restriction.class,
        parentColumns = "restriction_id",
        childColumns = "restriction_id",
        onDelete = CASCADE)})
public class PersonRestriction {


    @ColumnInfo(name = "person_id", index = true)
    private long personId;

    @ColumnInfo(name = "restriction_id", index = true)
    private long restrictionId;


    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public long getRestrictionId() {
        return restrictionId;
    }

    public void setRestrictionId(long restrictionId) {
        this.restrictionId = restrictionId;
    }

    @Override
    public String toString() {
        return personId + " " + restrictionId;
    }
}
