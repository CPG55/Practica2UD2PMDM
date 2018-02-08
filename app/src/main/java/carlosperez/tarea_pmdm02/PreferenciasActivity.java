package carlosperez.tarea_pmdm02;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by CaRLoS on 31/01/2018.
 */

public class PreferenciasActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.layout.activity_preferencias);

    }

}





