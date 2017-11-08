package koukin.llapanmi;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Luigi on 21/10/2017.
 */

public class Activity_Menu_Principal extends AppCompatActivity  implements MediaPlayer.OnPreparedListener{

    ImageView img_avatar;
    Button soundButton,btn_logica,btn_ciencias,btn_matematicas,btn_abstracto;
    TextView nickname,score1,score2,score3,score4;
    String gender;
    int avatarID;
    boolean firstInstance;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        System.out.println("SOUND VALUE: "+Utils.enabledSound);
        final MediaPlayer bsound = MediaPlayer.create(this, R.raw.buttons);
        final MediaPlayer bgsound = MediaPlayer.create(this, R.raw.background_music);
        img_avatar = (ImageView) findViewById(R.id.img_avatar);
        soundButton= (Button) findViewById(R.id.soundButton);
        btn_logica= (Button) findViewById(R.id.btn_logica);
        btn_ciencias= (Button) findViewById(R.id.btn_ciencia);
        btn_abstracto = (Button) findViewById(R.id.btn_abstracto);
        btn_matematicas= (Button) findViewById(R.id.btn_matematicas);
        nickname= (TextView) findViewById(R.id.tv_nickname);
        score1= (TextView)  findViewById(R.id.score1);
        score2= (TextView)  findViewById(R.id.score2);
        score3= (TextView)  findViewById(R.id.score3);
        score4= (TextView)  findViewById(R.id.score4);
        firstInstance= !Utils.enabledSound;

        Utils.setFont(this,nickname,"century-gotic.ttf");
        Utils.setFont(this,score1,"century-gotic.ttf");
        Utils.setFont(this,score2,"century-gotic.ttf");
        Utils.setFont(this,score3,"century-gotic.ttf");
        Utils.setFont(this,score4,"century-gotic.ttf");
        Utils.setFont(this,(TextView) findViewById(R.id.score_acertijo),"century-gotic.ttf");
        Utils.setFont(this,(TextView) findViewById(R.id.score_Ciencia),"century-gotic.ttf");
        Utils.setFont(this,(TextView) findViewById(R.id.score_Matematicas),"century-gotic.ttf");
        Utils.setFont(this,(TextView) findViewById(R.id.score_Logica),"century-gotic.ttf");

        try {
            loadUserData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(Utils.enabledSound) {
            bgsound.setLooping(true);
            bgsound.start();
        }

        Bundle bl = getIntent().getExtras();
        String seccion=bl.getString("section");
        int puntaje=bl.getInt("puntaje");
        System.out.println("THIS SECTION000: "+seccion);
        if(seccion!=null) {
            System.out.println("INGRESO A LA VERIFICACION!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            verificarPuntaje(seccion, puntaje);
        }
        btn_logica.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Utils.acumPoints=0;
                Utils.MaxScore=0;
                if(Utils.enabledSound){
                    bsound.start();
                    bgsound.stop();
                }
            Intent i=new Intent(getBaseContext(),Activity_Preguntas.class);
            i.putExtra("Tipo",0);
            startActivity(i);
            }
        });
        btn_ciencias.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Utils.acumPoints=0;
                Utils.MaxScore=0;
                if(Utils.enabledSound) {
                    bsound.start();
                    bgsound.stop();
                }
                Intent i=new Intent(getBaseContext(),Activity_Preguntas.class);
                i.putExtra("Tipo",1);
                startActivity(i);
            }
        });
        btn_abstracto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Utils.acumPoints=0;
                Utils.MaxScore=0;
                if(Utils.enabledSound) {
                    bsound.start();
                    bgsound.stop();
                }
                Intent i=new Intent(getBaseContext(),Activity_Preguntas.class);
                i.putExtra("Tipo",2);
                startActivity(i);
            }
        });
        btn_matematicas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Utils.acumPoints=0;
                Utils.MaxScore=0;
                if(Utils.enabledSound){
                    bsound.start();
                    bgsound.stop();
                }
                Intent i=new Intent(getBaseContext(),Activity_Preguntas.class);
                i.putExtra("Tipo",3);
                startActivity(i);
            }
        });

        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Utils.enabledSound){
                    soundButton.setBackgroundResource(R.mipmap.mute);
                    bgsound.stop();
                    bsound.stop();
                    Utils.enabledSound = !Utils.enabledSound;
                }
                else{
                        soundButton.setBackgroundResource(R.mipmap.sonido);
                    if(!firstInstance)
                        try {
                            bgsound.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    else{
                        firstInstance=false;
                    }
                    bgsound.start();
                    Utils.enabledSound = !Utils.enabledSound;
                }
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        String s=""+nickname.getText().toString()+"|"+gender+"|"+avatarID+"\n";
        s+= score1.getText().toString()+"|"+score2.getText().toString()+"|"+score3.getText().toString()+"|"+score4.getText().toString()+"\n";
        s+= Utils.enabledSound ? "true" : "false";
        s+= "\n";
        guardarDatosUsuario(s,this);
        System.out.println("SOUND VALUE: "+Utils.enabledSound);
    }

    public void loadUserData() throws IOException {
        String line;
        InputStream inputStream = Activity_Menu_Principal.this.openFileInput("user_data.cfg");
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            //LINEA DE DATOS USUARIO
            line = bufferedReader.readLine();
            System.out.println("MY DATA IS: "+line);
            String[] userData = line.trim().split("\\|");
            System.out.println("SPLIT DATA: "+Arrays.toString(userData));
            nickname.setText(userData[0]);
            gender = userData[1];
            avatarID= Integer.parseInt(userData[2]);
            img_avatar.setImageResource(Integer.parseInt(userData[2]));
            //LINEA DE PUNTAJES
            line= bufferedReader.readLine();
            System.out.println("MY DATA IS: "+line);
            userData = line.trim().split("\\|");
            score1.setText(userData[0]);
            score2.setText(userData[1]);
            score3.setText(userData[2]);
            score4.setText(userData[3]);
            //LINEA DE SONIDO
            line= bufferedReader.readLine();
            System.out.println("MY DATA IS: "+line);
            if(line.trim().equals("true")){
                Utils.enabledSound = true;
                soundButton.setBackgroundResource(R.mipmap.sonido);
            }
            else{
                soundButton.setBackgroundResource(R.mipmap.mute);
                Utils.enabledSound = false;
            }
            inputStream.close();



        }
    }

    @Override
    public void onPrepared(MediaPlayer player) {
        System.out.println("-----------------IS PLAYING!------------");
        player.start();
    }

    public void guardarDatosUsuario(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("user_data.cfg", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            System.out.println("File write failed: " + e.toString());
        }
    }

    public void verificarPuntaje(String seccion,int puntaje){
        int puntajeActual;
        AlertDialog.Builder mBuilder;
        Button ok;
        TextView tv_pop;
        View mView;
        final AlertDialog dialog;
        System.out.println("LA SECCION ESCOGIDA: "+seccion);
        switch(seccion){
            case "ciencias":
                puntajeActual= Integer.parseInt(score1.getText().toString());
                if(puntaje>puntajeActual){
                    score1.setText(""+puntaje);
                    mBuilder = new AlertDialog.Builder(this);
                    mView = getLayoutInflater().inflate(R.layout.pop_score, null);
                    tv_pop= (TextView) mView.findViewById(R.id.pop_tv);
                    tv_pop.setText(""+puntaje);
                    ok= (Button) mView.findViewById(R.id.ok_pop);
                    mBuilder.setView(mView);
                    dialog = mBuilder.create();
                    dialog.show();
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                dialog.dismiss();
                        }
                    });
                }
                break;
            case "mate":
                puntajeActual= Integer.parseInt(score1.getText().toString());
                if(puntaje>puntajeActual){
                    score2.setText(""+puntaje);
                    mBuilder = new AlertDialog.Builder(this);
                    mView = getLayoutInflater().inflate(R.layout.pop_score, null);
                    tv_pop= (TextView) mView.findViewById(R.id.pop_tv);
                    tv_pop.setText(""+puntaje);
                    ok= (Button) mView.findViewById(R.id.ok_pop);
                    mBuilder.setView(mView);
                    dialog = mBuilder.create();
                    dialog.show();
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
                break;
            case "acertijo":
                puntajeActual= Integer.parseInt(score1.getText().toString());
                if(puntaje>puntajeActual){
                    score3.setText(""+puntaje);
                    mBuilder = new AlertDialog.Builder(this);
                    mView = getLayoutInflater().inflate(R.layout.pop_score, null);
                    tv_pop= (TextView) mView.findViewById(R.id.pop_tv);
                    tv_pop.setText(""+puntaje);
                    ok= (Button) mView.findViewById(R.id.ok_pop);
                    mBuilder.setView(mView);
                    dialog = mBuilder.create();
                    dialog.show();
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
                break;
            case "logica":
                puntajeActual= Integer.parseInt(score1.getText().toString());
                if(puntaje>puntajeActual){
                    score4.setText(""+puntaje);
                    mBuilder = new AlertDialog.Builder(this);
                    mView = getLayoutInflater().inflate(R.layout.pop_score, null);
                    tv_pop= (TextView) mView.findViewById(R.id.pop_tv);
                    tv_pop.setText(""+puntaje);
                    ok= (Button) mView.findViewById(R.id.ok_pop);
                    mBuilder.setView(mView);
                    dialog = mBuilder.create();
                    dialog.show();
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
                break;
        }
    }
}
