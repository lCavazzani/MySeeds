package megaleios.com.myseeds.Domains.Forgot.Fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import megaleios.com.myseeds.R;
import megaleios.com.myseeds.Service.Core;
import megaleios.com.myseeds.Service.RequestService;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class ForgotFragment extends Fragment {

    @BindView(R.id.input_email)
    TextInputEditText inputEmail;

    Unbinder unbinder;

    public ForgotFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forgot, container, false);
        unbinder = ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.button_forgot)
    public void callForgot() {
        resetPassword();
    }

    public void resetPassword() {
        if (inputEmail.getText().toString().isEmpty()) {
            inputEmail.setError("Favor informar o E-mail");
        }else{
            RequestService.forgotPassword(getContext(), inputEmail.getText().toString(), new RequestService.CallbackDefault() {
                @Override
                public void onSuccess(JsonObject result) {
                    Core.getDialog(getContext(), result.get("message").toString()).show();
                    Log.e("reset", "sucesso" + result.toString());
                }

                @Override
                public void onError() {
                    Log.e("reset", "nok sucesso");
                }
            });
        }

    }
}
