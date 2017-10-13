package megaleios.com.myseeds.Domains.Help.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import megaleios.com.myseeds.R;
import megaleios.com.myseeds.Service.Core;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class HelpFragment extends Fragment {

//    @BindView(R.id.input_email)
//    TextInputEditText inputEmail;

    Unbinder unbinder;

    public HelpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_help, container, false);
        unbinder = ButterKnife.bind(this, view);

        Button button_send = (Button) view.findViewById(R.id.button_send);

            button_send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Mensagem enviada com sucesso", Toast.LENGTH_LONG).show();
//                    Core.getDialog(getActivity(),"Mensagem enviada com sucesso");
                }
            });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

//    @OnClick(R.id.button_forgot)
//    public void callForgot() {
//
//    }
}
