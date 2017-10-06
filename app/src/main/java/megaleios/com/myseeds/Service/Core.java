package megaleios.com.myseeds.Service;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.JsonObject;
import megaleios.com.myseeds.R;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by MegaDev02 on 13/07/2017.
 */

public class Core {

    public static final String TAG = "BRAU";
    public static Location location  = new Location("");
//    public static List<Brewery> lista_cervejaria = new ArrayList<Brewery>();
    public static String activityState = "";
//    public static Feed feed_atual;
//    public static PushEvent push_deslogado;

    public static MaterialDialog getDialog(Context context, String message){
        return new MaterialDialog.Builder(context)
                .title(R.string.app_name)
                .autoDismiss(true)
                .content(message)
                .positiveText("Ok").build();

    }

    public static MaterialDialog getDialogConfirm(Context context, String message, MaterialDialog.SingleButtonCallback callback){
        return new MaterialDialog.Builder(context)
                .title(R.string.app_name)
                .autoDismiss(true)
                .content(message)
                .positiveText("Ok")
                .onPositive(callback)
                .build();

    }

    public static boolean validar(final Context context, JsonObject result) {
        if (result.has("Erro") && result.get("Erro").getAsBoolean()){
            if (result.has("AcessExpire") && result.get("AcessExpire").getAsBoolean()){
                getDialogConfirm(context, result.get("Message").getAsString(), new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        //new SessionManager(context.getApplicationContext()).logoutUser();

//                        Intent i = new Intent(context, LoginActivity.class);
//                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        context.startActivity(i);
                    }
                }).show();

            }else{
                Toast.makeText(context, result.get("Message").getAsString(), Toast.LENGTH_SHORT).show();
            }

            return false;
        }
        return true;
    }

    public static MaterialDialog getLoading(Context context) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title(R.string.app_name)
                .content("Carregando...")
                .cancelable(false)
                .progress(true, 0);

        return builder.build();
    }

    public static String getDate(long time) {
        Date date = new Date(time*1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd/MM/yyyy "); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));

        return sdf.format(date);
    }
}
