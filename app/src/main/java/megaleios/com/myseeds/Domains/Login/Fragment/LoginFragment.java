package megaleios.com.myseeds.Domains.Login.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import megaleios.com.myseeds.Domains.Forgot.Activity.ForgotActivity;
import megaleios.com.myseeds.Domains.Register.Activity.RegisterActivity;
import megaleios.com.myseeds.R;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class LoginFragment extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.input_email)
    TextInputEditText inputEmail;
    @BindView(R.id.input_password)
    TextInputEditText inputPassword;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.button_login)
    public void login() {

    }

    @OnClick(R.id.button_login_facebook)
    public void loginFacebook() {

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

}
