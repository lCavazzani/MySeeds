package megaleios.com.myseeds.Service;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import megaleios.com.myseeds.Models.Auth;
import megaleios.com.myseeds.Models.ProfileUser;


/**
 * Created by MegaDev02 on 20/06/2017.
 */

public class RequestService {
    static int index = 0;

    private static void requisicao(final Context context, String URL, final JsonObject dados, final FutureCallback<JsonObject> futureCallback, String metodo) {
        final int innerIndex = index++;
        String token = "";

//        final SessionManager sessionManager = new SessionManager(context);
//        if (sessionManager.checkLogin()) {
//            token = sessionManager.getUsuario().getAccessToken();
//        }

        Log.i("REQUEST <" + innerIndex + ">", URL);
        Log.i("Access <" + innerIndex + ">", token);
        Log.i("DADOS <" + innerIndex + ">", dados.toString());


        FutureCallback<Response<JsonObject>> message = getResponseFutureCallback(futureCallback, innerIndex);
        Ion.with(context)
                .load(metodo, URL)
                .addHeader("Authorization", "bearer "+token)
                .setJsonObjectBody(dados)
                .asJsonObject()
                .withResponse()
                .setCallback(message);
    }

    @NonNull
    private static FutureCallback<Response<JsonObject>> getResponseFutureCallback(final FutureCallback<JsonObject> futureCallback, final int innerIndex) {
        return new FutureCallback<Response<JsonObject>>() {
            @Override
            public void onCompleted(Exception e, Response<JsonObject> result) {
                if (e == null) {
                    Log.i("RETORNO <" + innerIndex + ">", String.valueOf(result.getResult()));
                } else {
                    Log.i("ERROR <" + innerIndex + ">", e.getMessage() == null ? e.toString() : e.getMessage());

                }

                if (e != null) {
                    futureCallback.onCompleted(e, null);
                } else if (result.getHeaders().code() == 200) {
                    futureCallback.onCompleted(e, result.getResult());
                } else if (result.getHeaders().code() == 400) {
                    String msg = result.getResult().get("message").getAsString();
                    futureCallback.onCompleted(new Exception(msg), result.getResult());
                } else if (result.getHeaders().code() == 401) {

                } else {
                    //TODO tratar reauth
                    String msg = result.getHeaders().message() + " - " + result.getHeaders().code();
                    Log.i("ERROR HEADER <" + innerIndex + ">", msg);
                    futureCallback.onCompleted(new Exception(msg), result.getResult());
                }

            }
        };
    }

    private static void getJson(Context context, String URL, final FutureCallback<JsonObject> futureCallback) {
        JsonObject dados = new JsonObject();
        requisicao(context, URL, dados, futureCallback, "GET");
    }
    private static void postJson(Context context, String URL, final JsonObject dados, final FutureCallback<JsonObject> futureCallback) {
        requisicao(context, URL, dados, futureCallback, "POST");
    }

    public static void sign(final Context context, ProfileUser profileUser, final CallbackDefault callback) {

        JsonElement element = new Gson().toJsonTree(profileUser);
        JsonObject dados = element.getAsJsonObject();

        postJson(context, Config.URL_PATH + "api/v1/Profile/SignUp", dados, new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (e == null) {
                    if (Core.validar(context, result)) {
                        Auth auth = new Gson().fromJson(result, Auth.class);
                        callback.onSuccess(result);
                    } else {
                        callback.onError();
                        Core.getDialog(context, e.getMessage()).show();
                    }
                } else {
                    Core.getDialog(context, e.getMessage()).show();
                    callback.onError();
                }
            }
        });
    }

    public static void getInfo(final Context context, final CallbackDefault callback) {
        getJson(context, Config.URL_PATH + "api/v1/Profile/GetInfo", new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                validacaoBase(e, result, context, callback);
            }
        });
    }


    private static void validacaoBase(Exception e, JsonObject result, Context context, CallbackDefault callback) {
        if (e == null) {
            if (Core.validar(context, result)) {
                callback.onSuccess(result);
            } else {
                callback.onError();
            }
        } else {
            showError(e, context);
            callback.onError();
        }
    }

    private static void validacaoError(Exception e, JsonObject result, Context context, CallbackError callback) {
        if (e == null) {
            if (Core.validar(context, result)) {
                callback.onSuccess(result);
            } else {
                callback.onError(result);
            }
        } else {
            callback.onError(result);
        }
    }
    private static void showError(Exception e, Context context) {
        if (e.getMessage() != null) {
            Core.getDialog(context, e.getMessage()).show();
        }
    }

    public interface CallbackSimple {
        void onSuccess();
    }

    public interface CallbackDefault {
        void onSuccess(JsonObject result);

        void onError();
    }

    public interface CallbackError {
        void onSuccess(JsonObject result);

        void onError(JsonObject result);
    }

//    public interface CallbackAuth {
//        void onSuccess(Auth result);
//
//        void onError(String message);
//    }

}