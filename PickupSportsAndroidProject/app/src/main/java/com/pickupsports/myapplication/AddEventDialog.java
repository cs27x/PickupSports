package com.pickupsports.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class AddEventDialog extends Dialog {

    public AddEventDialog(Context context) {
        super(context);
        this.setContentView(R.layout.add_event_dialog);
        this.setTitle("Add an Event");


        Spinner spinner = (Spinner) findViewById(R.id.spinnerSports);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.sports, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        Button cancel = (Button) findViewById(R.id.buttonCancelAdding);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddEventDialog.this.dismiss();
            }
        });

        // onClickListener for Add Event button
        Button addEvent = (Button) findViewById(R.id.buttonAddEvent);
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddEventDialog.this.dismiss();
            }
        });
    }
}