package carlosperez.tarea_pmdm02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by CaRLoS on 13/11/2016.
 */

public class AjustesActivity extends Activity {

    //Variables de clase para spinner y campos de ajustes.
    private Spinner myspinner;
    private String dineroAjustado;
    private String numero1ajustado;
    private String numero2ajustado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        // ------------------  Configuración de las TABS. ---------------------- //
        //Referencia del contenedor TabHost del layout
        TabHost tabs = (TabHost) findViewById(android.R.id.tabhost);
        //Método para configurar tabs
        tabs.setup();

        //-----------------  PESTAÑA 1 -----------------------
        //Objeto de tipo TabSpec para la pestaña 1
        TabHost.TabSpec specification = tabs.newTabSpec("tab1");
        //Asignar identificador tab1 a la pestaña 1
        specification.setContent(R.id.tab1);
        //Establecer texto de la pestaña
        specification.setIndicator("DINERO");
        //Añadir pestaña
        tabs.addTab(specification);
        //-----------------  PESTAÑA 2 -----------------------
        //Objeto de tipo TabSpec para la pestaña 2
        specification = tabs.newTabSpec("tab2");
        //Asignar el identificador tab2 a la pestaña 2
        specification.setContent(R.id.tab2);
        //Establecer el texto
        specification.setIndicator("COMBINACION");
        //Añadir esta pestaña
        tabs.addTab(specification);
        // ---------------------------  FIN TABS. ----------------------------- //


        // ------------------  Configuración del Spinner. --------------------- //
        //Objeto spinner con referencia al layout
        myspinner = (Spinner) findViewById(R.id.spinner_ajustes_Tab1);

        //Datos para introducir al spinner.
        final String[] datos = new String[]{"1", "2", "5", "10"};

        //Creación de adaptador para utilizar el array en el spinner.
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //Tipo de estilo que se le asigna

        //Asignar adaptador al spinner
        myspinner.setAdapter(adaptador);
        // -------------------------  FIN del Spinner. -------------------------- //


        // RECUPERACION DE INFORMACION DEL INTENT.
        //Obtener informacion del deporte por el que se apuesta.
        String deporteApostado = "";
        Bundle bundle = getIntent().getExtras();
        deporteApostado = bundle.getString("apuesta");

        //Mostrar partido aleatorio en el texto de la segunda pestaña
        TextView texto_evento = (TextView) findViewById(R.id.textView_Ajustes_Tab2);
        String partido = partidoAleatorio(deporteApostado);
        texto_evento.setText(partido);


        //-------------------- LEER SPINNER -----------------------------------//

        myspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                dineroAjustado = parent.getItemAtPosition(position).toString() ;
                }

            public void onNothingSelected(AdapterView<?> parent) {
                dineroAjustado = "";
                }
        });

        //--------------------------------------------------------------------//
    }


    public void boton_volver(View vista) {
        //Destruir la actividad para volver a la anterior en la pila.
        finish();
    }

    public String partidoAleatorio(String deporteApostado) {

        // ------------------  Combinación de partidos. ----------------------- //

        final String partidosfutbol[] = {"Real Madrid - Barcelona", "At. Madrid - Valencia"};
        final String partidostenis[] = {"Nadal - Ferrer", "Songa - Djokovic"};
        final String partidosbaloncesto[] = {"Estudiantes - Barcelona", "Real Madrid - Joventut de Badalona"};
        final String partidosbalonmano[] = {"Naturhouse - Granoller", "Barcelona  - Bidasoa"};


        //Variables para almacenar resultados.
        int aleatorio;
        String partido = "";


        //Con el fin de que esto funcione en otros lenguajes, usaremos valores almacenados en string.xml
        String deporte1 = getResources().getString(R.string.checkbox_futbol);
        String deporte2 = getResources().getString(R.string.checkbox_tenis);
        String deporte3 = getResources().getString(R.string.checkbox_baloncesto);
        String deporte4 = getResources().getString(R.string.checkbox_balonmano);

        // Comparamos la cadena recibida con la equivalencia de los recursos
        //para determinar en que array de partidos escoger partido.
        if (deporteApostado.equals(deporte1)) {
            aleatorio = (int) (Math.random() * 2);
            partido = partidosfutbol[aleatorio];
        }
        if (deporteApostado.equals(deporte2)) {
            aleatorio = (int) (Math.random() * 2);
            partido = partidostenis[aleatorio];
        }
        if (deporteApostado.equals(deporte3)) {
            aleatorio = (int) (Math.random() * 2);
            partido = partidosbaloncesto[aleatorio];
        }
        if (deporteApostado.equals(deporte4)) {
            aleatorio = (int) (Math.random() * 2);
            partido = partidosbalonmano[aleatorio];
        }

        return partido;
    }



    public void boton_GuardarAjustes (View view) {

        //---------------------- LEER COMBINACION----------------------------//

        //Recuperar campo numero 1 de combinacion
        EditText numero1 = (EditText) findViewById(R.id.editText_ajustes_num1);
        numero1ajustado = (numero1.getText().toString());
        //Recuperar campo numero 2 de combinacion
        EditText numero2 = (EditText) findViewById(R.id.editText_ajustes_num2);
        numero2ajustado = (numero2.getText().toString());

        //--------------------------------------------------------------------//

        //Comprueba si los campos estan llenos.
        if (!TextUtils.isEmpty(numero1ajustado)  && !TextUtils.isEmpty(numero2ajustado)) {
            //Si estan llenos comprueba si el campo dinero no lo está. Normalmente lo va a estar, por tanto "false" y al else.
            if (TextUtils.isEmpty(dineroAjustado)) {
                Toast.makeText(this, "Elige tu Apuesta", Toast.LENGTH_LONG).show();

            } else {
                //Comprueba rango de la combinacion. Mayor que cero y menor de 300 en los dos.
                if (((Integer.parseInt(numero1ajustado) >= 0) && (Integer.parseInt(numero1ajustado) < 301)) && (((Integer.parseInt(numero2ajustado) >= 0) && (Integer.parseInt(numero2ajustado) < 301)))) {
                    //Mensaje de éxito en la validación.
                    Toast.makeText(this, "Acabas de hacer una apuesta", Toast.LENGTH_SHORT).show();
                    //Aqui se devuelven los datos en el intent.
                    Intent intent = new Intent();
                    intent.putExtra("resultado", "€: " + dineroAjustado + "\n" +
                            "Combinación 1: " + numero1ajustado + "\n" +
                            "Combinación 2: " + numero2ajustado);
                    setResult(RESULT_OK, intent);

                    //Destrucción de actividad.
                    destruirActividad();

                 } else {
                        Toast.makeText(this, "Error, cada combinación debe ser entre 0 y 300", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            Toast.makeText(this, "Elige tu combinación ganadora", Toast.LENGTH_LONG).show();
        }

    }

    //Este método ejecuta hilo de retraso en la destrucción de la actividad equivalente a la duración de la tostada.
    public void destruirActividad () {

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3500); // Retraso equivalente a LENGTH_LONG en la tostada.
                    AjustesActivity.this.finish(); //Destruir actividad.
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
