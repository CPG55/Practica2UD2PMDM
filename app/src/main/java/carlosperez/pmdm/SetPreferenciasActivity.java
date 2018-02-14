package carlosperez.pmdm;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by CaRLoS on 09/02/2018.
 */

public class SetPreferenciasActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new PreferenciasFragment()).commit();
    }

}