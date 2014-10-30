package com.pickupsports;

import static org.junit.Assert.assertEquals;

import com.pickupsports.repository.Event;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class EventTest {

    private Event event1_;
    private Event event2_;

    @Before
    public void setUp() throws Exception {
        Date time = new Date();

        this.event1_ = new Event(
                "name",
                "basketball",
                "blah",
                10,
                "level",
                "basketball",
                "rec center",
                time,
                true);

        this.event2_ = new Event(
                "name",
                "basketball",
                "blah",
                10,
                "level",
                "basketball",
                "rec center",
                time,
                true);
    }

    @Test
    public void testEquals() {
        assertEquals(event1_, event2_);
    }

    @Test
    public void testHashcode() {
        assertEquals(event1_.hashCode(), event2_.hashCode());
    }

}
