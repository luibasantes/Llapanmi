package koukin.llapanmi;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Luigi on 21/10/2017.
 */


public class Activity_Nuevo_Usuario extends AppCompatActivity {

    Button btn_listo;
    EditText input_nickname;
    ImageView image_mujer,image_hombre;
    String sexo = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);

        final MediaPlayer bsound = MediaPlayer.create(this, R.raw.buttons);
        final MediaPlayer esound = MediaPlayer.create(this, R.raw.error);
        final MediaPlayer bpsound = MediaPlayer.create(this, R.raw.blip);

        btn_listo = (Button) findViewById(R.id.btn_listo);
        input_nickname = (EditText) findViewById(R.id.input_nickname);
        image_hombre = (ImageView) findViewById(R.id.img_hombre);
        image_mujer = (ImageView) findViewById(R.id.img_mujer);
        image_hombre.setAlpha(80);
        image_mujer.setAlpha(80);

        image_hombre.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view) {
                bpsound.start();
                image_hombre.setAlpha(255);
                image_mujer.setAlpha(80);
                sexo="H";
            }
        });
        image_mujer.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view) {
                bpsound.start();
                image_hombre.setAlpha(80);
                image_mujer.setAlpha(255);
                sexo="M";
            }
        });

        btn_listo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(input_nickname.getText().length() > 0  && sexo!=""){
                    bsound.start();
                    Intent i=new Intent(getBaseContext(),Activity_Select_Avatar.class);
                    i.putExtra("nickname",input_nickname.getText().toString());
                    i.putExtra("genero",sexo);
                    startActivity(i);
                    finish();
                }else{
                    esound.start();
                    input_nickname.setHint("Porfavor, ingresa tu nickname!!!!");
                }



            }
        });

    }
}
