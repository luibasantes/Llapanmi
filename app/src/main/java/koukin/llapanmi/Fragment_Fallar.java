package koukin.llapanmi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Luigi on 06/11/2017.
 */

public class Fragment_Fallar extends android.support.v4.app.Fragment{
    private static final String TAG ="Fragment5";

    Button btn_continuar,btn_volver;
    TextView res_title,exp_title,txt_respuesta,txt_explicacion;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_preguntas_fallado,container,false);
        btn_continuar = (Button) view.findViewById(R.id.btn_continuar);
        btn_volver = (Button) view.findViewById(R.id.btn_salir);
        res_title= (TextView) view.findViewById(R.id.respuesta_title);
        exp_title= (TextView) view.findViewById(R.id.explicacion_title);
        txt_respuesta= (TextView) view.findViewById(R.id.text_respuesta);
        txt_explicacion= (TextView) view.findViewById(R.id.text_explicacion);

        Utils.setFont(getActivity(),res_title,"adventpro-bold.ttf");
        Utils.setFont(getActivity(),exp_title,"adventpro-bold.ttf");
        Utils.setFont(getActivity(),txt_respuesta,"century-gotic.ttf");
        Utils.setFont(getActivity(),txt_explicacion,"century-gotic.ttf");

        txt_explicacion.setText(Utils.explicacion);
        txt_respuesta.setText(Utils.respuesta);



        btn_continuar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ((Activity_Preguntas)getActivity()).changeFragment(((Activity_Preguntas)getActivity()).getTema());
                ((Activity_Preguntas)getActivity()).vaciarRepetidas();
                Utils.MaxScore= 0;
                Utils.acumPoints=0;
            }
        });

        btn_volver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Utils.MaxScore= Utils.acumPoints;
                Utils.acumPoints=0;
                ((Activity_Preguntas)getActivity()).vaciarRepetidas();
                Intent i=new Intent(getActivity(),Activity_Menu_Principal.class);
                i.putExtra("section",Utils.seccionActual);
                i.putExtra("puntaje",Utils.MaxScore);
                startActivity(i);
            }
        });



        return view;
    }
}
