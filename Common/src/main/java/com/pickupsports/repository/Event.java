package com.pickupsports.repository;

/**
 * Created by clarkperkins on 10/23/14.
 *
 */

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * A simple object to represent an event and its URL for viewing.
 *
 * @author jules
 */
@Entity
@Table(name="pickupsports_event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id = -1;

    @Column(name="event_name")
    private String eventName;

    @Column(name="sport")
    private String sport;

    @Column(name="description")
    private String description;

    @Column(name="attendance")
    private long attendance;

    @Column(name="max_attendance")
    private long maxAttendance;

    @Column(name="skill_level")
    private String skillLevel;

    @Column(name="equipment")
    private String equipment;

    @Column(name="location")
    private String location;

    @Column(name="time")
    private Date time;

    @Column(name="free")
    private boolean free;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "pickupsports_event_users",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> users;

    public Event() {
        super();
    }

    public Event(String x){
        String[] array = x.split("\t");
        eventName = array[0];
        sport = array[1];
        description = array[2];
        attendance = Long.parseLong(array[3]);
        skillLevel = array[4];
        equipment = array[5];
        location = array[6];
        //time =new Time(array[7]);
        free = Boolean.parseBoolean(array[8]);
    }

    public Event(String eventName, String sport, String description, long maxAttendance,
                 String skillLevel, String equipment, String location,
                 Date time, boolean free) {
        this();
        this.eventName = eventName;
        this.sport = sport;
        this.description = description;
        this.maxAttendance = maxAttendance;
        this.attendance = 0;	
        this.skillLevel = skillLevel;
        this.equipment = equipment;
        this.location = location;
        this.time = time;
        this.free = free;
    }
    
    public Event(String eventName, String sport, String description, long maxAttendance,
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
    
    public long getMaxAttendance() {
		return maxAttendance;
	}

	public void setMaxAttendance(long maxAttendance) {
		this.maxAttendance = maxAttendance;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
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
                    && Objects.equal(attendance, other.attendance)
                    && Objects.equal(skillLevel, other.skillLevel)
                    && Objects.equal(equipment, other.equipment)
                    && Objects.equal(location, other.location)
//                    && Objects.equal(time, other.time)
                    && Objects.equal(free, other.free);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return eventName + "\t" + sport + "\t" + description + "\t" + attendance + "\t" + skillLevel + "\t"
                + equipment + "\t" + location + "\t" + time + "\t" + free;
    }
}

