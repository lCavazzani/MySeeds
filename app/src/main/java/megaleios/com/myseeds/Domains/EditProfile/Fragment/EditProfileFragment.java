package megaleios.com.myseeds.Domains.EditProfile.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
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
import megaleios.com.myseeds.Service.Core;
import megaleios.com.myseeds.Service.RequestService;
import megaleios.com.myseeds.Util.MaskUtil;
import megaleios.com.myseeds.Util.SessionManager;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class EditProfileFragment extends Fragment {
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
    @BindView(R.id.input_login)
    TextInputEditText inputLogin;
    Unbinder unbinder;
    public MaterialDialog loading;
    private Auth auth;
    private SessionManager sessionManager;



    private TextWatcher maskPhone, maskCpf, maskBirthday;
    public EditProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        unbinder = ButterKnife.bind(this, view);
        loading = Core.getLoading(getContext());

        maskPhone = MaskUtil.insert("(##)#####-####", inputMobile);
        inputMobile.addTextChangedListener(maskPhone);

        maskCpf = MaskUtil.insert("###.###.###-##", inputCpf);
        inputCpf.addTextChangedListener(maskCpf);

        maskBirthday = MaskUtil.insert("##/##/####", inputBirthday);
        inputBirthday.addTextChangedListener(maskBirthday);

        auth = new Auth();
        sessionManager = new SessionManager(getContext());

        if(sessionManager.getUsuario().getFullName() != null && !sessionManager.getUsuario().getFullName().isEmpty()) {
            inputName.setText(sessionManager.getUsuario().getFullName());
        }

        if(sessionManager.getUsuario().getDateBirth() != null && !sessionManager.getUsuario().getDateBirth().isEmpty()) {
            inputBirthday.setText(sessionManager.getUsuario().getDateBirth());
        }

        if(sessionManager.getUsuario().getEmail() != null && !sessionManager.getUsuario().getEmail().isEmpty()) {
            inputEmail.setText(sessionManager.getUsuario().getEmail().trim());
        }

        if(sessionManager.getUsuario().getCpf() != null && !sessionManager.getUsuario().getCpf().isEmpty()) {
            inputCpf.setHint(sessionManager.getUsuario().getCpf().trim());
        }

        if(sessionManager.getUsuario().getCellphone() != null && !sessionManager.getUsuario().getCellphone().isEmpty()) {
            inputMobile.setHint(sessionManager.getUsuario().getCellphone().trim());
        }


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
        if (inputBirthday.getText().toString().isEmpty()) {
            Log.e("false", "8");
            inputPassword.setError(getString(R.string.password_required));
            isValid = false;
        }
        if (inputLogin.getText().toString().isEmpty()) {
            Log.e("false", "8");
            inputPassword.setError(getString(R.string.password_required));
            isValid = false;
        }
        if (inputCpf.getText().toString().isEmpty()) {
            Log.e("false", "8");
            inputCpf.setError(getString(R.string.password_required));
            isValid = false;
        }
        if (inputMobile.getText().toString().isEmpty()) {
            Log.e("false", "8");
            inputMobile.setError(getString(R.string.phone));
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
    public void saveChanges() {

        JsonObject dados = new JsonObject();

        dados.addProperty("id", sessionManager.getUsuario().getId());
        dados.addProperty("fullName", inputName.getText().toString());
        dados.addProperty("dateBirth", inputBirthday.getText().toString());
        dados.addProperty("email", inputEmail.getText().toString());
        dados.addProperty("cpf", inputCpf.getText().toString());
        dados.addProperty("cellphone", inputMobile.getText().toString());
        dados.addProperty("password", "");

        RequestService.updateProfile(getContext(), dados, new RequestService.CallbackDefault() {
            @Override
            public void onSuccess(JsonObject result) {
                Auth auth = sessionManager.getUsuario();
                JsonObject object = result.getAsJsonObject("data");
                auth.setFullName(object.get("fullName").getAsString());
                auth.setDateBirth(object.get("dateBirth").getAsString());
                auth.setCpf(object.get("cpf").getAsString());
                auth.setEmail(object.get("email").getAsString());
                //   auth.setPhoto(object.get("photo").getAsString());
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
    }

}
