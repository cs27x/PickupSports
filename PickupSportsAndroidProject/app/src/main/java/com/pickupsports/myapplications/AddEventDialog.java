package com.pickupsports.myapplications;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.pickupsports.client.EventSvcApi;
import com.pickupsports.repository.Event;

import java.util.concurrent.Callable;

/**
 * AddEventDialog-
 * used to create the dialog used to create a new pickup sports event
 */
public class AddEventDialog extends Dialog {

    EventSvcApi eventService;

    public AddEventDialog(Context context) {
        super(context);
        this.setContentView(R.layout.add_event_dialog);
        this.setTitle(context.getResources().getString(R.string.event_dialog_title));

        final EditText notes = (EditText) findViewById(R.id.EditTextAddNotes);
        final EditText name = (EditText) findViewById(R.id.EditTextName);
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerSports);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.sports, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

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
                sport = spinner.getSelectedItem().toString();
                Event newEvent = createBasicEvent(name.getText().toString(), sport, notes.getText().toString());
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
    private Event createBasicEvent(String sport, String description, String name) {
        return new Event(name, sport, description, 100, null);
    }

    private void refreshVideos(final Event event) {
        final EventSvcApi svc = EventSvc.init("http://10.66.159.70:8080");

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