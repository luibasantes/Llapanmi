package koukin.llapanmi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

/**
 * Created by Luigi on 06/11/2017.
 */

public class Activity_Preguntas extends AppCompatActivity {

    private SectionsStatePageAdapter mSectionsStatePageAdapter;
    private CustomViewPager mViewPager;
    private LinearLayout linearlayout1;
    private int tema;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);
        mSectionsStatePageAdapter = new SectionsStatePageAdapter(getSupportFragmentManager());
        mViewPager = (CustomViewPager) findViewById(R.id.container);
        linearlayout1 = (LinearLayout) findViewById(R.id.linearLayout1);

        setupViewPager(mViewPager);



        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            tema = extras.getInt("Tipo");
            mViewPager.setCurrentItem(tema);
            if(tema ==0){
                linearlayout1.setBackgroundResource(R.mipmap.bgblue);
            }else if(tema ==1){
                linearlayout1.setBackgroundResource(R.mipmap.bggreen);
            }else if(tema ==2){
                linearlayout1.setBackgroundResource(R.mipmap.bgred);
            }else if(tema ==3){
                linearlayout1.setBackgroundResource(R.mipmap.bgyellow);
            }
        }else{
            mViewPager.setCurrentItem(0);
        }
        mViewPager.setPagingEnabled(false);

    }
    public void changeFragment(int position){
        /*
        0-logica
        1-ciencias
        2-abstracto
        3-matematicas
        4-acertado
        5-fallar
        */
        this.mViewPager.setCurrentItem(position);
    }
    private void setupViewPager(ViewPager viewPager){
        SectionsStatePageAdapter adapter = new SectionsStatePageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_Logica(),"Logica");
        adapter.addFragment(new Fragment_Ciencias(),"Ciencias");
        adapter.addFragment(new Fragment_Abstracto(),"Abstracto");
        adapter.addFragment(new Fragment_Matematicas(),"Matematicas");
        adapter.addFragment(new Fragment_Acertado(),"Acertado");
        adapter.addFragment(new Fragment_Fallar(),"Fallar");

        viewPager.setAdapter(adapter);
        viewPager.setEnabled(false);
    }

    public int getTema(){
        return this.tema;
    }
}
