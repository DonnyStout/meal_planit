package edu.cnm.deepdive.mealplanit.model;


import android.arch.persistence.room.*;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {@ForeignKey(entity = Person.class,
    parentColumns = "person_id",
    childColumns = "person_id",
    onDelete = CASCADE), @ForeignKey(entity = Restriction.class,
    parentColumns = "restriction_id",
    childColumns = "restriction_id",
    onDelete = CASCADE)})
public class PersonRestriction {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "personrestriction_id")
  private long personRestrictionId;

  @ColumnInfo(name = "person_id", index = true)
  private long personId;

  @ColumnInfo(name = "restriction_id", index = true)
  private long restrictionId;


  public long getPersonRestrictionId() {
    return personRestrictionId;
  }

  public void setPersonRestrictionId(long personRestrictionId) {
    this.personRestrictionId = personRestrictionId;
  }

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
