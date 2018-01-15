package carlosperez.tarea_pmdm02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;



/**
 * Created by CaRLoS on 21/11/2016.
 */

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Button miBotonVolver = (Button) (findViewById(R.id.boton_InfoVolver));

        miBotonVolver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }

        });
    }



}
