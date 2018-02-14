package carlosperez.pmdm;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by CaRLoS on 15/01/2018.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    // Campo de fecha.
    private EditText fechaSeleccionada;

    // Método que crea el diálogo con el calendario.
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Instancia del calendario.
        Calendar calendario = Calendar.getInstance();
        // Fecha por defecto.
        int añoActual = calendario.get(Calendar.YEAR);
        int mesActual = calendario.get(Calendar.MONTH);
        int diaActual = calendario.get(Calendar.DAY_OF_MONTH);

        // Crea instancia de DatePickerDialog para devolver la actividad.
        return new DatePickerDialog(getActivity(), this, añoActual, mesActual, diaActual);

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        fechaSeleccionada.setText(String.valueOf(calcularEdad(datePicker)));
    }

    public void setFecha(EditText editText) {

        this.fechaSeleccionada = editText;

    }

    public int calcularEdad(DatePicker fechaSeleccionada) {

        Calendar today = Calendar.getInstance();

        int diff_year = today.get(Calendar.YEAR) - fechaSeleccionada.getYear();
        int diff_month = today.get(Calendar.MONTH) - fechaSeleccionada.getMonth();
        int diff_day = today.get(Calendar.DAY_OF_MONTH) - fechaSeleccionada.getDayOfMonth();

        // Si está en ese año pero todavía no los ha cumplido
        if (diff_month < 0 || (diff_month == 0 && diff_day < 0)) {
            diff_year = diff_year - 1;
        }

        return diff_year;
    }
}





