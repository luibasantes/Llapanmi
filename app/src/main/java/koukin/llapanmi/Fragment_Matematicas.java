package koukin.llapanmi;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Luigi on 06/11/2017.
 */

public class Fragment_Matematicas extends android.support.v4.app.Fragment{
    private static final String TAG ="Fragment4";
    ArrayList<Pregunta> preguntas;
    TextView pregunta;
    Button opcionA,opcionB,opcionC,opcionD,listo;
    private int respuesta_usuario , indice_pregunta;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_preguntas_matematicas_texto,container,false);
        preguntas=new ArrayList<>();
        pregunta = (TextView) view.findViewById(R.id.textoPregunta);
        opcionA = (Button) view.findViewById(R.id.opcion_A);
        opcionB = (Button) view.findViewById(R.id.opcion_B);
        opcionC = (Button) view.findViewById(R.id.opcion_C);
        opcionD = (Button) view.findViewById(R.id.opcion_D);
        listo = (Button) view.findViewById(R.id.btn_listo);
        respuesta_usuario =4;
        indice_pregunta=0;


        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(getActivity().getAssets().open("Matematicas1.csv"),"ISO-8859-1"), 8192);
            reader.readLine();
            String line;
            while((line=reader.readLine())!=null){
                String [] datosPregunta= line.split(";");
                Pregunta p = new Pregunta(datosPregunta[1],datosPregunta[2],datosPregunta[3],datosPregunta[4],datosPregunta[5],datosPregunta[1].trim());
                System.out.println("!!!PREGUNTA: "+line+"á é í ó ú ");
                preguntas.add(p);
            }
        } catch (IOException e) {
            e.printStackTrace() ;
        }

        Random r = new Random();
        boolean bandera=true;

        while (bandera){
            indice_pregunta= r.nextInt(preguntas.size());
            if(((Activity_Preguntas)getActivity()).tamanoRepetidas()+1 == preguntas.size()){
                bandera=false;
                ((Activity_Preguntas)getActivity()).vaciarRepetidas();
                ((Activity_Preguntas)getActivity()).changeFragment(6);
            }else{
                if(!((Activity_Preguntas)getActivity()).existeEnRepetidas(indice_pregunta)){
                    ((Activity_Preguntas)getActivity()).addRepetidas(indice_pregunta);
                    bandera=false;
                }
            }

        }


        int indiceR= r.nextInt(4);

        Utils.setFont(getActivity(),pregunta,"century-gotic.ttf");
        Utils.setFont(getActivity(),opcionA,"century-gotic.ttf");
        Utils.setFont(getActivity(),opcionB,"century-gotic.ttf");
        Utils.setFont(getActivity(),opcionC,"century-gotic.ttf");
        Utils.setFont(getActivity(),opcionD,"century-gotic.ttf");

        pregunta.setText(Html.fromHtml(preguntas.get(indice_pregunta).getPregunta()));


        if (indiceR==0){
            opcionA.setText(preguntas.get(indice_pregunta).getRespuesta());
            opcionB.setText(preguntas.get(indice_pregunta).getOpcionB());
            opcionC.setText(preguntas.get(indice_pregunta).getOpcionC());
            opcionD.setText(preguntas.get(indice_pregunta).getOpcionD());
        }else if(indiceR==1){
            opcionA.setText(preguntas.get(indice_pregunta).getOpcionB());
            opcionB.setText(preguntas.get(indice_pregunta).getRespuesta());
            opcionC.setText(preguntas.get(indice_pregunta).getOpcionC());
            opcionD.setText(preguntas.get(indice_pregunta).getOpcionD());
        }else if(indiceR==2){
            opcionA.setText(preguntas.get(indice_pregunta).getOpcionB());
            opcionB.setText(preguntas.get(indice_pregunta).getOpcionC());
            opcionC.setText(preguntas.get(indice_pregunta).getRespuesta());
            opcionD.setText(preguntas.get(indice_pregunta).getOpcionD());
        }else{
            opcionA.setText(preguntas.get(indice_pregunta).getOpcionB());
            opcionB.setText(preguntas.get(indice_pregunta).getOpcionC());
            opcionC.setText(preguntas.get(indice_pregunta).getOpcionD());
            opcionD.setText(preguntas.get(indice_pregunta).getRespuesta());
        }


        /*
        pregunta.setText(preguntas.get(indice_pregunta).getPregunta());
        opcionA.setText(preguntas.get(indice_pregunta).getRespuesta());
        opcionB.setText(preguntas.get(indice_pregunta).getOpcionB());
        opcionC.setText(preguntas.get(indice_pregunta).getOpcionC());
        opcionD.setText(preguntas.get(indice_pregunta).getOpcionD());
        */


        opcionA.setOnClickListener(buttonListener);
        opcionB.setOnClickListener(buttonListener);
        opcionC.setOnClickListener(buttonListener);
        opcionD.setOnClickListener(buttonListener);

        listo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(respuesta_usuario!=4){
                    if(respuesta_usuario==0){
                        if(opcionA.getText().equals(preguntas.get(indice_pregunta).getRespuesta())){
                            ((Activity_Preguntas)getActivity()).changeFragment(4);

                        }else{
                            ((Activity_Preguntas)getActivity()).changeFragment(5);
                            ((Activity_Preguntas)getActivity()).vaciarRepetidas();
                        }
                    }else if(respuesta_usuario==1){
                        if(opcionB.getText().equals(preguntas.get(indice_pregunta).getRespuesta())){
                            ((Activity_Preguntas)getActivity()).changeFragment(4);
                        }else{
                            ((Activity_Preguntas)getActivity()).changeFragment(5);
                            ((Activity_Preguntas)getActivity()).vaciarRepetidas();
                        }
                    }else if(respuesta_usuario==2){
                        if(opcionC.getText().equals(preguntas.get(indice_pregunta).getRespuesta())){
                            ((Activity_Preguntas)getActivity()).changeFragment(4);
                        }else{
                            ((Activity_Preguntas)getActivity()).changeFragment(5);
                            ((Activity_Preguntas)getActivity()).vaciarRepetidas();
                        }
                    }else if(respuesta_usuario==3){
                        if(opcionD.getText().equals(preguntas.get(indice_pregunta).getRespuesta())){
                            ((Activity_Preguntas)getActivity()).changeFragment(4);
                        }else{
                            ((Activity_Preguntas)getActivity()).changeFragment(5);
                            ((Activity_Preguntas)getActivity()).vaciarRepetidas();
                        }
                    }
                }
            }
        });

        return view;

    }


    public View.OnClickListener buttonListener= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.opcion_A:

                    opcionA.setBackgroundColor(Color.YELLOW);
                    opcionB.setBackgroundColor(Color.TRANSPARENT);
                    opcionC.setBackgroundColor(Color.TRANSPARENT);
                    opcionD.setBackgroundColor(Color.TRANSPARENT);
                    respuesta_usuario=0;
                    break;
                case R.id.opcion_B:
                    opcionB.setBackgroundColor(Color.YELLOW);
                    opcionA.setBackgroundColor(Color.TRANSPARENT);
                    opcionC.setBackgroundColor(Color.TRANSPARENT);
                    opcionD.setBackgroundColor(Color.TRANSPARENT);
                    respuesta_usuario=1;
                    break;
                case R.id.opcion_C:
                    opcionC.setBackgroundColor(Color.YELLOW);
                    opcionB.setBackgroundColor(Color.TRANSPARENT);
                    opcionA.setBackgroundColor(Color.TRANSPARENT);
                    opcionD.setBackgroundColor(Color.TRANSPARENT);
                    respuesta_usuario=2;
                    break;
                case R.id.opcion_D:
                    opcionD.setBackgroundColor(Color.YELLOW);
                    opcionB.setBackgroundColor(Color.TRANSPARENT);
                    opcionC.setBackgroundColor(Color.TRANSPARENT);
                    opcionA.setBackgroundColor(Color.TRANSPARENT);
                    respuesta_usuario=3;
                    break;
                default:
                    opcionA.setBackgroundColor(Color.TRANSPARENT);
                    opcionB.setBackgroundColor(Color.TRANSPARENT);
                    opcionC.setBackgroundColor(Color.TRANSPARENT);
                    opcionD.setBackgroundColor(Color.TRANSPARENT);
                    respuesta_usuario=4;
                    break;
            }
        }
    };
}
