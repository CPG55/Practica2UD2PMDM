package carlosperez.pmdm;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CaRLoS on 14/02/2018.
 */

public class BDHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "apuestas";
    public static final String DATABASE_TABLE_NAME = "resultados";

    // Campos de la tabla.
    public static final String COLUMNA_ID_ENCUENTRO = "id_encuentro";
    public static final String COLUMNA_EQUIPO1 = "equipo1";
    public static final String COLUMNA_EQUIPO2 = "equipo2";
    public static final String COLUMNA_RESULTADO1 = "resultado1";
    public static final String COLUMNA_RESULTADO2 = "resultado2";

    // Cadena que va a crear la base de datos según los campos dados.
    private static final String DATABASE_CREATE = "create table "
            + DATABASE_TABLE_NAME + "(" + COLUMNA_ID_ENCUENTRO + " integer primary key autoincrement , " + COLUMNA_EQUIPO1 + " text not null , "
            + COLUMNA_EQUIPO2 + " text not null , " + COLUMNA_RESULTADO1 + " text not null , " + COLUMNA_RESULTADO2 + " text not null);";

    // Se sobreescribe el constructor para usar las constantes.
    public BDHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        // Creación de la base de datos. Tratar excepciones.
        try {

            database.execSQL(DATABASE_CREATE);

        } catch (SQLException exceptionSQL) {

            exceptionSQL.printStackTrace();

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {

        try {
            // Si se actuliza la version de SQLITE aqui se hace el upgrade.
            database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);

            // Aqui se debería tratar "bien" el cambio de versión de la base de datos y copiar los datos para mantener las tablas antes de volver a crearla con la nueva version.

            // Crear de nuevo la BD.
            onCreate(database);

        } catch (SQLException exceptionSQL) {

            exceptionSQL.printStackTrace();

        }
    }
}
