package com.pickupsports.myapplications;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.pickupsports.client.EventSvcApi;
import com.pickupsports.repository.Event;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Callable;
import android.content.*;

/**
 * AddEventDialog-
 * used to create the dialog used to create a new pickup sports event
 */
public class AddEventDialog extends Dialog {
    final String PREFS_NAME = "mysp";

    EventSvcApi eventService;
    public AddEventDialog(Context context) {
        super(context);
        this.setContentView(R.layout.add_event_dialog);
        this.setTitle(context.getResources().getString(R.string.event_dialog_title));

        final EditText notes = (EditText) findViewById(R.id.EditTextAddNotes);
        final EditText name = (EditText) findViewById(R.id.EditTextName);
        final EditText location = (EditText) findViewById(R.id.EditTextLocation);
        final Spinner costSpinner = (Spinner) findViewById(R.id.spinnerCost);
        final Spinner sportsSpinner = (Spinner) findViewById(R.id.spinnerSports);
        final TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);

        ArrayAdapter<CharSequence> sportsAdapter = ArrayAdapter.createFromResource(context,
                R.array.sports, android.R.layout.simple_spinner_item);
        sportsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sportsSpinner.setAdapter(sportsAdapter);

        ArrayAdapter<CharSequence> costAdapter = ArrayAdapter.createFromResource(context,
                R.array.cost, android.R.layout.simple_spinner_item);
        costAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        costSpinner.setAdapter(costAdapter);

        Button cancel = (Button) findViewById(R.id.buttonCancelAdding);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Event newEvent = new Event(null,)
                AddEventDialog.this.dismiss();
            }
        });

        // onClickListener for Add Event button
        Button addEvent = (Button) findViewById(R.id.buttonAddEvent);
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sport = "basketball";
                String cost = "";
                Date time = createDateFromTime(timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                sport = sportsSpinner.getSelectedItem().toString();
                cost = sportsSpinner.getSelectedItem().toString();
                Event newEvent = createBasicEvent(sport, notes.getText().toString(),
                        name.getText().toString(), time, location.getText().toString());

                SharedPreferences.Editor editor = getOwnerActivity().getSharedPreferences(PREFS_NAME, 0).edit();
                editor.putString("event" + newEvent.getEventName(), newEvent.toString());
                editor.commit();
                if (cost.equals("Free")) {
                    newEvent.setFree(true);
                }
                else
                {
                    newEvent.setFree(false);
                }
                refreshVideos(newEvent);
                AddEventDialog.this.dismiss();
            }
        });
    }

    /**
     * createBasicEvent-
     * private helper method used to create basic events
     *
     * @param sport       - the sport that is selected on the spinner
     * @param description - the description of the event entered in the editText
     * @return - the created event
     */
    private Event createBasicEvent(String sport, String description, String name, Date time, String location) {
        return new Event(name, sport, description, 100,
                "skill level", "equipment", location, time, true);
    }

    /**
     * createDateFromTime-
     * helper method that creates a date event from time values
     *
     * @param hour-    the hour to be used in the date event
     * @param minutes- the minutes to be used in the date event
     * @return- a date event that contains defaults except for the hour and minutes
     */
    private Date createDateFromTime(int hour, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date newDate = calendar.getTime();
        return newDate;
    }

    //refreshes video feed. Need to update this to occur after creation of an event
    // as well as autoupdate when others create events or add refresh button.
    private void refreshVideos(final Event event) {
        final EventSvcApi svc = EventSvc.init("http://pickupsports.herokuapp.com");

        if (svc != null) {
            CallableTask.invoke(new Callable<Void>() {

                @Override
                public Void call() throws Exception {
                    return svc.addEvent(event);
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