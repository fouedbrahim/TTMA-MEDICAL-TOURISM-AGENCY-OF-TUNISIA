package com.example.ramiboukadida.ttma;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    final Context context = this;

    Button valider;
    Button sinscrire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valider = (Button) findViewById(R.id.valider);
        valider.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {


                Intent myIntent = new Intent(view.getContext(), HomeActivity.class);

                startActivity(myIntent);

            }

        });

        sinscrire = (Button) findViewById(R.id.sinscrire);
        sinscrire.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {


                Intent myIntent = new Intent(view.getContext(), InscriptionActivity.class);

                startActivity(myIntent);

            }

        });
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
}
