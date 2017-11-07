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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);
        mSectionsStatePageAdapter = new SectionsStatePageAdapter(getSupportFragmentManager());
        mViewPager = (CustomViewPager) findViewById(R.id.container);
        linearlayout1 = (LinearLayout) findViewById(R.id.linearLayout1);

        setupViewPager(mViewPager);



        Bundle extras = getIntent().getExtras();
        int value;
        if (extras != null) {
            value = extras.getInt("Tipo");
            mViewPager.setCurrentItem(value);
            if(value==0){
                linearlayout1.setBackgroundResource(R.mipmap.bgblue);
            }else if(value==1){
                linearlayout1.setBackgroundResource(R.mipmap.bggreen);
            }else if(value==2){
                linearlayout1.setBackgroundResource(R.mipmap.bgred);
            }else if(value==3){
                linearlayout1.setBackgroundResource(R.mipmap.bgyellow);
            }
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
