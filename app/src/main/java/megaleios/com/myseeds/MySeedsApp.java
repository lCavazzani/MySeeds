package megaleios.com.myseeds;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by user on 29/09/17.
 */

public class MySeedsApp extends Application {

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);

        MultiDex.install(context);
    }
}
