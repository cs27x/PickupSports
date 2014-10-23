package com.pickupsports.client;

/**
 * Created by clarkperkins on 10/23/14.
 */

import com.pickupsports.repository.Event;
import retrofit.http.*;

import java.util.Collection;

/**
 * This interface defines an API for a VideoSvc. The
 * interface is used to provide a contract for client/server
 * interactions. The interface is annotated with Retrofit
 * annotations so that clients can automatically convert the
 *
 *
 * @author jules
 *
 */
public interface EventSvcApi {

    public static final String TITLE_PARAMETER = "title";

    public static final String DURATION_PARAMETER = "duration";

    // The path where we expect the VideoSvc to live
    public static final String VIDEO_SVC_PATH = "/video";

    // The path to search videos by title
    public static final String VIDEO_TITLE_SEARCH_PATH = VIDEO_SVC_PATH + "/search/findByName";

    // The path to search videos by title
    public static final String VIDEO_DURATION_SEARCH_PATH = VIDEO_SVC_PATH + "/search/findByDurationLessThan";

    @GET(VIDEO_SVC_PATH)
    public Collection<Event> getVideoList();

    @POST(VIDEO_SVC_PATH)
    public Void addEvent(@Body Event v);

    @DELETE(VIDEO_SVC_PATH + "/{id}")
    public Void deleteVideo(@Path("id") long id);

    @GET(VIDEO_TITLE_SEARCH_PATH)
    public Collection<Event> findByTitle(@Query(TITLE_PARAMETER) String title);

    @GET(VIDEO_DURATION_SEARCH_PATH)
    public Collection<Event> findByDurationLessThan(@Query(DURATION_PARAMETER) long duration);

}
