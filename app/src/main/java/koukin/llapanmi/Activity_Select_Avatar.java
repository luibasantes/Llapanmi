package koukin.llapanmi;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import in.goodiebag.carouselpicker.CarouselPicker;

public class Activity_Select_Avatar extends AppCompatActivity {

    CarouselPicker carousel;
    Button btnListo;
    int avatar_seleccionado;
    String nickname, genero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final MediaPlayer bsound = MediaPlayer.create(this, R.raw.buttons);
        final MediaPlayer crsound = MediaPlayer.create(this, R.raw.blip);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__select__avatar);
        carousel= (CarouselPicker) findViewById(R.id.avatar_carousel);
        btnListo= (Button) findViewById(R.id.btn_avatarListo);
        Bundle bundle = getIntent().getExtras();
        nickname= bundle.getString("nickname");
        genero= bundle.getString("genero");

        Utils.setFont(this,(TextView)findViewById(R.id.avatar_label),"century-gotic.ttf");
        final List<CarouselPicker.PickerItem> imageItems = new ArrayList<>();
        imageItems.add(new CarouselPicker.DrawableItem(R.mipmap.avatar_h1));
        imageItems.add(new CarouselPicker.DrawableItem(R.mipmap.avatar_h2));
        imageItems.add(new CarouselPicker.DrawableItem(R.mipmap.avatar_h3));
        imageItems.add(new CarouselPicker.DrawableItem(R.mipmap.avatar_m1));
        imageItems.add(new CarouselPicker.DrawableItem(R.mipmap.avatar_m2));
        imageItems.add(new CarouselPicker.DrawableItem(R.mipmap.avatar_m3));
        avatar_seleccionado=R.mipmap.avatar_h1;


        CarouselPicker.CarouselViewAdapter imageAdapter = new CarouselPicker.CarouselViewAdapter(this, imageItems, 0);
        carousel.setAdapter(imageAdapter);

        carousel.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                crsound.start();
                if(imageItems.get(position).hasDrawable()){
                    avatar_seleccionado = imageItems.get(position).getDrawable();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnListo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bsound.start();
                guardarDatosUsuario(""+nickname + "|" +genero + "|" + avatar_seleccionado+"\n",Activity_Select_Avatar.this);
                Intent i = new Intent(getBaseContext(),Activity_Menu_Principal.class);
                i.putExtra("avatar",avatar_seleccionado);
                i.putExtra("nickname",nickname);
                i.putExtra("genero",genero);
                startActivity(i);
                finish();

            }
        });
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
}
