package de.bfw.mytestapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // XML Elemente in Java erstellen
    private EditText nameET, wohnOrtET;
    private AppCompatButton sendB;
    private AppCompatButton switchB;
    private TextView anzeigeTV;

    private String name;
    private String wohnort;
    private boolean firstRun;

    // SharedPreferences Datei öffnen
    SharedPreferences preferences;
    // öffnen des Editors (wird nur zum Speichern benötigt)
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

         */
        preferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        editor = preferences.edit();

        // laden eines Wertes
        this.firstRun = preferences.getBoolean("firstRunMyEmailClient",false);

        if (firstRun) {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        }

        setContentView(R.layout.uebung2_constraint);

        // Java Objekte mit XML Objekten verknüpfen
        nameET = findViewById(R.id.nameET);
        wohnOrtET = findViewById(R.id.wohnOrtET);
        sendB = findViewById(R.id.sendB);
        anzeigeTV = findViewById(R.id.anzeigeTV);
        switchB = findViewById(R.id.switchB);

        // Werte aus den Eingabefeldern holen und speichern
        name = nameET.getText().toString();
        wohnort = wohnOrtET.getText().toString();

        sendB.setOnClickListener(this);
        switchB.setOnClickListener(this);

        // speichern eines Key/Value-Paares
        editor.putBoolean("firstRunMyEmailClient", true);
        // Speichern abschliessen
        editor.apply();

        /*
        - anonymous onClickListener -
        sendB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do something
            }
        });

        sendB.setOnClickListener(v -> {
            @Override
            public void onClick(View v) {

            }
        });
        */

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == sendB.getId()) {
            // Variablen holen sich die neuen Werte (ansonsten werden nur die Werte eingefügt
            // die beim onCreate() übergeben wurden
            name = nameET.getText().toString();
            wohnort = wohnOrtET.getText().toString();

            editor.putString("name", name);
            editor.putString("wohnort", wohnort);
            editor.apply();

            // Toast = kleines Infofenster / leckeres Frühstück
            Toast.makeText(this, "Name: " + name + "\nWohnort: " + wohnort, Toast.LENGTH_SHORT).show();

            anzeigeTV.setText("Name: " + name + "\nWohnort: " + wohnort);
        }

        // Von "this" zu "MainActivity2.class" switchen
        // expliziter Intent
        if (v.getId() == switchB.getId()) {
            Intent intent = new Intent(this, MainActivity2.class);
            /*
            intent.putExtra("name", name);
            intent.putExtra("wohnort", wohnort);
             */
            editor.putString("name", name);
            editor.putString("wohnort", wohnort);
            editor.apply();
            startActivity(intent);
        }
    }
}