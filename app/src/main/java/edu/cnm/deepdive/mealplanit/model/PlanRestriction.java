package edu.cnm.deepdive.mealplanit.model;


import android.arch.persistence.room.*;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Model that holds the information for when the {@link PlanRestriction} table is created.
 * It is tied to ids from both {@link Restriction} and {@link Plan}. This is a table for
 * future implementation of multiple restrictions which makes both a many to many, to a one to many.
 */
@Entity(primaryKeys = {"restriction_id", "plan_id"},
    foreignKeys = {@ForeignKey(entity = Restriction.class,
        parentColumns = "restriction_id",
        childColumns = "restriction_id",
        onDelete = CASCADE),
        @ForeignKey(entity = Plan.class,
            parentColumns = "plan_id",
            childColumns = "plan_id",
            onDelete = CASCADE)})
public class PlanRestriction {

  @ColumnInfo(name = "restriction_id", index = true)
  private long restrictionId;

  @ColumnInfo(name = "plan_id", index = true)
  private long planId;


  public long getRestrictionId() {
    return restrictionId;
  }

  public void setRestrictionId(long restrictionId) {
    this.restrictionId = restrictionId;
  }

  public long getPlanId() {
    return planId;
  }

  public void setPlanId(long planId) {
    this.planId = planId;
  }

  @Override
  public String toString() {
    return restrictionId + " " + planId;
  }
}
