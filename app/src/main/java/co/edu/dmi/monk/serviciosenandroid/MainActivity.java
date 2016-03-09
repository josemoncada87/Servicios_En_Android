package co.edu.dmi.monk.serviciosenandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*Test commit*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button iniciar = (Button) findViewById(R.id.btn_init_service);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(v);
            }
        });

        Button detener = (Button) findViewById(R.id.btn_stop_service);
        detener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(v);
            }
        });

        Button solicitar = (Button) findViewById(R.id.btn_getValue_service);
        solicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valor = ""+ ServicioContador.getValor();
                TextView tv = (TextView) findViewById(R.id.tv_last_value_service);
                tv.setText(valor);
            }
        });
    }

    // Metodo para iniciar el servicio
    public void startService(View view) {
        startService(new Intent(getBaseContext(), ServicioContador.class));
    }

    // Metodo para detener el servicio
    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), ServicioContador.class));
    }
}
