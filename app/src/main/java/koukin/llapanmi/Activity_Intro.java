package koukin.llapanmi;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class Activity_Intro extends AppCompatActivity {

    private int tiempo = 10;
    int pStatus = 0;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread thread = new Thread() {
            public void run() {
                while (pStatus < 300) {
                    pStatus += 1;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                    try {
                        sleep(tiempo);
                    } catch (Exception e) {

                    }
                }

                Intent i=new Intent(getBaseContext(),Activity_Nuevo_Usuario.class);
                startActivity(i);


                finish();




            }
        };
        thread.start();

    }
}
