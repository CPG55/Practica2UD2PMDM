package carlosperez.tarea_pmdm02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

            case R.id.MenuOpcion_Ayuda:
                Intent intent = new Intent (this , AyudaActivity.class );
                startActivity(intent);
                return true;

            case R.id.MenuOpcion_Info:
                Intent intent2 = new Intent (this , InfoActivity.class );
                startActivity(intent2);
                return true;

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

        //Si se ha registrado con éxito, la comparación de códigos es verdadera y se lanza la tostada.
        if (requestCode == RE_CODE_REGISTRO && resultCode == RESULT_OK ) {
            String textoResultado = datos.getExtras().getString("resultado");
            //Lanzar tostada con el resultado.
            Toast.makeText( this , textoResultado, Toast.LENGTH_LONG).show();
            //Flag de registrado.
            registrado = true;
        }
        //Recuperar datos del deporte apostado.
        if (requestCode == RE_CODE_APUESTA && resultCode == RESULT_OK ) {
            String textoResultado = datos.getExtras().getString("resultado");
            //Lanzar tostada con el resultado.
            Toast.makeText( this ,getString(R.string.string_deporteApostado) + textoResultado, Toast.LENGTH_LONG).show();
            //Flag de registrado.
            apostado = true;
            apuesta = textoResultado;
        }
        //Recuperar datos de los ajustes sobre la apuesta.
        if (requestCode == RE_CODE_AJUSTES && resultCode == RESULT_OK ) {
            String textoResultado = datos.getExtras().getString("resultado");
            //Lanzar tostada con el resultado.
            Toast.makeText( this ,getString(R.string.string_apuesta) + textoResultado, Toast.LENGTH_LONG).show();

        }
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

    // --------------------- FIN DE FUNCIONALIDAD DE LOS BOTONES ------------------------- //

}
