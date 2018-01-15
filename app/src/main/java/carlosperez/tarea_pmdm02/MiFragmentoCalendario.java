package carlosperez.tarea_pmdm02;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

/**
 * Created by CaRLoS on 15/01/2018.
 */

public class MiFragmentoCalendario extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private static final String SHOW_ERROR_MESSAGE = "No eres mayor de edad.";


    // Método que crea el diálogo con el calendario.
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Obtiene la fecha actual por defecto.
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Crear instancia de DatePickerDialog y devolverla.
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }


    // Método que selecciona la fecha cada vez que se modifica el datepicker.
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {

        // Instancia de calendario.
        Calendar userAge = new GregorianCalendar(year, month, day);
        Calendar minAdultAge = new GregorianCalendar();

        minAdultAge.add(Calendar.YEAR, -18);

        if (minAdultAge.before(userAge)) {
            // String showErrorMessage = SHOW_ERROR_MESSAGE;
        }
    }

}





