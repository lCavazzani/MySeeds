package megaleios.com.myseeds.Domains.Register.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import megaleios.com.myseeds.Domains.Main.Activity.MainActivity;
import megaleios.com.myseeds.Models.Auth;
import megaleios.com.myseeds.Models.ProfileUser;
import megaleios.com.myseeds.R;
import megaleios.com.myseeds.Service.RequestService;
import megaleios.com.myseeds.Util.MaskUtil;
import megaleios.com.myseeds.Util.SessionManager;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class RegisterFragment extends Fragment {
    @BindView(R.id.input_name)
    TextInputEditText inputName;
    @BindView(R.id.input_email)
    TextInputEditText inputEmail;
    @BindView(R.id.input_birthday)
    TextInputEditText inputBirthday;
    @BindView(R.id.input_cpf)
    TextInputEditText inputCpf;
    @BindView(R.id.input_mobile)
    TextInputEditText inputMobile;
    @BindView(R.id.input_password)
    TextInputEditText inputPassword;
    @BindView(R.id.input_password_confirm)
    TextInputEditText inputConfirmPassword;
    Unbinder unbinder;
    public MaterialDialog loading;
    private Auth auth;


    private TextWatcher maskPhone, maskCpf, maskBirthday;
    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        unbinder = ButterKnife.bind(this, view);

        maskPhone = MaskUtil.insert("(##)#####-####", inputMobile);
        inputMobile.addTextChangedListener(maskPhone);

        maskCpf = MaskUtil.insert("###.###.###-##", inputCpf);
        inputCpf.addTextChangedListener(maskCpf);

        maskBirthday = MaskUtil.insert("##/##/####", inputBirthday);
        inputBirthday.addTextChangedListener(maskBirthday);

        auth = new Auth();


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private boolean validForm() {

        boolean isValid = true;

        if (inputName.getText().toString().isEmpty()) {
            Log.e("false", "1");
            inputName.setError(getString(R.string.name_required));
            isValid = false;
        }

        if (inputEmail.getText().toString().isEmpty()) {
            Log.e("false", "4");
            inputEmail.setError(getString(R.string.email_required));
            isValid = false;
        }

        if (inputPassword.getText().toString().isEmpty()) {
            Log.e("false", "8");
            inputPassword.setError(getString(R.string.password_required));
            isValid = false;
        }
        if (inputConfirmPassword.getText().toString().isEmpty()) {
            Log.e("false", "9");
            inputConfirmPassword.setError(getString(R.string.confirm_password_required));
            isValid = false;
        }

        if (!inputConfirmPassword.getText().toString().equals(inputPassword.getText().toString())) {
            inputConfirmPassword.setError(getString(R.string.confirm_password_required));
            inputPassword.setError(getString(R.string.confirm_password_required));
            isValid = false;
        }

        return isValid;

    }

    @OnClick(R.id.button_register)
    public void sendRegister() {
        if (validForm()) {
            loading.show();
            ProfileUser profileUser = new ProfileUser();
            profileUser.setFirstName(inputName.getText().toString());
            profileUser.setEmail(inputEmail.getText().toString());
            profileUser.setPassword(inputPassword.getText().toString());


            RequestService.sign(getContext(), profileUser, new RequestService.CallbackDefault() {
                @Override
                public void onSuccess(JsonObject result) {

                    JsonObject objectData = result.getAsJsonObject("data");

                    auth.setAccessToken(objectData.get("access_token").getAsString());

                    final SessionManager sessionManager = new SessionManager(getActivity());
                    sessionManager.createLoginSession(auth);

                    RequestService.getInfo(getActivity(), new RequestService.CallbackDefault() {
                        @Override
                        public void onSuccess(JsonObject result) {
                            JsonObject object = result.getAsJsonObject("data");
                            auth.setFirstName(object.get("firstName").getAsString());
                            auth.setLastName(object.get("lastName").getAsString());
                            auth.setPhone(object.get("phone").getAsString());
                            auth.setEmail(object.get("email").getAsString());
                            auth.setPhotoProfile(object.get("photoProfile").getAsString());
                            auth.setDescription(object.get("description").toString());
                            auth.setCityId(object.getAsJsonObject("city").get("id").getAsString());
                            auth.setStateId(object.getAsJsonObject("city").get("stateId").getAsString());
                            auth.setCityName(object.getAsJsonObject("city").get("name").getAsString());
                            auth.setStateName(object.getAsJsonObject("city").get("uf").getAsString());
                            auth.setPosts(Integer.valueOf(object.get("posts").getAsString()));
                            auth.setFriends(Integer.valueOf(object.get("friends").getAsString()));
                            auth.setId(object.get("id").getAsString());

                            SessionManager sessionManager = new SessionManager(getActivity());
                            sessionManager.createLoginSession(auth);

                            loading.dismiss();
                            Intent i = new Intent(getActivity(), MainActivity.class);
                            startActivity(i);
                        }

                        @Override
                        public void onError() {
                            loading.dismiss();
                        }
                    });

                    loading.dismiss();
                    Log.e("string", "success");
                }

                @Override
                public void onError() {

                    loading.dismiss();
                    Log.e("string", "no success");
                }
            });
        }

        Log.e("register", "ok");

    }
}
