package com.pickupsports.repository;

/**
 * Created by clarkperkins on 10/23/14.
 *
 */

import com.google.common.base.Objects;

import javax.persistence.Entity;

import java.util.HashSet;
import java.util.Set;

/**
 * A simple object to represent an event and its URL for viewing.
 *
 * @author jules
 */
@Entity
public class User {
    private String name;
    private int attendance_rating;
    private int skill_rating;
    private Set<String> favorite_sports;
    private Set<Event> joined_events;

    public User(String n){
        name = n;
        attendance_rating = 0;
        skill_rating = 0;
        favorite_sports = new HashSet<String>();
        joined_events = new HashSet<Event>();
    }

    public String getName(){
        return name;
    }

    public void setAttendance_rating(int attendance_rating){
        this.attendance_rating = attendance_rating;
    }

    public int getAttendance_rating(){
        return attendance_rating;
    }

    public int getSkill_rating() {
        return skill_rating;
    }

    public void setSkill_rating(int skill_rating) {
        this.skill_rating = skill_rating;
    }

    public void addFavoriteSport(String sport){
        favorite_sports.add(sport);
    }

    public void removeFavoriteSport(String sport){
        favorite_sports.remove(sport);
    }

    public boolean isFavoriteSport(String sport){
        if(favorite_sports.contains(sport)){
            return true;
        }
        return false;
    }

    public void joinEvent(Event e){
        joined_events.add(e);
    }

    public void unJoinEvent(Event e){
        joined_events.remove(e);
    }

    public boolean isJoinedEvent(Event e){
        if(joined_events.contains(e)){
            return true;
        }
        return false;
    }



}

