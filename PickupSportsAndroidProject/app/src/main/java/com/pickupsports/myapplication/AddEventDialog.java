package com.pickupsports.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.pickupsports.repository.Event;

/**
 * AddEventDialog-
 * used to create the dialog used to create a new pickup sports event
 */
public class AddEventDialog extends Dialog {

    public AddEventDialog(Context context) {
        super(context);
        this.setContentView(R.layout.add_event_dialog);
        this.setTitle(context.getResources().getString(R.string.event_dialog_title));

        final EditText notes = (EditText) findViewById(R.id.EditTextAddNotes);
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
                Event newEvent = createBasicEvent(sport, notes.getText().toString());
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
    private Event createBasicEvent(String sport, String description) {
        return new Event(null, sport, description, 100, null);
    }
}