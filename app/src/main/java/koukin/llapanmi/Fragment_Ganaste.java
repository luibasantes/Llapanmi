package koukin.llapanmi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Luigi on 06/11/2017.
 */
public class Fragment_Ganaste extends android.support.v4.app.Fragment{
    private static final String TAG ="Fragment6";

    Button btn_volver;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_preguntas_ganaste,container,false);


        btn_volver = (Button) view.findViewById(R.id.btn_salir);

        btn_volver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i=new Intent(getActivity(),Activity_Menu_Principal.class);
                startActivity(i);
            }
        });

        return view;
    }
}
