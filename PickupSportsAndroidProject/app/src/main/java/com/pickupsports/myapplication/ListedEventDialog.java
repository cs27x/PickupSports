package com.pickupsports.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pickupsports.repository.Event;


/**
 * Created by Jeremey on 10/29/2014.
 */
public class ListedEventDialog extends Dialog{

    // Create custom dialog for an event
    public ListedEventDialog(final Context c, final Event e)
    {
        super(c);

        this.setContentView(R.layout.listed_event_dialog_layout);
        this.setTitle(e.getEventName());

        TextView sportText = (TextView)findViewById(R.id.textViewSport);
        sportText.setText("Sport: " + e.getSport());

        TextView attendText = (TextView)findViewById(R.id.textViewAttendance);
        attendText.setText("Attendance: " + e.getAttendance());


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
                ListedEventDialog.this.dismiss();

                //Event event = e;
                //event.incrementAttendance();
            }
        });
    }
}
