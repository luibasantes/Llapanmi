package koukin.llapanmi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

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

        btn_listo = (Button) findViewById(R.id.btn_listo);
        input_nickname = (EditText) findViewById(R.id.input_nickname);
        image_hombre = (ImageView) findViewById(R.id.img_hombre);
        image_mujer = (ImageView) findViewById(R.id.img_mujer);


        image_hombre.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view) {

                image_hombre.setAlpha(80);
                image_mujer.setAlpha(255);
                sexo="H";
            }
        });
        image_mujer.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view) {
                image_hombre.setAlpha(255);
                image_mujer.setAlpha(80);
                sexo="M";
            }
        });

        btn_listo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(input_nickname.getText().length() > 0  && sexo!=""){
                    Intent i=new Intent(getBaseContext(), Activity_Menu_Principal.class);
                    i.putExtra("nickname",input_nickname.getText());
                    startActivity(i);
                    finish();
                }else{
                    input_nickname.setHint("Porfavor, ingresa tu nickname!!!!");
                }



            }
        });

    }
}
