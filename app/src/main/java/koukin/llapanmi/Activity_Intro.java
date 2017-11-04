package koukin.llapanmi;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Activity_Intro extends AppCompatActivity {

    private int tiempo = 10;
    int pStatus = 0;
    int cont = 0;
    private Handler handler = new Handler();
    ImageView fondo;
    Button bola1,bola2,bola3,bola4 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        fondo = (ImageView) findViewById(R.id.fondo);
        final List<Button> lista = new ArrayList();
        bola1 = (Button) findViewById(R.id.bola1);
        bola2 = (Button) findViewById(R.id.bola2);
        bola3 = (Button) findViewById(R.id.bola3);
        bola4 = (Button) findViewById(R.id.bola4);
        lista.add(bola1);
        lista.add(bola2);
        lista.add(bola3);
        lista.add(bola4);

        final Thread thread = new Thread() {
            public void run() {
                while (pStatus < 600) {
                    pStatus += 1;
                    /*if (pStatus == 300){
                        fondo.setBackgroundResource(R.mipmap.logojuego);
                    }*/

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(pStatus%50==0){
                                switch (cont % 4) {
                                    case 0:
                                        bola1.setBackgroundResource(R.mipmap.punto4);
                                        bola2.setBackgroundResource(R.mipmap.punto1);
                                        bola3.setBackgroundResource(R.mipmap.punto2);
                                        bola4.setBackgroundResource(R.mipmap.punto3);
                                        break;
                                    case 1:
                                        bola1.setBackgroundResource(R.mipmap.punto3);
                                        bola2.setBackgroundResource(R.mipmap.punto4);
                                        bola3.setBackgroundResource(R.mipmap.punto1);
                                        bola4.setBackgroundResource(R.mipmap.punto2);
                                        break;
                                    case 2:
                                        bola1.setBackgroundResource(R.mipmap.punto2);
                                        bola2.setBackgroundResource(R.mipmap.punto3);
                                        bola3.setBackgroundResource(R.mipmap.punto4);
                                        bola4.setBackgroundResource(R.mipmap.punto1);
                                        break;
                                    case 3:
                                        bola1.setBackgroundResource(R.mipmap.punto1);
                                        bola2.setBackgroundResource(R.mipmap.punto2);
                                        bola3.setBackgroundResource(R.mipmap.punto3);
                                        bola4.setBackgroundResource(R.mipmap.punto4);
                                        break;

                                    default:
                                        break;
                                }
                                cont++;
                            }

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
