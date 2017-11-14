package koukin.llapanmi;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Activity_Intro extends AppCompatActivity {

    private int tiempo = 10;
    int pStatus = 0;
    int cont = 0;
    private Handler handler = new Handler();
    ImageView fondo;
    ImageView bola1,bola2,bola3,bola4 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        fondo = (ImageView) findViewById(R.id.fondo);
        final List<ImageView> lista = new ArrayList();
        bola1 = (ImageView) findViewById(R.id.iv_punto1);
        bola2 = (ImageView) findViewById(R.id.iv_punto2);
        bola3 = (ImageView) findViewById(R.id.iv_punto3);
        bola4 = (ImageView) findViewById(R.id.iv_punto4);
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
                                        bola1.setImageResource(R.mipmap.punto4);
                                        bola2.setImageResource(R.mipmap.punto1);
                                        bola3.setImageResource(R.mipmap.punto2);
                                        bola4.setImageResource(R.mipmap.punto3);
                                        break;
                                    case 1:
                                        bola1.setImageResource(R.mipmap.punto3);
                                        bola2.setImageResource(R.mipmap.punto4);
                                        bola3.setImageResource(R.mipmap.punto1);
                                        bola4.setImageResource(R.mipmap.punto2);
                                        break;
                                    case 2:
                                        bola1.setImageResource(R.mipmap.punto2);
                                        bola2.setImageResource(R.mipmap.punto3);
                                        bola3.setImageResource(R.mipmap.punto4);
                                        bola4.setImageResource(R.mipmap.punto1);
                                        break;
                                    case 3:
                                        bola1.setImageResource(R.mipmap.punto1);
                                        bola2.setImageResource(R.mipmap.punto2);
                                        bola3.setImageResource(R.mipmap.punto3);
                                        bola4.setImageResource(R.mipmap.punto4);
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

                InputStream inputStream =null;

                try {
                    inputStream = Activity_Intro.this.openFileInput("user_data.cfg");
                    System.out.println("Succesful try!!!!!!!!!!!!");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                if(inputStream==null) {
                    Intent i = new Intent(getBaseContext(), Activity_Nuevo_Usuario.class);
                    startActivity(i);
                }
                else{
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent i = new Intent(getBaseContext(), Activity_Menu_Principal.class);
                    startActivity(i);
                }


                finish();




            }
        };
        thread.start();

    }

    private boolean isAssetExists(String pathInAssetsDir){
        AssetManager assetManager = this.getResources().getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(pathInAssetsDir);
            if(null != inputStream ) {
                inputStream.close();
                return true;
            }
        }  catch(IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
