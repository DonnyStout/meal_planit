package edu.cnm.deepdive.mealplanit.model;




import static android.arch.persistence.room.ForeignKey.CASCADE;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Model that holds the information for creating the {@link PersonRestriction} table in the database.
 * Accessed and modify through the user of accessors and mutators. This model holds the information so
 * if multiple {@link Restriction} are allowed in a future iteration, this would make a many to many
 * into a one to many between {@link Person} and {@link Restriction}
 */
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
