package com.pickupsports.repository;

/**
 * Created by clarkperkins on 10/23/14.
 *
 */

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

    private String eventName;
    private String sport;
    private String description;

    private long attendance;
    private long max_attendance;
    private String skillLevel;

    private String equipment;
    private String location;

    private Date time;
    private boolean free;


    public Event() {
        super();
    }
    
    public Event(String eventName, String sport, String description, long max_attendance,
                 String skillLevel, String equipment, String location,
                 Date time, boolean free) {
        this();
        this.eventName = eventName;
        this.sport = sport;
        this.description = description;
        this.setMax_attendance(max_attendance);
        this.attendance = 0;	
        this.skillLevel = skillLevel;
        this.equipment = equipment;
        this.location = location;
        this.time = time;
        this.free = free;
    }
    
    public Event(String eventName, String sport, String description, long max_attendance, 
    		String equipment) {
    	this();
    	this.eventName = eventName;
    	this.sport = sport;
    	this.description = description;
    	this.attendance = 0;
	   	this.skillLevel = "";
	   	this.equipment = equipment;
	   	this.location = "";
	   	this.time = new Date();
	   	this.free = true;
	}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
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
    
    public long getMax_attendance() {
		return max_attendance;
	}

	public void setMax_attendance(long max_attendance) {
		this.max_attendance = max_attendance;
	}

	public void incrementAttendance()
    {
    	this.attendance ++;
    }
    
    public void decrementAttendance()
    {
    	this.attendance --;
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
     * Two Events are considered equal if they have exactly the same values for
     * all their fields
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event other = (Event) obj;
            // Google Guava provides great utilities for equals too!
            return Objects.equal(eventName, other.eventName)
                    && Objects.equal(sport, other.sport)
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

    @Override
    public String toString() {
        return eventName + " / " + sport + " / " + description + " / " + attendance + " / " + skillLevel + " / "
                + equipment + " / " + location + " / " + time + " / " + free;
    }
}

