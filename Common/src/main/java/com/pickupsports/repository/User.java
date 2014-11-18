package com.pickupsports.repository;

import javax.persistence.Entity;

import java.util.HashSet;
import java.util.Set;

/**
 * A simple class defining a User of the App
 *
 * @author Sean
 */
@Entity
public class User {
    private String name;
    private int attendance_rating;
    private int skill_rating;
    private Set<String> favorite_sports;
    private Set<Event> joined_events;

    /**
     * Constructs a User object with the users name
     * @param n the name of the user
     */
    public User(String n){
        name = n;
        attendance_rating = 0;
        skill_rating = 0;
        favorite_sports = new HashSet<String>();
        joined_events = new HashSet<Event>();
    }

    /**
     *
     * @return name of the user
     */
    public String getName(){
        return name;
    }

    /**
     *
     * @param attendance_rating new attendance rating for the user
     */
    public void setAttendance_rating(int attendance_rating){
        this.attendance_rating = attendance_rating;
    }

    /**
     *
     * @return users attendance rating
     */
    public int getAttendance_rating(){
        return attendance_rating;
    }

    /**
     *
     * @return users skill rating
     */
    public int getSkill_rating() {
        return skill_rating;
    }

    /**
     *
     * @param skill_rating new skill rating for the user
     */
    public void setSkill_rating(int skill_rating) {
        this.skill_rating = skill_rating;
    }

    /**
     *
     * @param sport sport to add to list of favorite sports
     */
    public void addFavoriteSport(String sport){
        favorite_sports.add(sport);
    }

    /**
     * removes sport from favorite_sports
     * @param sport sport to be removed
     */
    public void removeFavoriteSport(String sport){
        favorite_sports.remove(sport);
    }

    /**
     * Determines whether a sport is in favorite_sports
     * @param sport sport to be checked
     * @return true if sport is in favorite_sports
     */
    public boolean isFavoriteSport(String sport){
        return favorite_sports.contains(sport);
    }

    /**
     *
     * @param e event to be joined
     */
    public void joinEvent(Event e){
        joined_events.add(e);
    }

    /**
     *
     * @param e event to be removed from list
     */
    public void unJoinEvent(Event e){
        joined_events.remove(e);
    }

    /**
     *
     * @param e event to be checked
     * @return true if event is in the list, false otherwise
     */
    public boolean isJoinedEvent(Event e){
        return joined_events.contains(e);
    }
}

