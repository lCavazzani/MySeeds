package megaleios.com.myseeds.Domains.Profile.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import megaleios.com.myseeds.Domains.EditProfile.Activity.EditProfileActivity;
import megaleios.com.myseeds.Domains.Main.Activity.MainActivity;
import megaleios.com.myseeds.Models.Auth;
import megaleios.com.myseeds.R;
import megaleios.com.myseeds.Service.Core;
import megaleios.com.myseeds.Service.RequestService;
import megaleios.com.myseeds.Util.ImageInputHelper;
import megaleios.com.myseeds.Util.SessionManager;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class ProfileFragment extends Fragment implements ImageInputHelper.ImageActionListener{
    @BindView(R.id.save_changes)
    Button saveChanges;
    @BindView(R.id.name_textview)
    TextView fullName;
    @BindView(R.id.email_textview)
    TextView email;
    @BindView(R.id.cellphone_textview)
    TextView cellphone;
    @BindView(R.id.birth_textview)
    TextView dateBirth;
    @BindView(R.id.cpf_textview)
    TextView cpf;
    @BindView(R.id.login_textview)
    TextView login_textview;
    @BindView(R.id.edit_photo)
    TextView edit_photo;
    @BindView(R.id.imageView7)
    ImageView imageView7;

    private String imgUpada = "";

    Unbinder unbinder;
    private ImageInputHelper imageInputHelper;
    public MaterialDialog loading;

    private SessionManager sessionManager;
    Auth auth;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        auth = Auth.getInstance();

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, view);
        imageInputHelper = new ImageInputHelper(this);
        imageInputHelper.setImageActionListener(this);
        sessionManager = new SessionManager(getContext());
        loading = Core.getLoading(getContext());

        if(sessionManager.getUsuario().getFullName() != null && !sessionManager.getUsuario().getFullName().isEmpty()) {
            fullName.setText(sessionManager.getUsuario().getFullName());
        }
        if (sessionManager.getUsuario().getLogin() != null && !sessionManager.getUsuario().getLogin().isEmpty()){
            login_textview.setText(sessionManager.getUsuario().getLogin());
        }
        if(sessionManager.getUsuario().getDateBirth() != null && !sessionManager.getUsuario().getDateBirth().isEmpty()) {
            dateBirth.setText(sessionManager.getUsuario().getDateBirth());
        }
        if(sessionManager.getUsuario().getEmail() != null && !sessionManager.getUsuario().getEmail().isEmpty()) {
            email.setText(sessionManager.getUsuario().getEmail().trim());
        }
        if(sessionManager.getUsuario().getCpf() != null && !sessionManager.getUsuario().getCpf().isEmpty()) {
            cpf.setText(sessionManager.getUsuario().getCpf().trim());
        }
        if(sessionManager.getUsuario().getCellphone() != null && !sessionManager.getUsuario().getCellphone().isEmpty()) {
            cellphone.setText(sessionManager.getUsuario().getCellphone().trim());
        }
        if(sessionManager.getUsuario().getPhoto() != null && !sessionManager.getUsuario().getPhoto().isEmpty()) {
            Glide
                    .with(this)
                    .load(sessionManager.getUsuario().getPhoto())
                    .thumbnail(1f)
                    .into(imageView7);
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.save_changes)
    public void callForgot() {
        Intent i = new Intent(getActivity(), EditProfileActivity.class);
        startActivity(i);
    }
    @OnClick(R.id.edit_photo)
    public void editPhoto() {
        imageInputHelper = new ImageInputHelper(this);
        imageInputHelper.setImageActionListener(this);
        imageInputHelper.selectImageFromGallery();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            imageInputHelper.onActivityResult(requestCode, resultCode, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onImageSelectedFromGallery(Uri uri, File imageFile) {
        imageInputHelper.requestCropImage(uri, 512, 512, 1, 1);
    }

    @Override
    public void onImageTakenFromCamera(Uri uri, File imageFile) {

    }

    @Override
    public void onImageCropped(Uri uri, File imageFile) throws IOException {
        // getting bitmap from uri
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),uri);

        uploadImage(uri);

        Log.e("uri", uri.toString());

        Glide
                .with(this)
                .load(uri)
                .thumbnail(1f)
                .into(imageView7);


    }
    private void uploadImage(Uri uri) {

        File arquivo = new File(uri.getPath());

        Glide
                .with(this)
                .load(uri)
                .thumbnail(1f)
                .into(imageView7);

        loading.show();
        Ion.with(this)
                .load("POST","http://myseeds.api.megaleios.kinghost.net/api/v1/Profile/Upload")
                .progressHandler(new ProgressCallback() {
                    @Override
                    public void onProgress(long downloaded, long total) {
                        Log.i("UPLOAD", "" + downloaded + " / " + total);
                    }
                })
                .setMultipartFile("files", "multipart/form-data", arquivo)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        loading.dismiss();
                        if (e == null) {
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                JSONObject array = jsonObject.getJSONObject("data");
                                imgUpada = array.optString("fileName");
                                Log.i("UPLOAD", imgUpada);
                                JsonObject dados = new JsonObject();

                                dados.addProperty("id", sessionManager.getUsuario().getId());
                                dados.addProperty("fullName", sessionManager.getUsuario().getFullName());
                                dados.addProperty("dateBirth", sessionManager.getUsuario().getDateBirth());
                                dados.addProperty("email", sessionManager.getUsuario().getEmail());
                                dados.addProperty("cpf", sessionManager.getUsuario().getCpf());
                                dados.addProperty("cellphone", sessionManager.getUsuario().getCellphone());
                                dados.addProperty("photo", imgUpada);
                                RequestService.updateProfile(getContext(), dados, new RequestService.CallbackDefault() {
                                    @Override
                                    public void onSuccess(JsonObject result) {
                                        Auth auth = sessionManager.getUsuario();
                                        JsonObject object = result.getAsJsonObject("data");
                                        auth.setFullName(object.get("fullName").getAsString());
                                        auth.setDateBirth(object.get("dateBirth").getAsString());
                                        auth.setCpf(object.get("cpf").getAsString());
                                        auth.setEmail(object.get("email").getAsString());
                                        auth.setPhoto(object.get("photo").getAsString());
                                        auth.setCellphone(object.get("cellphone").getAsString());
                                        auth.setId(object.get("id").getAsString());

                                        SessionManager sessionManager = new SessionManager(getContext());
                                        sessionManager.createLoginSession(auth);

                                        Intent intent = getActivity().getIntent();
                                        getActivity().finish();
                                        startActivity(intent);
                                        Core.getDialogConfirm(getContext(), result.get("message").getAsString(), new MaterialDialog.SingleButtonCallback() {
                                            @Override
                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                getActivity().finish();
                                            }
                                        }).show();
                                    }
                                    @Override
                                    public void onError() {
                                        Log.e("reset", "nok sucesso");
                                    }
                                });

                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }

                        } else {
                            String errorMessage = e.getMessage();
                            new MaterialDialog.Builder(getContext())
                                    .title("MySeeds")
                                    .content(errorMessage)
                                    .positiveText(R.string.ok)
                                    .show();
                        }
                    }
                });
    }

}
