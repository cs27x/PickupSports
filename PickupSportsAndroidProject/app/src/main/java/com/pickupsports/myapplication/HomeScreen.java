package com.pickupsports.myapplication;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeScreen extends ListActivity {
//    RestaurantList allEvents;
//    RestaurantList filteredEvents;
//    ListView listViewEvents;
//    Spinner spinnerSorting;
//    Spinner spinnerFilter;
//    ArrayAdapter<String> spinnerSortingAdapter;
//    ArrayAdapter<String> spinnerFilterAdapter;
//    SimpleAdapter listViewAdapter;
//    int curPositionSorting;
//    static final List<Map<String,String>> list = new ArrayList<Map<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

//        allRestaurants = RestaurantList.getInstance(getResources());
//
//        setUpSpinners();
//        listViewRestaurants = (ListView) findViewById(android.R.id.list);
//        filteredRestaurants = allRestaurants;
//        listViewRestaurants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Event restaurant = filteredEvents.get(i);
//                Intent intent = new Intent(getBaseContext(), MyActivity.class);
//                intent.putExtra("restaurant", restaurant);
//                startActivity(intent);
//
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_screen, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_new_event:
                AddEventDialog dialog = new AddEventDialog(this);
                dialog.show();
                return true;
//            case R.id.action_settings:
//                openSettings();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpSpinners() {
        // TODO Auto-generated method stub
//        spinnerSorting = (Spinner) findViewById(R.id.spinnerSorting);
//        spinnerSortingAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
//        spinnerSortingAdapter.add("Distance");
//        spinnerSortingAdapter.add("Time Until Closed");
//        spinnerSorting.setAdapter(spinnerSortingAdapter);
//        spinnerSorting.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view,
//                                       int position, long id) {
//                // TODO Auto-generated method stub
//                performSort(position);
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // TODO Auto-generated method stub
//
//            }
//        });
//
//        spinnerFilter = (Spinner) findViewById(R.id.spinnerFilters);
//        spinnerFilterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
//        spinnerFilterAdapter.add("Open");
//        spinnerFilterAdapter.add("Meal Plan");
//        spinnerFilterAdapter.add("Taste of Nashville");
//        spinnerFilter.setAdapter(spinnerFilterAdapter);
//        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view,
//                                       int position, long id) {
//                // TODO Auto-generated method stub
//                if (position == 0) {
//                    Log.i("filteredRests", "open");
//                    filteredRestaurants = allRestaurants.getOpen();
//                    Log.i("the open size is", "" + filteredRestaurants.size());
//                    performSort(curPositionSorting);
//                } else if (position == 1) {
//                    filteredRestaurants = allRestaurants.getMealPlan();
//                    Log.i("the mealplan size is", "" + filteredRestaurants.size());
//                    performSort(curPositionSorting);
//
//                } else {
//                    filteredRestaurants = allRestaurants.getToN();
//                }
//                populateList();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // TODO Auto-generated method stub
//
//            };
//        });
    }

    private void performSort(int position) {
//        if(position == 1) {
//            Log.i("filtering by time", "now");
//            filteredRestaurants.sortByTime();
//        } else {
//            Log.i("filtering by distance", "now");
//            filteredRestaurants.sortByDistance(gps.getLatitude(), gps.getLongitude());
//        }
//        curPositionSorting = position;
//        populateList();
    }

    private void populateList() {

        //MAKE SURE TO CLEAR LISTVIEW BEFORE POPULATING LISTVIEW
//        String[] subitems = {"Name", "Distance", "Hours Status", "Meal Status"};
//        list.clear();
//        listViewAdapter = new SimpleAdapter(this, list, R.layout.custom_row_view,
//                subitems, new int[] {R.id.text1,R.id.text2, R.id.text3, R.id.text4});
//        for(Event r : filteredEvents) {
//            //populate listViewAdapter
//            Map<String,String> temp = new HashMap<String,String>();
//            temp.put("Name", r.getName()"'s " + r.getSport());
//            temp.put("Distance", Double.toString(r.getDistanceFrom(gps.getLatitude(), gps.getLongitude())) + " m");
//            temp.put("Hours Status", r.isOpen() ? "Open" : "Closed");
//            temp.put("Meal Status", r.isMealPlan() ? "Meal Plan" : "Taste of Nashville");
//            list.add(temp);
//        }
//        setListAdapter(listViewAdapter);
//        this.listViewRestaurants.deferNotifyDataSetChanged();
    }
}