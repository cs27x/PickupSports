package com.pickupsports.repository;


import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * A simple class defining a User of the App
 *
 * @author Sean
 */
@Entity
@Table(name = "pickupsports_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id = -1;

    @Column(name = "name")
    private String name;

    @Column(name = "attendance_rating")
    private int attendanceRating;

    @Column(name = "skill_rating")
    private int skillRating;

//    @Column(name = "favorite_sports")
//    private Set<String> favoriteSports;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    private Set<Event> joinedEvents;

    /**
     * Constructs a User object with the users name
     * @param n the name of the user
     */
    public User(String n){
        name = n;
        attendanceRating = 0;
        skillRating = 0;
//        favoriteSports = new HashSet<>();
        joinedEvents = new HashSet<>();
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
    public void setAttendanceRating(int attendance_rating){
        this.attendanceRating = attendance_rating;
    }

    /**
     *
     * @return users attendance rating
     */
    public int getAttendanceRating(){
        return attendanceRating;
    }

    /**
     *
     * @return users skill rating
     */
    public int getSkillRating() {
        return skillRating;
    }

    /**
     *
     * @param skill_rating new skill rating for the user
     */
    public void setSkillRating(int skill_rating) {
        this.skillRating = skill_rating;
    }

    /**
     *
     * @param sport sport to add to list of favorite sports
     */
//    public void addFavoriteSport(String sport){
//        favoriteSports.add(sport);
//    }

    /**
     * removes sport from favorite_sports
     * @param sport sport to be removed
     */
//    public void removeFavoriteSport(String sport){
//        favoriteSports.remove(sport);
//    }

    /**
     * Determines whether a sport is in favorite_sports
     * @param sport sport to be checked
     * @return true if sport is in favorite_sports
     */
//    public boolean isFavoriteSport(String sport){
//        return favoriteSports.contains(sport);
//    }

    /**
     *
     * @param e event to be joined
     */
    public void joinEvent(Event e){
        joinedEvents.add(e);
    }

    /**
     *
     * @param e event to be removed from list
     */
    public void unJoinEvent(Event e){
        joinedEvents.remove(e);
    }

    /**
     *
     * @param e event to be checked
     * @return true if event is in the list, false otherwise
     */
    public boolean isJoinedEvent(Event e){
        return joinedEvents.contains(e);
    }
}

