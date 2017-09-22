package megaleios.com.myseeds.Domains.ChangePassword.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import megaleios.com.myseeds.R;
import megaleios.com.myseeds.Service.RequestService;
import megaleios.com.myseeds.Util.SessionManager;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class ChangePasswordFragment extends Fragment {

    //    @BindView(R.id.input_email)
//    TextInputEditText inputEmail;
    @BindView(R.id.button_change)
    Button btnEntrar;
    @BindView(R.id.input_last_password)
    EditText inputLastPassword;
    @BindView(R.id.input_new_password)
    EditText inputNewPassword;
    @BindView(R.id.input_confirm_password)
    EditText inputConfirmPassword;
    Unbinder unbinder;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_change_password, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.button_change)
    public void sendNewPassword() {
        if (validForm()) {
            SessionManager sessionManager = new SessionManager(getContext());
            RequestService.updatePassword(getContext(), sessionManager.getUsuario().getId(), inputNewPassword.getText().toString(), inputLastPassword.getText().toString(), new RequestService.CallbackDefault() {
                @Override
                public void onSuccess(JsonObject result) {
                    getActivity().finish();
                }

                @Override
                public void onError() {
                    Log.e("reset", "nok sucesso");
                }
            });
        }

    }

    private boolean validForm() {
        boolean isValid = true;

        if (inputLastPassword.getText().toString().isEmpty()) {
            inputLastPassword.setError("Favor informar a senha antiga");
            isValid = false;
        } else if (inputNewPassword.getText().toString().isEmpty()) {
            inputNewPassword.setError("Favor informar a nova senha");
            isValid = false;
        } else if (inputConfirmPassword.getText().toString().isEmpty()) {
            inputConfirmPassword.setError("Favor confirmar a nova senha");
            isValid = false;
        } else if (!inputNewPassword.getText().toString().equals(inputConfirmPassword.getText().toString())) {
            inputConfirmPassword.setError("Senha deve ser igual ao campo anterior");
            isValid = false;
        }
        return isValid;
    }

}
