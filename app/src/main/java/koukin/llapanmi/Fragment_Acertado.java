package koukin.llapanmi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.sql.SQLOutput;

/**
 * Created by Luigi on 06/11/2017.
 */
public class Fragment_Acertado extends android.support.v4.app.Fragment{
    private static final String TAG ="Fragment5";

    Button btn_continuar,btn_volver;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_preguntas_acertado,container,false);

        btn_continuar = (Button) view.findViewById(R.id.btn_continuar);
        btn_volver = (Button) view.findViewById(R.id.btn_salir);

        btn_continuar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Utils.acumPoints+=5;
                ((Activity_Preguntas)getActivity()).changeFragment(((Activity_Preguntas)getActivity()).getTema());

            }
        });

        btn_volver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Utils.acumPoints+=5;
                System.out.println("PUNTAJE ACUMULADO: "+Utils.acumPoints);
                ((Activity_Preguntas)getActivity()).vaciarRepetidas();
                Intent i=new Intent(getActivity(),Activity_Menu_Principal.class);
                i.putExtra("section",Utils.seccionActual);
                i.putExtra("puntaje",Utils.acumPoints);
                startActivity(i);
            }
        });

        return view;
    }
}
