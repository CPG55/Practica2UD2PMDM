package carlosperez.tarea_pmdm02;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    //Método para implementar menu.
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.mymenu, menu);

        return true;
    }

    //Método para implementar funcionalidad del menú.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            // Icono del action bar.
            case R.id.icono_Ayuda:
                Intent intent_Help = new Intent(getApplicationContext(), AyudaActivity.class);
                startActivity(intent_Help);
                return true;

            case R.id.MenuOpcion_Info:
                Intent intent_About = new Intent(this, InfoActivity.class);
                startActivity(intent_About);
                return true;

            case R.id.preferencias:
                Intent preferencias = new Intent("carlosperez.tarea_pmdm02.PreferenciasActivity_preferences");
                startActivity(preferencias);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // --------  RECUPERACION DE DATOS DE INTENTS CON RETORNO ---------------- //
    //Variables de clase.
    //Códigos de RESPUESTA de los INTENTS con retorno.
    protected static final int RE_CODE_REGISTRO = 1;
    protected static final int RE_CODE_APUESTA = 2;
    protected static final int RE_CODE_AJUSTES = 3;
    //Flags.
    boolean registrado = false;
    boolean apostado = false;
    // Auxiliares.
    String apuesta ;

    //Método para recuperar la información de la devolución de los intents.
    @Override
    protected void onActivityResult (int requestCode , int resultCode , Intent datos) {

        // Si se ha registrado con éxito, la comparación de códigos es verdadera y se lanza la tostada.
        if (requestCode == RE_CODE_REGISTRO && resultCode == RESULT_OK ) {
            String textoResultado = datos.getExtras().getString("resultado");
            //Lanzar tostada con el resultado.
            Toast.makeText( this , textoResultado, Toast.LENGTH_LONG).show();
            //Flag de registrado.
            registrado = true;
        }
        // Recuperar datos del deporte apostado.
        if (requestCode == RE_CODE_APUESTA && resultCode == RESULT_OK ) {
            String textoResultado = datos.getExtras().getString("resultado");
            //Lanzar tostada con el resultado.
            Toast.makeText( this ,getString(R.string.string_deporteApostado) + textoResultado, Toast.LENGTH_LONG).show();
            //Flag de registrado.
            apostado = true;
            apuesta = textoResultado;
        }
        // Recuperar datos de los ajustes sobre la apuesta.
        if (requestCode == RE_CODE_AJUSTES && resultCode == RESULT_OK ) {
            String textoResultado = datos.getExtras().getString("resultado");
            //Lanzar tostada con el resultado.
            Toast.makeText( this ,getString(R.string.string_apuesta) + textoResultado, Toast.LENGTH_LONG).show();

        }
    }
    // --------------  FIN DE RECUPERACION DE DATOS DE INTENTS CON RETORNO ---------------- //

    // -------------- METODOS ASOCIADOS A LAS PREFERENCIAS -------------------------------- //

    //botón, mostrar valores de preferencias
    public void onClickDisplay(View view) {
        // obtenemos una instancia SharedPreferences,
        // utilizando el formato <NombrePaquete>_preferences
        // OJO MUY IMPORTANTE => el nombre del paquete es en este ejemplo: com.pmdm.ud5_preferences
        // y a continuación se pone _preferences
        // por eso el nombre completo a cargar es: "com.pmdm.ud5_preferences_preferences"
        // MODE_PRIVATE: el archivo de preferencia solo lo puede abrir la aplicación que lo creó
        SharedPreferences appPrefs = getSharedPreferences("carlosperez.tarea_pmdm02.PreferenciasActivity_preferences", MODE_PRIVATE);

        //recupera una preferencia de cadena pasándole la clave y valor por defecto si no existe la preferencia
        tostada(appPrefs.getString("editTextPref", ""));
    }

    //botón, modificar valores de preferencias
    public void onClickModify(View view) {
        // obtenemos una instancia SharedPreferences
        SharedPreferences appPrefs = getSharedPreferences("carlosperez.tarea_pmdm02.PreferenciasActivity_preferences", MODE_PRIVATE);

        //obtiene un objeto Editor mediante edit() => preparado para cambiar las preferencias
        SharedPreferences.Editor prefsEditor = appPrefs.edit();

        // cambia el valor de la preferencia "editTextPref" mediante putString()
        // coge el valor del cuadro de texto txtString y se lo pone a esta preferencia
        prefsEditor.putString("editTextPref", ((EditText) findViewById(R.id.txtString)).getText().toString());

        //confirma los cambios
        prefsEditor.commit();

    }

    // muestra una tostada o mensaje de duración larga
    private void tostada(String str) {
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }

    // -------------- FIN METODOS ASOCIADOS A LAS PREFERENCIAS -------------------------------- //



    // ------- FUNCIONALIDAD ASOCIADA A LOS BOTONES DE LA ACTIVIDAD PRINCIPAL -----------  //.

    //Lanzar Pantalla de Registro.
    public void ejecutar_Registro(View vista) {
        //Intent que lanzará la actividad de registro.
        Intent intent = new Intent(this, RegistroActivity.class);

        //Lanzar intent con retorno de información.
        startActivityForResult(intent , RE_CODE_REGISTRO);
    }


    //Lanzar Pantalla de Apuestas, esperando resultados.
    public void ejecutar_Apuestas (View vista) {

        //Si no estas registrado no puedes lanzar las Apuestas.
        if (!registrado) {
            Toast.makeText( this , "Error no puedes apostar sino te has registrado", Toast.LENGTH_LONG).show();
        } else {
            Intent intencionApostar = new Intent(this, ApuestasActivity.class);
            //Lanzar intent con retorno de información.
            startActivityForResult(intencionApostar , RE_CODE_APUESTA);
        }
    }

    //Lanzar Pantalla de Ajustes, pasando la información del deporte al que se apuesta.
    public void ejecutar_Ajustes (View vista) {

        //Si no has seleccionado la apuesta no puedes lanzar Ajustes.
        if (!apostado) {
            Toast.makeText( this , "Error no puedes realizar ajustes sino has seleccionado el tipo de apuesta", Toast.LENGTH_LONG).show();
        } else {

            Intent intencionAjustes = new Intent(this, AjustesActivity.class);
            //Añadir información de apuesta en el intent.
            intencionAjustes.putExtra("apuesta" , apuesta);
            //Lanzar intent con retorno de información.
            startActivityForResult(intencionAjustes , RE_CODE_AJUSTES);
        }
    }

    // --------------------- FIN DE FUNCIONALIDAD DE LOS BOTONES ------------------------- //

}
