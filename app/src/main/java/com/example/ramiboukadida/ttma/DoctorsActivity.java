package com.example.ramiboukadida.ttma;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import utils.JsonUtils;

/**
 * Created by FOUED BRAHIM on 08/11/2015.
 */
public class DoctorsActivity extends ActionBarActivity {

     private static String url = "http://10.0.2.2:18080/PidevService-web/rest/doctors/getall";

     private static final String TAG_NAME = "firsNameDoc";
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private ProgressDialog progressDialog;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    JSONArray users = null;

    private TextView responseTv;
    private static String resp;

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
        responseTv = (TextView) findViewById(R.id.responseTv);
        new ParseJSON().execute();

        mDrawerList = (ListView) findViewById(R.id.navList);


        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DoctorsActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
            }
        });

        addDrawerItems();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        //mActivityTitle = getTitle().toString();
        getSupportActionBar().setTitle("Doctors");

        setupDrawer();

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
            }
        };
    }

    private void addDrawerItems(){
        String[] osArray = {  "Clinics","Our doctors","Consultation Request","My appointements","Home","Disconnect" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(EventsActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0: {
                        Intent intent = new Intent(DoctorsActivity.this, CliniqueActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 1:{
                        Intent intent = new Intent(DoctorsActivity.this, DoctorsActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 2:{
                        Intent intent = new Intent(DoctorsActivity.this, ConsulterSpecialisteActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 3:{
                        Intent intent = new Intent(DoctorsActivity.this, MyappointementsActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 4:{
                        Intent intent = new Intent(DoctorsActivity.this,HomeActivity.class);
                        startActivity(intent);
                        break;
                    }

                    case 5:{
                        Intent intent = new Intent(DoctorsActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    }
                    default:
                        break;

                }
            }
        });
    }



    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Menu");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle("Evénements");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //  return true;
        //}

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        // your code.
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle(R.string.quitter);

        // set dialog message
        alertDialogBuilder

                .setCancelable(true)
                .setPositiveButton(R.string.oui, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        //EventsActivity.this.finish();
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(startMain);
                        finish();
                    }
                })
                .setNegativeButton(R.string.non, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();

                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    private class ParseJSON extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Show the progress dialog for loading activity
            progressDialog = new ProgressDialog(DoctorsActivity.this);
            progressDialog.setMessage("Loading Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {

           String stream = JsonUtils.request(url).toString();




            if(stream!=null) {

                try {
                    // JSONObject jsonObject = new JSONObject(jsonStr);
                    // users = jsonObject.getJSONArray(TAG_USERS);
                    JSONArray users=new JSONArray(stream);
                    for(int i=0;i<users.length();i++){
                        JSONObject u = users.getJSONObject(i);

                        //  JSONObject usr = u.getJSONObject(TAG_USER);


                        String name = u.getString(TAG_NAME);

                        resp = "NAME : " + name ;


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                responseTv.setText(resp);
                                Toast.makeText(getApplicationContext(),resp,Toast.LENGTH_LONG).show();
                            }
                        });



                    }



                }catch (JSONException jex){
                    Log.d("Error -> ",jex.toString());
                }
            }


            return null;
        }


}}


