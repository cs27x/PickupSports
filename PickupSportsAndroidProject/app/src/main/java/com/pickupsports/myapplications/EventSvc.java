package com.pickupsports.myapplications;

import android.content.Context;

import com.pickupsports.TestUtils;
import com.pickupsports.client.EventSvcApi;

public class EventSvc {

    private static EventSvcApi videoSvc_;

    public static synchronized EventSvcApi getOrShowLogin(Context ctx) {
        if (videoSvc_ != null) {
            return videoSvc_;
        } else {
//            Intent i = new Intent(ctx, LoginScreenActivity.class);
//            ctx.startActivity(i);
            return null;
        }
    }

    public static synchronized EventSvcApi init(String server) {//, String user,  String pass

        videoSvc_ = TestUtils.getEventService(server);

        return videoSvc_;
    }
}
