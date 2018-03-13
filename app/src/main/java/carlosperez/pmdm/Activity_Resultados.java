package carlosperez.pmdm;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by CaRLoS on 14/02/2018.
 * En caso de estar haciendo consultas grandes y no ser solamente un ejemplo, debería implementarse AsyncTask para evitar colapsar la app al usar SQLITE.
 */

public class Activity_Resultados extends AppCompatActivity {

    // Datos que se van a recuperar del intent para mostrar en la interfaz.
    private String datos_Deporte;
    private String[] todasLasColumnas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_resultados);

        // Vistas a rellenar del interfaz de la actividad.
        TextView deporte = findViewById(R.id.textView_deporte);
        TextView id_encuentro = findViewById(R.id.textView_id_encuentro);
        TextView equipo1 = findViewById(R.id.textView_equipo1);
        TextView equipo2 = findViewById(R.id.textView_equipo2);
        TextView resultado1 = findViewById(R.id.textView_resultado1);
        TextView resultado2 = findViewById(R.id.textView_resultado2);

        // RECUPERACION DE INFORMACION DEL INTENT.
        // Datos que se van a incluir en la base de datos.
        Bundle bundle = getIntent().getExtras();
        datos_Deporte = bundle.getString("deporte");

        // Cabecera de la tabla de la actividad.
        deporte.setText(datos_Deporte);

        try {
            // Abrir base de datos.
            BDHelper bdHelper = new BDHelper(this);
            SQLiteDatabase database = bdHelper.getWritableDatabase();

            // Inicializar un array con los campos de la tabla.
            todasLasColumnas = new String[]{BDHelper.COLUMNA_ID_ENCUENTRO, BDHelper.COLUMNA_EQUIPO1, BDHelper.COLUMNA_EQUIPO2,
                    BDHelper.COLUMNA_RESULTADO1, BDHelper.COLUMNA_RESULTADO2};

            // Solamente se va a buscar la fila 1 para mostrar en pantalla al abrir la actividad. Se almacena en el objeto miCursor
            Cursor miCursor = database.query(true, BDHelper.DATABASE_TABLE_NAME, todasLasColumnas,
                    BDHelper.COLUMNA_ID_ENCUENTRO + "=" + 1, null, null, null, null, null);

            // si hay datos devueltos, apunta al principio y lee la primera fila de datos para rellenar la tabla.
            if (miCursor != null) {
                miCursor.moveToFirst();
                id_encuentro.setText(miCursor.getString(0));
                equipo1.setText(miCursor.getString(1));
                equipo2.setText(miCursor.getString(2));
                resultado1.setText(miCursor.getString(3));
                resultado2.setText(miCursor.getString(4));

            }

        } catch (SQLException excepcionSQL) {

            excepcionSQL.printStackTrace();

        }


    }


    //Método para el botón Volver.
    public void boton_volver(View vista) {
        //Destruir la actividad para volver a la anterior en la pila.
        finish();
    }


}
