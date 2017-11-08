package koukin.llapanmi;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Luigi on 21/10/2017.
 */

public class Activity_Menu_Principal extends AppCompatActivity {

    ImageView img_avatar;
    Button soundButton,btn_logica,btn_ciencias,btn_matematicas,btn_abstracto;
    TextView nickname;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        img_avatar = (ImageView) findViewById(R.id.img_avatar);
        soundButton= (Button) findViewById(R.id.soundButton);
        btn_logica= (Button) findViewById(R.id.btn_logica);
        btn_ciencias= (Button) findViewById(R.id.btn_ciencia);
        btn_abstracto = (Button) findViewById(R.id.btn_abstracto);
        btn_matematicas= (Button) findViewById(R.id.btn_matematicas);
        nickname= (TextView) findViewById(R.id.tv_nickname);

        Utils.setFont(this,nickname,"century-gotic.ttf");
        Utils.setFont(this,(TextView) findViewById(R.id.score1),"century-gotic.ttf");
        Utils.setFont(this,(TextView) findViewById(R.id.score2),"century-gotic.ttf");
        Utils.setFont(this,(TextView) findViewById(R.id.score3),"century-gotic.ttf");
        Utils.setFont(this,(TextView) findViewById(R.id.score4),"century-gotic.ttf");
        Utils.setFont(this,(TextView) findViewById(R.id.score_acertijo),"century-gotic.ttf");
        Utils.setFont(this,(TextView) findViewById(R.id.score_Ciencia),"century-gotic.ttf");
        Utils.setFont(this,(TextView) findViewById(R.id.score_Matematicas),"century-gotic.ttf");
        Utils.setFont(this,(TextView) findViewById(R.id.score_Logica),"century-gotic.ttf");

        try {
            loadUserData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btn_logica.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
            Intent i=new Intent(getBaseContext(),Activity_Preguntas.class);
            i.putExtra("Tipo",0);
            startActivity(i);
            }
        });
        btn_ciencias.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(),Activity_Preguntas.class);
                i.putExtra("Tipo",1);
                startActivity(i);
            }
        });
        btn_abstracto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(),Activity_Preguntas.class);
                i.putExtra("Tipo",2);
                startActivity(i);
            }
        });
        btn_matematicas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(),Activity_Preguntas.class);
                i.putExtra("Tipo",3);
                startActivity(i);
            }
        });
    }

    public void loadUserData() throws IOException {
        String line;
        InputStream inputStream = Activity_Menu_Principal.this.openFileInput("user_data.cfg");
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ((receiveString = bufferedReader.readLine()) != null) {
                stringBuilder.append(receiveString);
            }
            inputStream.close();

            line = stringBuilder.toString();
            System.out.println("MY DATA IS: "+line);
            String[] userData = line.split("\\|");
            System.out.println("SPLIT DATA: "+Arrays.toString(userData));
            nickname.setText(userData[0]);
            img_avatar.setImageResource(Integer.parseInt(userData[2]));

        }
    }
}
