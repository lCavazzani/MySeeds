package megaleios.com.myseeds;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.onesignal.OneSignal;

/**
 * Created by user on 29/09/17
 */

public class MySeedsApp extends Application {

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        // Call syncHashedEmail anywhere in your app if you have the user's email.
        // This improves the effectiveness of OneSignal's "best-time" notification scheduling feature.
        // OneSignal.syncHashedEmail(userEmail);
    }
}
