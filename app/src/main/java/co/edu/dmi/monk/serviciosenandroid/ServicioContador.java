package co.edu.dmi.monk.serviciosenandroid;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class ServicioContador extends Service {

    private final String TAG = "ServicioContador";
    private boolean running;
    private Thread proceso;
    private static int valor;

    @Override
    public IBinder onBind(Intent arg0) {
        return null; // TODO implementar binding para comunicar al cliente con el servicio
    }

    public static int getValor(){
        return valor;
    }

    @Override
    public void onCreate() {
        valor = 0;
        Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show();
        running = true;
        proceso = new Thread(new Runnable() {
            @Override
            public void run() {
                while(running) {
                    Log.d(TAG, "num: " + valor);
                    valor++;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        running = false;
                    }
                }
            }
        });
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Inicia el servicio
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        Log.d(TAG, "Service Started");
        if(proceso.getState() == Thread.State.NEW) {
            proceso.start();
        }else{
            valor = 0;
            proceso = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(running) {
                        Log.d(TAG, "num: " + valor);
                        valor++;
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            //e.printStackTrace();
                            running = false;
                        }
                    }
                }
            });
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        // Detiene el servicio
        super.onDestroy();
        proceso.interrupt();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
        Log.d(TAG, "Service Destroyed");
    }
}