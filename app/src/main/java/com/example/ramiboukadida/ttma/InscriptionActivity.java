package com.example.ramiboukadida.ttma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Rami Boukadida on 04/11/2015.
 */
public class InscriptionActivity extends Activity {

    Button valider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        valider = (Button) findViewById(R.id.valider);
        valider.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {


                Intent myIntent = new Intent(view.getContext(), MainActivity.class);

                startActivity(myIntent);

            }

        });
    }
}
