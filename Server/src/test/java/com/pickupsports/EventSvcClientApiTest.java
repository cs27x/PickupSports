package com.pickupsports;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pickupsports.client.EventSvcApi;
import com.pickupsports.repository.Event;
import org.junit.Before;
import org.junit.Test;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.converter.GsonConverter;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * This integration test sends HTTP calls to the server using the Retrofit library.
 * The server must be running before you launch this test. 
 *
 * @author jules
 *
 */
public class EventSvcClientApiTest {

    private EventSvcApi eventService;

    private Event event;

    @Before
    public void setUp() throws Exception {

        final String TEST_URL = "http://localhost:8080";

        eventService = TestUtils.getEventService(TEST_URL);

        event = TestUtils.randomEvent();

    }

    /**
     * This test creates a Event, adds the Event to the EventSvc, and then
     * checks that the Event is included in the list when getEventList() is
     * called.
     *
     * @throws Exception
     */
    @Test
    public void testEventCrud() throws Exception {

        // Add the event
        eventService.addEvent(event);

        // We should get back the event that we added above
        Collection<Event> events = eventService.getEventList();
        assertTrue(events.size() > 0);
        assertTrue(events.contains(event));

        for(Event e : events) {
            eventService.deleteEvent(e.getId());
        }

        events = eventService.getEventList();
        assertEquals(0, events.size());
    }


    @Test
    public void testEventFindBySport() throws Exception {

        // Add the event
        eventService.addEvent(event);

        Collection<Event> events = eventService.findBySport(event.getSport());
        assertTrue(events.size() > 0);
        for(Event e : events){
            assertEquals(event.getSport(), e.getSport());
        }

        for(Event e : events){
            eventService.deleteEvent(e.getId());
        }

        assertTrue(eventService.findBySport(event.getSport()).size() == 0);
    }

}
