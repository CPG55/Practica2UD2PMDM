package carlosperez.pmdm;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by CaRLoS on 31/01/2018.
 */

public class PreferenciasFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferencias);

    }

}





