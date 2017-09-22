package megaleios.com.myseeds.Domains.Profile.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import megaleios.com.myseeds.Domains.EditProfile.Activity.EditProfileActivity;
import megaleios.com.myseeds.Domains.Main.Activity.MainActivity;
import megaleios.com.myseeds.Models.Auth;
import megaleios.com.myseeds.R;
import megaleios.com.myseeds.Service.RequestService;
import megaleios.com.myseeds.Util.SessionManager;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class ProfileFragment extends Fragment {
    @BindView(R.id.save_changes)
    Button saveChanges;
    @BindView(R.id.name_textview)
    TextView fullName;
    @BindView(R.id.email_textview)
    TextView email;
    @BindView(R.id.cellphone_textview)
    TextView cellphone;
    @BindView(R.id.birth_textview)
    TextView dateBirth;
    @BindView(R.id.cpf_textview)
    TextView cpf;
    Unbinder unbinder;
    private SessionManager sessionManager;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, view);

        sessionManager = new SessionManager(getContext());

        if(sessionManager.getUsuario().getFullName() != null && !sessionManager.getUsuario().getFullName().isEmpty()) {
            fullName.setText(sessionManager.getUsuario().getFullName());
        }
        if(sessionManager.getUsuario().getDateBirth() != null && !sessionManager.getUsuario().getDateBirth().isEmpty()) {
            dateBirth.setText(sessionManager.getUsuario().getDateBirth());
        }
        if(sessionManager.getUsuario().getEmail() != null && !sessionManager.getUsuario().getEmail().isEmpty()) {
            email.setText(sessionManager.getUsuario().getEmail().trim());
        }
        if(sessionManager.getUsuario().getCpf() != null && !sessionManager.getUsuario().getCpf().isEmpty()) {
            cpf.setHint(sessionManager.getUsuario().getCpf().trim());
        }
        if(sessionManager.getUsuario().getCellphone() != null && !sessionManager.getUsuario().getCellphone().isEmpty()) {
            cellphone.setHint(sessionManager.getUsuario().getCellphone().trim());
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.save_changes)
    public void callForgot() {
        Intent i = new Intent(getActivity(), EditProfileActivity.class);
        startActivity(i);
    }

}
