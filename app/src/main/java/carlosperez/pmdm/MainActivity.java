package carlosperez.pmdm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Variables de clase.
    //Códigos de RESPUESTA de los INTENTS con retorno.
    //protected static final int RE_CODE_PREFERENCIAS = 0;
    protected static final int RE_CODE_REGISTRO = 1;
    protected static final int RE_CODE_APUESTA = 2;
    protected static final int RE_CODE_AJUSTES = 3;

    //Flags.
    boolean registrado = false;
    boolean apostado = false;
    // Auxiliares.
    private String apuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Cambiar tema de la aplicacion segun la preferencia. Antes de llamar al super.
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean(("pref_theme"), false)) {
            setTheme(R.style.AppTheme_Dark);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        //cargarPreferencias();

    }

    //Método para implementar menu.
    @Override
    public boolean onCreateOptionsMenu(Menu miMenu) {
        getMenuInflater().inflate(R.menu.menu, miMenu);

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

            case R.id.menu_Info:
                Intent intent_About = new Intent(this, InfoActivity.class);
                startActivity(intent_About);
                return true;

            case R.id.menu_preferencias:
                Intent intent_preferencias = new Intent(this, SetPreferenciasActivity.class);
                startActivity(intent_preferencias);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // --------  RECUPERACION DE DATOS DE INTENTS CON RETORNO ---------------- //

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

        // Recuperar datos de las preferencias.
        //  if (requestCode == RE_CODE_PREFERENCIAS && resultCode == RESULT_OK ) {
        //cargarPreferencias();
        // }

    }
    // --------------  FIN DE RECUPERACION DE DATOS DE INTENTS CON RETORNO ---------------- //


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


    public void ejecutar_resultados(View vista) {


    }





    // --------------------- FIN DE FUNCIONALIDAD DE LOS BOTONES ------------------------- //

    // -------------- METODOS ASOCIADOS A LAS PREFERENCIAS // SIN UTILIDAD ACTUAL -------------------------------- //

    public void cargarPreferencias() {
        // Instancia SharedPreferences para cargar las preferencias.
        SharedPreferences misPreferencias = PreferenceManager.getDefaultSharedPreferences(this);

        //boolean my_checkbox_preference = mySharedPreferences.getBoolean("checkbox_preference", false);
        //prefCheckBox.setChecked(my_checkbox_preference);

        //String my_edittext_preference = mySharedPreferences.getString("edittext_preference", "");
        //prefEditText.setText(my_edittext_preference);

    }

    // muestra una tostada o mensaje de duración larga
    private void tostada(String str) {
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }

    // -------------- FIN METODOS ASOCIADOS A LAS PREFERENCIAS -------------------------------- //

}
