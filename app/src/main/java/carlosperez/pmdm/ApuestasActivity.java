package carlosperez.pmdm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Created by CaRLoS on 13/11/2016.
 */

public class ApuestasActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_apuestas);

    }

    //Método para el botón Volver.
    public void boton_volver (View vista) {
        //Destruir la actividad para volver a la anterior en la pila.
        finish();
    }

    //Contador de clicks de aceptar apuesta.
    int clicks = 0;

    //Método para el botón Aceptar Apuesta.
    public void boton_aceptarApuesta (View vista) {

        //Objetos checkbox del interface.
        CheckBox futbol = findViewById(R.id.checkBox_Futbol);
        CheckBox tenis = findViewById(R.id.checkBox_Tenis);
        CheckBox baloncesto = findViewById(R.id.checkBox_Baloncesto);
        CheckBox balonmano = findViewById(R.id.checkBox_Balonmano);


        //Array de CheckBox
        CheckBox apuestas [] = { futbol , tenis , baloncesto  , balonmano } ;

        //Contador de apuestas marcadas.
        int apuestasMarcadas = 0;
        //Ultima apuesta marcada
        String apuesta = null ;
        //Clicks del boton aceptar
        clicks++;

        //Este bucle comprueba el numero de checkbox marcados.
        for (int i=0 ; i<4 ; i++) {
            //Si está marcado aumenta el contador y guarda la cadena de texto del checkbox.
            if (apuestas[i].isChecked()) {
                apuesta = apuestas[i].getText().toString();
                apuestasMarcadas++;
            }
        }

        //Ninguna selección de apuesta.
        if (apuestasMarcadas<1 && clicks<2) {
            Toast.makeText(this, "No has seleccionado ninguna apuesta", Toast.LENGTH_LONG).show();
            //aux++;
        }

        if (apuestasMarcadas<1 && clicks>1) {
            Toast.makeText(this, "Sigues sin seleccionar nada. Vale ya", Toast.LENGTH_LONG).show();
        }


        //Más de una apuesta seleccionada.
        if (apuestasMarcadas>1) {
            //Tostada de más de una apuesta seleccionada
            Toast.makeText(this, "La versión Lite solo admite un tipo de apuesta. Compra nuestra versión de Pago", Toast.LENGTH_LONG).show();
        }

        //Seleccion de una sola apuesta, seleccion correcta para el intent de devolución.
        if (apuestasMarcadas==1) {
            Toast.makeText(this, "Apuesta guardada", Toast.LENGTH_LONG).show();

            //Devolución de la apuesta seleccionada en el intent.
            Intent intent = new Intent ();
            intent.putExtra("resultado" , apuesta );
            setResult(RESULT_OK , intent);

            //Hilo de delay y destrucción de actividad.
            thread.start();

        }
    }


    //Este hilo va a provocar un retraso en finalizar la actividad equivalente a la duración de la tostada.
    Thread thread = new Thread(){
        @Override
        public void run() {
            try {
                Thread.sleep(3500); // Retraso equivalente a LENGTH_LONG en la tostada.
                ApuestasActivity.this.finish(); //Destruir actividad.
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

}
