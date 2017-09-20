package megaleios.com.myseeds.Domains.Login.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import megaleios.com.myseeds.Domains.Forgot.Activity.ForgotActivity;
import megaleios.com.myseeds.Domains.Login.Activity.LoginActivity;
import megaleios.com.myseeds.Domains.Register.Activity.RegisterActivity;
import megaleios.com.myseeds.Domains.Main.Activity.MainActivity;
import megaleios.com.myseeds.Models.Auth;
import megaleios.com.myseeds.R;
import megaleios.com.myseeds.Service.Core;
import megaleios.com.myseeds.Service.RequestService;
import megaleios.com.myseeds.Util.SessionManager;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class LoginFragment extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.input_email)
    TextInputEditText inputEmail;
    @BindView(R.id.input_password)
    TextInputEditText inputPassword;
    public MaterialDialog loading;

    TextView openRegister;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        openRegister = (TextView)view.findViewById(R.id.open_register);

        openRegister.setText(Html.fromHtml(getString(R.string.open_register_text)));
        loading = Core.getLoading(getContext());


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.button_login)
    public void login() {
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.button_login_facebook)
    public void loginFacebook() {
        callFeed();
    }

    @OnClick(R.id.open_register)
    public void openRegister() {
        Intent i = new Intent(getActivity(), RegisterActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.forgot_password)
    public void forgotPassword() {
        Intent i = new Intent(getActivity(), ForgotActivity.class);
        startActivity(i);
    }
    private void callFeed() {

        if(inputEmail.getText().toString().isEmpty()) {
            inputEmail.setError("Favor informar o login");
        }else if(inputPassword.getText().toString().isEmpty()){
            inputPassword.setError("Favor informar a senha");
        }else{
            loading.show();
            new SessionManager(getContext()).logoutUser();
            RequestService.login(getContext(), inputEmail.getText().toString(), inputPassword.getText().toString(), new RequestService.CallbackDefault() {
                @Override
                public void onSuccess(JsonObject result) {
                    JsonObject object = result.getAsJsonObject("data");

                    final Auth auth = new Auth();
                    auth.setAccessToken(object.get("access_token").getAsString());

                    final SessionManager sessionManager = new SessionManager(getContext());
                    sessionManager.createLoginSession(auth);

                    RequestService.getInfo(getContext(), new RequestService.CallbackDefault() {
                        @Override
                        public void onSuccess(JsonObject result) {
                            JsonObject object = result.getAsJsonObject("data");
                            auth.setFullName(object.get("fullName").getAsString());
                            auth.setDateBirth(object.get("dateBirth").getAsString());
                            auth.setCpf(object.get("cpf").getAsString());
                            auth.setEmail(object.get("email").getAsString());
                            //   auth.setPhoto(object.get("photo").getAsString());
                            auth.setCellphone(object.get("cellphone").getAsString());
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


                }

                @Override
                public void onError() {
                    loading.dismiss();
                }
            });
        }
    }

}
