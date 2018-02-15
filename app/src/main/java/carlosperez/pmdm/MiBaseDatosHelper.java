package carlosperez.pmdm;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CaRLoS on 14/02/2018.
 */

public class MiBaseDatosHelper extends SQLiteOpenHelper {


    public MiBaseDatosHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MiBaseDatosHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //  sqLiteDatabase.execSQL(“CREATE TABLE miTabla (campo1 INTEGER, campo2 TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        //Se elimina la versión anterior de la tabla
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS miTabla");
        //Se crea la nueva versión de la tabla
//      sqLiteDatabase.execSQL(“CREATE TABLE miTabla (campo1 INTEGER, campo2 TEXT)”);


    }


}
