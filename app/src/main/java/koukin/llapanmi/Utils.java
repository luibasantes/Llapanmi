package koukin.llapanmi;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by pc on 7/11/2017.
 */

public class Utils {

    public static boolean enabledSound= true;

    public static void setFont(Context c, TextView tv,String font){
        Typeface face = Typeface.createFromAsset(c.getAssets(), "fonts/"+font);
        tv.setTypeface(face);

    }

    public static void setFont(Context c, Button bt, String font){
        Typeface face = Typeface.createFromAsset(c.getAssets(), "fonts/"+font);
        bt.setTypeface(face);

    }
}
