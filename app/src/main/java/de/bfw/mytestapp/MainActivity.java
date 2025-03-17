package de.bfw.mytestapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.uebung2_constraint);
        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        */

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

            // Toast = kleines Infofenster / leckeres Frühstück
            Toast.makeText(this, "Name: " + name + "\nWohnort: " + wohnort, Toast.LENGTH_SHORT).show();

            anzeigeTV.setText("Name: " + name + "\nWohnort: " + wohnort);
        }
    }
}