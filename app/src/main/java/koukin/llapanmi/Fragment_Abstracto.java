package koukin.llapanmi;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Luigi on 06/11/2017.
 */

public class Fragment_Abstracto extends android.support.v4.app.Fragment{
    private static final String TAG ="Fragment3";
    ArrayList<Pregunta> preguntas;
    TextView pregunta;
    Button opcionA,opcionB,opcionC,opcionD;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_preguntas_abstracto_texto,container,false);
        preguntas=new ArrayList<>();
        pregunta = (TextView) view.findViewById(R.id.textoPregunta);
        opcionA = (Button) view.findViewById(R.id.opcion_A);
        opcionB = (Button) view.findViewById(R.id.opcion_B);
        opcionC = (Button) view.findViewById(R.id.opcion_C);
        opcionD = (Button) view.findViewById(R.id.opcion_D);




        opcionA.setOnClickListener(buttonListener);
        opcionB.setOnClickListener(buttonListener);
        opcionC.setOnClickListener(buttonListener);
        opcionD.setOnClickListener(buttonListener);
        return view;
    }


    public View.OnClickListener buttonListener= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.opcion_A:

                    opcionA.setBackgroundColor(Color.rgb(238,130,238));
                    opcionB.setBackgroundColor(Color.TRANSPARENT);
                    opcionC.setBackgroundColor(Color.TRANSPARENT);
                    opcionD.setBackgroundColor(Color.TRANSPARENT);
                    break;
                case R.id.opcion_B:
                    opcionB.setBackgroundColor(Color.rgb(238,130,238));
                    opcionA.setBackgroundColor(Color.TRANSPARENT);
                    opcionC.setBackgroundColor(Color.TRANSPARENT);
                    opcionD.setBackgroundColor(Color.TRANSPARENT);
                    break;
                case R.id.opcion_C:
                    opcionC.setBackgroundColor(Color.rgb(238,130,238));
                    opcionB.setBackgroundColor(Color.TRANSPARENT);
                    opcionA.setBackgroundColor(Color.TRANSPARENT);
                    opcionD.setBackgroundColor(Color.TRANSPARENT);
                    break;
                case R.id.opcion_D:
                    opcionD.setBackgroundColor(Color.rgb(238,130,238));
                    opcionB.setBackgroundColor(Color.TRANSPARENT);
                    opcionC.setBackgroundColor(Color.TRANSPARENT);
                    opcionA.setBackgroundColor(Color.TRANSPARENT);
                    break;
                default:
                    opcionA.setBackgroundColor(Color.TRANSPARENT);
                    opcionB.setBackgroundColor(Color.TRANSPARENT);
                    opcionC.setBackgroundColor(Color.TRANSPARENT);
                    opcionD.setBackgroundColor(Color.TRANSPARENT);
                    break;
            }
        }
    };
}