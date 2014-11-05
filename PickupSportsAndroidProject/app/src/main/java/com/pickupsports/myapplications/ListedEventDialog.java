package com.pickupsports.myapplications;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pickupsports.client.EventSvcApi;
import com.pickupsports.repository.Event;

import java.util.concurrent.Callable;


/**
 * Created by Jeremey on 10/29/2014.
 */
public class ListedEventDialog extends Dialog{

    // Create custom dialog for a listed event
    public ListedEventDialog(final Context c, final Event e)
    {
        super(c);

        this.setContentView(R.layout.listed_event_dialog_layout);
        this.setTitle(e.getEventName());

        TextView sportText = (TextView)findViewById(R.id.textViewSport);
        sportText.setText("Sport: " + e.getSport());

        TextView attendText = (TextView)findViewById(R.id.textViewAttendance);
        attendText.setText("Attendance: " + e.getAttendance());

        TextView locationText = (TextView)findViewById(R.id.textViewLocation);
        locationText.setText("Location: " + e.getLocation());

        TextView timeText = (TextView)findViewById(R.id.textViewTime);
        timeText.setText("Time: " + e.getTime());

        EditText descriptionText = (EditText)findViewById(R.id.editTextNotes);
        descriptionText.setText(e.getDescription());


        Button cancel = (Button)findViewById(R.id.buttonCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListedEventDialog.this.dismiss();
            }
        });

        // onClickListener for Join button
        Button join = (Button)findViewById(R.id.buttonJoin);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event event = e;
                event.incrementAttendance();
                updateEvent(event);

                ListedEventDialog.this.dismiss();
            }
        });
    }

    private void updateEvent(final Event event) {
        final EventSvcApi svc = EventSvc.init("http://pickupsports.herokuapp.com");

        if (svc != null) {
            CallableTask.invoke(new Callable<Void>() {

                @Override
                public Void call() throws Exception {
                    return svc.editEvent(event.getId(),event);
                }
            }, new TaskCallback<Void>() {

                @Override
                public void success(Void result) {
//                    List<String> names = new ArrayList<String>();
//                    for (Void v : result) {
//                        names.add(v.getName());
//                    }
//                    videoList_.setAdapter(new ArrayAdapter<String>(
//                            VideoListActivity.this,
//                            android.R.layout.simple_list_item_1, names));
                }

                @Override
                public void error(Exception e) {
//                    Toast.makeText(
//                            VideoListActivity.this,
//                            "Unable to fetch the video list, please login again.",
//                            Toast.LENGTH_SHORT).show();
//
//                    startActivity(new Intent(VideoListActivity.this,
//                            LoginScreenActivity.class));
                }
            });
        }
    }
}

