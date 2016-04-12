package com.example.yoso.quotilius02;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Form extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        // Asignando escucha a los botones
        Button saveButton = (Button) findViewById(R.id.saveButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);

        saveButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.saveButton) {
            // Obtener los datos de los campos
            EditText quoteField = (EditText) findViewById(R.id.quoteField);
            EditText authorField = (EditText) findViewById(R.id.authorField);

            // Nuevo Intent con Extras
            Intent backData = new Intent();
            backData.putExtra("body", quoteField.getText().toString());
            backData.putExtra("author", authorField.getText().toString());

            // Enviar la información
            setResult(RESULT_OK, backData);

        } else {
            // El envío fracasó
            setResult(RESULT_CANCELED);
        }

        // Terminar la actividad Nueva_Frase
        finish();

    }
}
