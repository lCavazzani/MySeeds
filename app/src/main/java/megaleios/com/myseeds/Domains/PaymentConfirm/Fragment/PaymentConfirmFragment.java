package megaleios.com.myseeds.Domains.PaymentConfirm.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import megaleios.com.myseeds.Adapters.PaymentConfirmAdapter;
import megaleios.com.myseeds.Domains.FinishedPayment.Activity.FinishedPaymentActivity;
import megaleios.com.myseeds.Domains.Instituicao.Activity.InstituicaoActivity;
import megaleios.com.myseeds.Domains.PaymentConfirm.Activity.PaymentConfirmActivity;
import megaleios.com.myseeds.R;

/**
 * Created by MegaDev02 on 13/09/2017.
 */

public class PaymentConfirmFragment extends Fragment {

    @BindView(R.id.button_contribuir)
    Button contribuir;
    String[] titles = new String[]{"Campanha festa junina", "Campanha de Natal", "Campanha ação de graças", "Valor Total"};
    String[] values = new String[]{"R$ 35,00", "R$ 10,00", "R$ 5,00", "R$ 86,00"};


    Unbinder unbinder;

    public PaymentConfirmFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_payment_confirmation, container, false);
        unbinder = ButterKnife.bind(this, view);

        contribuir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FinishedPaymentActivity.class);
                startActivity(i);
            }
        });
        ListView listView = (ListView) view.findViewById(R.id.list);

        listView.setAdapter(new PaymentConfirmAdapter((PaymentConfirmActivity) view.getContext(),titles,values ));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
