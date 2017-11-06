package koukin.llapanmi;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Luigi on 06/11/2017.
 */

public class Fragment_Logica extends android.support.v4.app.Fragment{
    private static final String TAG ="Fragment1";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_preguntas_logica_texto,container,false);
        return view;
    }
}
