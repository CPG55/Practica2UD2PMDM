package carlosperez.tarea_pmdm02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by CaRLoS on 11/11/2016.
 */

public class RegistroActivity  extends AppCompatActivity {

    String nombreRegistrado ;
    String emailRegistrado ;
    String edadRegistrada ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }


    //Se desencadena al pulsar el botón de validar el registro.
    public void validar_Datos(View vista) {

        //Variables de método para vistas de datos registrados.
        //Recuperar Campo nombre
        EditText textoNombre = (EditText) findViewById(R.id.editText_Nombre);
        nombreRegistrado = (textoNombre.getText().toString());
        //Recuperar Campo email
        EditText textoEmail = (EditText) findViewById(R.id.editText_Email);
        emailRegistrado = (textoEmail.getText().toString());
        //Recuperar Campo edad.
        EditText textoEdad = (EditText) findViewById(R.id.editText_Edad);
        edadRegistrada = (textoEdad.getText().toString());

        // Si es menor , no se registra, si faltan datos marca error , y si esta correcto sale mensaje de guay!
        //Comprobar datos.

        //Si los 3 campos estan llenos.
        if (!TextUtils.isEmpty(nombreRegistrado)  && !TextUtils.isEmpty(emailRegistrado) && !TextUtils.isEmpty(edadRegistrada) ) {

            //Si es menor no se registra.
            if (Integer.parseInt(edadRegistrada) < 18) {
                  Toast.makeText( this , "Un menor no puede registrarse en esta aplicación!!", Toast.LENGTH_LONG).show();

            //Por eliminación será mayor de edad, y se registra.
            } else {
                Toast.makeText( this , "Quedas registrado", Toast.LENGTH_SHORT).show();
                //Aqui se devuelven los datos en el intent.
                Intent intent = new Intent ();
                intent.putExtra("resultado" , "Nombre: " + nombreRegistrado + "\n" +
                                              "Email: "  +  emailRegistrado + "\n" +
                                              "Edad: "   +   edadRegistrada  );
                setResult(RESULT_OK , intent);

                //Hilo de delay y destrucción de actividad.
                thread.start();

            }

        } else {
           //Aquí debería ir un AlertDialog.
           Toast.makeText(getApplicationContext(), "NO TE PUEDO REGISTRAR", Toast.LENGTH_LONG).show();

        }

    }

    //Este hilo va a provocar un retraso en finalizar la actividad equivalente a la duración de la tostada.
    Thread thread = new Thread(){
        @Override
        public void run() {
            try {
                Thread.sleep(3500); // Retraso equivalente a LENGTH_LONG en la tostada.
                RegistroActivity.this.finish(); //Destruir actividad.
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    //Para volver a la anterior activity sin guardar.
    public void boton_volver (View vista) {
        //Destruir la actividad para volver a la anterior en la pila.
        finish();
    }


}