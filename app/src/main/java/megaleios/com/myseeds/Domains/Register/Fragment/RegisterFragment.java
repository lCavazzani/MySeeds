package megaleios.com.myseeds.Domains.Register.Fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import megaleios.com.myseeds.R;
import megaleios.com.myseeds.Util.MaskUtil;

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

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.button_register)
    public void sendRegister() {

    }
}
