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
import megaleios.com.myseeds.Util.SessionManager;


/**
 * Created by MegaDev02 on 20/06/2017.
 */

public class RequestService {
    static int index = 0;

    private static void requisicao(final Context context, String URL, final JsonObject dados, final FutureCallback<JsonObject> futureCallback, String metodo) {
        final int innerIndex = index++;
        String token = "";

        final SessionManager sessionManager = new SessionManager(context);
        if (sessionManager.checkLogin()) {
            token = sessionManager.getUsuario().getAccessToken();
        }

        Log.i("REQUEST <" + innerIndex + ">", URL);
        Log.i("Access <" + innerIndex + ">", token);
        Log.i("DADOS <" + innerIndex + ">", dados.toString());

        if(token.equals("")){
            FutureCallback<Response<JsonObject>> message = getResponseFutureCallback(futureCallback, innerIndex);
            Ion.with(context)
                    .load(metodo, URL)
//                    .addHeader("Authorization", "bearer "+token)
                    .setJsonObjectBody(dados)
                    .asJsonObject()
                    .withResponse()
                    .setCallback(message);
        }
        else{
            FutureCallback<Response<JsonObject>> message = getResponseFutureCallback(futureCallback, innerIndex);
            Ion.with(context)
                    .load(metodo, URL)
                    .addHeader("Authorization", "bearer "+token)
                    .setJsonObjectBody(dados)
                    .asJsonObject()
                    .withResponse()
                    .setCallback(message);
        }

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
                    Log.i("ERROR HEADER <", "ERROR");
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

    public static void forgotPassword(final Context context, String email, final CallbackDefault callback) {

        JsonObject dados = new JsonObject();
        dados.addProperty("email", email);

        postJson(context, Config.URL_PATH + "api/v1/profile/forgotpassword", dados, new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (e == null) {
                    if (Core.validar(context, result)) {
                        callback.onSuccess(result);
                    } else {
                        Core.getDialog(context, result.get("message").toString()).show();
                        callback.onError();
                    }
                } else {
                    Core.getDialog(context, result.get("message").toString()).show();
                    callback.onError();
                }
            }
        });
    }

    public static void updateProfile(final Context context, JsonObject dados, final CallbackDefault callback) {

        postJson(context, Config.URL_PATH + "api/v1/profile/update", dados, new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (e == null) {
                    if (Core.validar(context, result)) {
                        callback.onSuccess(result);
                    } else {
                        Core.getDialog(context, result.get("message").toString()).show();
                        callback.onError();
                    }
                } else {
                    Core.getDialog(context, result.get("message").toString()).show();
                    callback.onError();
                }
            }
        });
    }

    public static void addCard(final Context context, JsonObject dados, final CallbackDefault callback) {

        postJson(context, Config.URL_PATH + "api/v1/CreditCard/", dados, new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (e == null) {
                    if (Core.validar(context, result)) {
                        callback.onSuccess(result);
                    } else {
                        Core.getDialog(context, result.get("message").toString()).show();
                        callback.onError();
                    }
                } else {
                    Core.getDialog(context, result.get("message").toString()).show();
                    callback.onError();
                }
            }
        });
    }

    public static void deleteCard(final Context context, JsonObject dados, final CallbackDefault callback) {

        postJson(context, Config.URL_PATH + "api/v1/CreditCard/Delete", dados, new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (e == null) {
                    if (Core.validar(context, result)) {
                        callback.onSuccess(result);
                    } else {
                        Core.getDialog(context, result.get("message").toString()).show();
                        callback.onError();
                    }
                } else {
                    Core.getDialog(context, result.get("message").toString()).show();
                    callback.onError();
                }
            }
        });
    }

    public static void updatePassword(final Context context, String profileId, String newPassword, String lastPassword, final CallbackDefault callback) {
        JsonObject dados = new JsonObject();
        dados.addProperty("id", profileId);
        dados.addProperty("newPassword", newPassword);
        dados.addProperty("password", lastPassword);

        postJson(context, Config.URL_PATH + "api/v1/profile/updatepassword", dados, new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (e == null) {
                    if (Core.validar(context, result)) {
                        callback.onSuccess(result);
                    } else {
                        //   Core.getDialog(context, result.get("message").toString()).show();
                        callback.onError();
                    }
                } else {
//                    Core.getDialog(context, result.get("message").toString()).show();
                    callback.onError();
                }
            }
        });
    }

    public static void login(final Context context, String username, String password, final CallbackDefault callback) {

        Ion.with(context)
                .load(Config.URL_PATH + "api/v1/Profile/Token")
                .setBodyParameter("login", username)
                .setBodyParameter("password", password)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if (e == null) {
                            JsonObject json = new Gson().fromJson(result, JsonObject.class);
                            Log.e("json", json.toString());
                            if (json.get("erro").getAsBoolean()) {
                                Core.getDialog(context, json.get("message").toString()).show();
                                callback.onError();
                            } else {
                                callback.onSuccess(json);
                            }
                        } else {
                            Core.getDialog(context, e.getMessage()).show();
                            //callback.onError(context.getString(R.string.erro_desconhecido));
                        }
                    }
                });
    }

    public static void loginFace(final Context context, String facebookId, final CallbackDefault callback) {

        Ion.with(context)
                .load(Config.URL_PATH + "api/v1/Profile/Token")
                .setBodyParameter("facebookid", facebookId)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        Log.e("json", result);
                        if (e == null) {
                            JsonObject json = new Gson().fromJson(result, JsonObject.class);
                            Log.e("json", json.toString());
                            if (json.get("erro").getAsBoolean()) {
                                Core.getDialog(context, json.get("message").toString()).show();
                                callback.onError();
                            } else {
                                callback.onSuccess(json);
                            }
                        } else {
                            Core.getDialog(context, e.getMessage()).show();
                            //callback.onError(context.getString(R.string.erro_desconhecido));
                        }
                    }
                });
    }

    public static void getCards(final Context context, String profileId, final CallbackDefault callback) {
        getJson(context, Config.URL_PATH + "api/v1/CreditCard/" + profileId, new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (e == null) {
                    if (Core.validar(context, result)) {
                        callback.onSuccess(result);
                    } else {
                        callback.onError();
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
                if (e == null) {
                    if (Core.validar(context, result)) {
                        callback.onSuccess(result);
                    } else {
                        callback.onError();
                    }
                } else {
                    Core.getDialog(context, e.getMessage()).show();
                    callback.onError();
                }
            }
        });
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

    public static void getFeed(final Context context, String typeFilter, int page, final CallbackDefault callback) {
        getJson(context, Config.URL_PATH + "api/v1/Instituion/ExplorerFilter/1/1", new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                //Log.e("result", result.toString());
                if (e == null) {
                    if (Core.validar(context, result)) {
                        callback.onSuccess(result);
                    } else {
                        callback.onError();
                    }
                } else {
                    Core.getDialog(context, e.getMessage()).show();
                    callback.onError();
                }
            }
        });
    }
    public static void getDetailsInstitution(final Context context, String institutionId, int page, final CallbackDefault callback) {
        getJson(context, Config.URL_PATH + "api/v1/Instituion/DetailsInstitution/"+institutionId+"/1", new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                //Log.e("result", result.toString());
                if (e == null) {
                    if (Core.validar(context, result)) {
                        callback.onSuccess(result);
                    } else {
                        callback.onError();
                    }
                } else {
                    Core.getDialog(context, e.getMessage()).show();
                    callback.onError();
                }
            }
        });
    }

    public static void getDonation(final Context context, JsonObject dados, final CallbackDefault callback) {

        postJson(context, Config.URL_PATH + "api/v1/Donatation", dados, new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (e == null) {
                    if (Core.validar(context, result)) {
                        callback.onSuccess(result);
                    } else {
                        Core.getDialog(context, result.get("message").toString()).show();
                        callback.onError();
                    }
                } else {
                    Core.getDialog(context, result.get("message").toString()).show();
                    callback.onError();
                }
            }
        });
    }

    public static void getHistory(final Context context, String profileId, int page, final CallbackDefault callback) {
        getJson(context, Config.URL_PATH + "api/v1/MyDonatations/"+profileId+"/1", new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                //Log.e("result", result.toString());
                if (e == null) {
                    if (Core.validar(context, result)) {
                        callback.onSuccess(result);
                    } else {
                        callback.onError();
                    }
                } else {
                    Core.getDialog(context, e.getMessage()).show();
                    callback.onError();
                }
            }
        });
    }
    public static void getNotification(final Context context, String profileId, int page, final CallbackDefault callback) {
        getJson(context, Config.URL_PATH + "api/v1/notification/"+profileId+"/1", new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                //Log.e("result", result.toString());
                if (e == null) {
                    if (Core.validar(context, result)) {
                        callback.onSuccess(result);
                    } else {
                        callback.onError();
                    }
                } else {
                    Core.getDialog(context, e.getMessage()).show();
                    callback.onError();
                }
            }
        });
    }


}