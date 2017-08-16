package myseeds.megaleios.com.myseeds.Domains.Login.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import myseeds.megaleios.com.myseeds.R;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class LoginFragment extends Fragment {
    Unbinder unbinder;
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

    }
