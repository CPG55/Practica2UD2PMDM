package carlosperez.pmdm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CaRLoS on 14/02/2018.
 */

public class BDHelper extends SQLiteOpenHelper {

    //    DEPORTE
    //    ID_ENCUENTRO EQUIPO1 EQUIPO2 RESULTADO1 RESULTADO2
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "encuentros";

    public static final String TABLA_RESULTADOS = "resultados";
    public static final String COLUMNA_ID_ENCUENTRO = "_id";
    public static final String COLUMNA_EQUIPO1 = "equipo1";
    public static final String COLUMNA_EQUIPO2 = "equipo2";
    public static final String COLUMNA_RESULTADO1 = "resultado1";
    public static final String COLUMNA_RESULTADO2 = "resultado2";

    private static final String SQL_CREATE = "create table "
            + TABLA_RESULTADOS + "(" + COLUMNA_ID_ENCUENTRO + " integer primary key autoincrement, " + COLUMNA_EQUIPO1 + " text not null, "
            + COLUMNA_EQUIPO2 + " text not null, " + COLUMNA_RESULTADO1 + " integer, " + COLUMNA_RESULTADO2 + " integer);";

    // Se sobreescribe el constructor para usar las constantes.
    public BDHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        // Creación de la base de datos.
        database.execSQL(SQL_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {

        // Si actulizaramos la version de SQLITE que utilizamos aqui podriamos  incluir el método que hace el upgrade.
        database.execSQL("DROP TABLE IF EXISTS resultados");


    }


}
