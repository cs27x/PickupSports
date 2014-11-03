package com.pickupsports.client;

/**
 * Created by clarkperkins on 10/23/14.
 *
 */


import com.pickupsports.repository.Event;
import retrofit.http.*;

import java.util.Collection;

/**
 * This interface defines an API for a EventSvc. The
 * interface is used to provide a contract for client/server
 * interactions. The interface is annotated with Retrofit
 * annotations so that clients can automatically convert the
 *
 *
 * @author jules
 *
 */
public interface EventSvcApi {

    public static final String SPORT_PARAMETER = "sport";

    // The path where we expect the EventSvc to live
    public static final String EVENT_SVC_PATH = "/events/";

    // The path to search videos by title
    public static final String EVENT_SPORT_SEARCH_PATH = EVENT_SVC_PATH + "search/findBySport/";

    // The path to search videos by title
//    public static final String EVENT_DURATION_SEARCH_PATH = EVENT_SVC_PATH + "search/findByDurationLessThan";

    @GET(EVENT_SVC_PATH)
    public Collection<Event> getEventList();

    @POST(EVENT_SVC_PATH)
    public Void addEvent(@Body Event event);

    @GET(EVENT_SVC_PATH + "/{id}")
    public Event getEvent(@Path("id") long id);

    @PUT(EVENT_SVC_PATH + "/{id}")
    public Void editEvent(@Path("id") long id, @Body Event newEvent);

    @DELETE(EVENT_SVC_PATH + "/{id}")
    public Void deleteEvent(@Path("id") long id);

    @GET(EVENT_SPORT_SEARCH_PATH)
    public Collection<Event> findBySport(@Query(SPORT_PARAMETER) String sport);

//    @GET(EVENT_DURATION_SEARCH_PATH)
//    public Collection<Event> findByDurationLessThan(@Query(DURATION_PARAMETER) long duration);

}
