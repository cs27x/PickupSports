package com.pickupsports;

import com.pickupsports.client.EventSvcApi;
import com.pickupsports.json.JacksonConverter;
import com.pickupsports.json.ResourcesMapper;
import com.pickupsports.repository.Event;
import org.junit.Before;
import org.junit.Test;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;

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

        eventService = new RestAdapter.Builder()
                .setEndpoint(TEST_URL)
                .setConverter(new JacksonConverter(new ResourcesMapper()))
                .setLogLevel(LogLevel.FULL)
                .build()
                .create(EventSvcApi.class);

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

        for (Event e : events) {
            System.out.println(e);
        }
        System.out.println(event);

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
