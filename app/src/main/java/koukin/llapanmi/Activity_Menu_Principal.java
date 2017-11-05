package koukin.llapanmi;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    Button soundButton;
    TextView nickname;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        img_avatar = (ImageView) findViewById(R.id.img_avatar);
        soundButton= (Button) findViewById(R.id.soundButton);
        nickname= (TextView) findViewById(R.id.tv_nickname);

        try {
            loadUserData();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
