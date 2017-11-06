package koukin.llapanmi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Luigi on 06/11/2017.
 */

public class Activity_Preguntas extends AppCompatActivity {

    private SectionsStatePageAdapter mSectionsStatePageAdapter;
    private CustomViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logica);
        mSectionsStatePageAdapter = new SectionsStatePageAdapter(getSupportFragmentManager());
        mViewPager = (CustomViewPager) findViewById(R.id.container);

        setupViewPager(mViewPager);



        Bundle extras = getIntent().getExtras();
        int value;
        if (extras != null) {
            value = extras.getInt("Tipo");
            mViewPager.setCurrentItem(value);
        }else{
            mViewPager.setCurrentItem(0);
        }
        mViewPager.setPagingEnabled(false);

    }
    public void changeFragment(int position){

        this.mViewPager.setCurrentItem(position);

    }
    private void setupViewPager(ViewPager viewPager){
        SectionsStatePageAdapter adapter = new SectionsStatePageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_Logica(),"Logica");
        adapter.addFragment(new Fragment_Ciencias(),"Ciencias");
        adapter.addFragment(new Fragment_Abstracto(),"Abstracto");
        adapter.addFragment(new Fragment_Matematicas(),"Matematicas");

        viewPager.setAdapter(adapter);
        viewPager.setEnabled(false);
    }
}
