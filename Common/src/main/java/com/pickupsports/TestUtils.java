package com.pickupsports;

import com.pickupsports.repository.Event;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.UUID;

/**
 * Created by jules on 10/6/14.
 *
 */
public class TestUtils {

    private static final String[] POSSIBLE_LOCALHOSTS = {
            "10.0.2.2", // Android Emulator
            "192.168.56.1" // Genymotion
    };

    public static String findTheRealLocalhostAddress() {
        String realLocalHost = null;

        for(String localhost : POSSIBLE_LOCALHOSTS) {
            try {
                URL url = new URL("http://"+localhost+":8080");
                URLConnection con = url.openConnection();
                con.setConnectTimeout(500);
                con.setReadTimeout(500);
                InputStream in = con.getInputStream();
                if (in != null){
                    realLocalHost = localhost;
                    in.close();
                    break;
                }
            } catch (Exception e) {}
        }

        return realLocalHost;
    }

    public static Event randomEvent() {
        return new Event(
                randomEventName(),
                randomEventSport(),
                randomEventDescription(),
                randomEventAttendance(),
                randomEventSkillLevel(),
                randomEventEquipment(),
                randomEventLocation(),
                randomEventTime(),
                randomEventFree());
    }

    public static String randomEventName() {
        return UUID.randomUUID().toString();
    }

    public static String randomEventSport() {
        return UUID.randomUUID().toString();
    }

    public static String randomEventDescription() {
        return UUID.randomUUID().toString();
    }

    public static long randomEventAttendance() {
        return (long)Math.rint(Math.random() * 1000);
    }

    public static String randomEventSkillLevel() {
        return UUID.randomUUID().toString();
    }

    public static String randomEventEquipment() {
        return UUID.randomUUID().toString();
    }

    public static String randomEventLocation() {
        return UUID.randomUUID().toString();
    }

    public static Date randomEventTime() {
        return new Date();
    }

    public static boolean randomEventFree() {
        return Math.random() < 0.5;
    }

}

