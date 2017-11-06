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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Luigi on 06/11/2017.
 */

public class Fragment_Ciencias extends android.support.v4.app.Fragment{
    private static final String TAG ="Fragment2";
    ArrayList<Pregunta> preguntas;
    TextView pregunta;
    Button opcionA,opcionB,opcionC,opcionD;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preguntas_ciencia_texto,container,false);
        preguntas=new  ArrayList<>();
        pregunta = (TextView) view.findViewById(R.id.textoPregunta);
        opcionA = (Button) view.findViewById(R.id.opcion_A);
        opcionB = (Button) view.findViewById(R.id.opcion_B);
        opcionC = (Button) view.findViewById(R.id.opcion_C);
        opcionD = (Button) view.findViewById(R.id.opcion_D);

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getActivity().getAssets().open("Ciencias.csv")));
            reader.readLine();
            String line;
            while((line=reader.readLine())!=null){
                String [] datosPregunta= line.split(";");
                Pregunta p = new Pregunta(datosPregunta[1],datosPregunta[2],datosPregunta[3],datosPregunta[4],datosPregunta[5],datosPregunta[1].trim());
                preguntas.add(p);
            }
        } catch (IOException e) {
            e.printStackTrace() ;
        }

        pregunta.setText(preguntas.get(0).getPregunta());
        opcionA.setText(preguntas.get(0).getRespuesta());
        opcionB.setText(preguntas.get(0).getOpcionB());
        opcionC.setText(preguntas.get(0).getOpcionC());
        opcionD.setText(preguntas.get(0).getOpcionD());

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
                   opcionA.setBackgroundResource(R.mipmap.selectgreen);
                   opcionB.setBackgroundColor(Color.TRANSPARENT);
                   opcionC.setBackgroundColor(Color.TRANSPARENT);
                   opcionD.setBackgroundColor(Color.TRANSPARENT);
                   break;
               case R.id.opcion_B:
                   opcionB.setBackgroundResource(R.mipmap.selectgreen);
                   opcionA.setBackgroundColor(Color.TRANSPARENT);
                   opcionC.setBackgroundColor(Color.TRANSPARENT);
                   opcionD.setBackgroundColor(Color.TRANSPARENT);
                   break;
               case R.id.opcion_C:
                   opcionC.setBackgroundResource(R.mipmap.selectgreen);
                   opcionB.setBackgroundColor(Color.TRANSPARENT);
                   opcionA.setBackgroundColor(Color.TRANSPARENT);
                   opcionD.setBackgroundColor(Color.TRANSPARENT);
                   break;
               case R.id.opcion_D:
                   opcionD.setBackgroundResource(R.mipmap.selectgreen);
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