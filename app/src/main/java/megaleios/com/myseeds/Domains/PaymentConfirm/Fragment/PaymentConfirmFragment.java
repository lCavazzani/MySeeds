package megaleios.com.myseeds.Domains.PaymentConfirm.Fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import megaleios.com.myseeds.R;

/**
 * Created by MegaDev02 on 13/09/2017.
 */

public class PaymentConfirmFragment extends Fragment {

    @BindView(R.id.input_email)
    TextInputEditText inputEmail;

    Unbinder unbinder;

    public PaymentConfirmFragment() {
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

    }
}
