package koukin.llapanmi;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__select__avatar);
        carousel= (CarouselPicker) findViewById(R.id.avatar_carousel);
        btnListo= (Button) findViewById(R.id.btn_avatarListo);
        Bundle bundle = getIntent().getExtras();
        nickname= bundle.getString("nickname");
        genero= bundle.getString("genero");

        final List<CarouselPicker.PickerItem> imageItems = new ArrayList<>();
        imageItems.add(new CarouselPicker.DrawableItem(R.mipmap.ic_launcher));
        imageItems.add(new CarouselPicker.DrawableItem(R.mipmap.ic_launcher_round));
        imageItems.add(new CarouselPicker.DrawableItem(R.mipmap.ic_launcher));
        imageItems.add(new CarouselPicker.DrawableItem(R.mipmap.ic_launcher));
        CarouselPicker.CarouselViewAdapter imageAdapter = new CarouselPicker.CarouselViewAdapter(this, imageItems, 0);
        carousel.setAdapter(imageAdapter);

        carousel.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
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
                guardarDatosUsuario(""+nickname + "|" +genero + "|" + avatar_seleccionado+"\n",Activity_Select_Avatar.this);
                Intent i = new Intent(getBaseContext(),Activity_Menu_Principal.class);
                i.putExtra("avatar",avatar_seleccionado);
                i.putExtra("nickname",nickname);
                i.putExtra("genero",genero);
                startActivity(i);

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