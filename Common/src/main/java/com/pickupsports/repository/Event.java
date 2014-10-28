package com.pickupsports.repository;

/**
 * Created by clarkperkins on 10/23/14.
 *
 */

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.google.common.base.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * A simple object to represent an event and its URL for viewing.
 *
 * @author jules
 */
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id = -1;

    private String sport;
    private String description;

    private long attendance;
    private String skillLevel;

    private String equipment;
    private String location;

    @JsonSerialize(using = DateSerializer.class)
    private Date time;
    private boolean free;


    public Event() {
        super();
    }

    public Event(String sport, String description, long attendance,
                 String skillLevel, String equipment, String location,
                 Date time, boolean free) {
        this();
        this.sport = sport;
        this.description = description;
        this.attendance = attendance;
        this.skillLevel = skillLevel;
        this.equipment = equipment;
        this.location = location;
        this.time = time;
        this.free = free;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getAttendance() {
        return attendance;
    }

    public void setAttendance(long attendance) {
        this.attendance = attendance;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    /**
     * Two Videos will generate the same hashcode if they have exactly the same
     * values for their name, url, and duration.
     */
    @Override
    public int hashCode() {
        // Google Guava provides great utilities for hashing
        return Objects.hashCode(sport, description, attendance, skillLevel,
                equipment, location, time, free);
    }



    /**
     * Two Videos are considered equal if they have exactly the same values for
     * their name, url, and duration.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event other = (Event) obj;
            // Google Guava provides great utilities for equals too!
            return Objects.equal(sport, other.sport)
                    && Objects.equal(description, other.description)
                    && attendance == other.attendance
                    && Objects.equal(skillLevel, other.skillLevel)
                    && Objects.equal(equipment, other.equipment)
                    && Objects.equal(location, other.location)
                    && Objects.equal(time, other.time)
                    && free == other.free;
        } else {
            return false;
        }
    }

}

