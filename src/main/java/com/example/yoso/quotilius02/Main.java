package com.example.yoso.quotilius02;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Main extends AppCompatActivity {

    // Código de envío para el intent
    public final static int ADD_REQUEST_CODE = 1;

    // Atributos para datos
    private QuotesDataSource dataSource;
    private SimpleCursorAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Crear nuevo objeto QuotesDataSource
        dataSource = new QuotesDataSource(this);

        // Instanciamos en nuestra Activity simplemente tendremos un ListView llamado listview

        ListView listView = (ListView) findViewById(R.id.listView);

        // Iniciando el nuevo Adaptador
        adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.two_line_list_item,
                dataSource.getAllQuotes(),
                new String[]{QuotesDataSource.ColumnQuotes.BODY_QUOTES,
                        QuotesDataSource.ColumnQuotes.AUTHOR_QUOTES},
                new int[]{android.R.id.text1, android.R.id.text2},
               SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER

        );

        // Por último enlazamos el SimpleCursorAdapter al ListView con el método setAdapter.

        listView.setAdapter(adapter);


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
        if (id == R.id.action_add) {
            //Iniciando la actividad Form
            initForm();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Método onActivityResult() y realiza las operaciones necesarias para guardar los datos
    // en la base de datos
    @Override
    protected void onActivityResult(int re1questCode, int resultCode, Intent data){
        super.onActivityResult(re1questCode, resultCode, data);

        if ( re1questCode == ADD_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                // Insertar registro en la base de datos del formulario
                String body = data.getStringExtra("body");
                String author = data.getStringExtra("author");

                dataSource.saveQuotesRow(body,author);
                //Refrescando la lista manualmente
                adapter.changeCursor(dataSource.getAllQuotes());
            }
        }
    }

    //Método para el intent en accion bar
    private void initForm(){
        // Iniciando la actividad Form
        Intent intent = new Intent(this, Form.class);

        // Iniciar el inten
        startActivityForResult(intent, ADD_REQUEST_CODE);
    }
}
