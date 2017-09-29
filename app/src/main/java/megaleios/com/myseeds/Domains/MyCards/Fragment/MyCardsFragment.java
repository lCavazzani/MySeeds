package megaleios.com.myseeds.Domains.MyCards.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import megaleios.com.myseeds.R;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class MyCardsFragment extends Fragment {

//    @BindView(R.id.input_email)
//    TextInputEditText inputEmail;

    Unbinder unbinder;

    public MyCardsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_cards, container, false);
        unbinder = ButterKnife.bind(this, view);
        LinearLayout add_card = (LinearLayout) view.findViewById(R.id.add_card);

//        add_card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


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
