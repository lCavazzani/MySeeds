package megaleios.com.myseeds.Domains.EditProfile.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;

import butterknife.BindView;
import butterknife.ButterKnife;
import megaleios.com.myseeds.Domains.EditProfile.Fragment.EditProfileFragment;
import megaleios.com.myseeds.Domains.Forgot.Fragment.ForgotFragment;
import megaleios.com.myseeds.Domains.Profile.Fragment.ProfileFragment;
import megaleios.com.myseeds.R;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class EditProfileActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        ButterKnife.bind(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.forgotContainer, new EditProfileFragment()).commit();

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + "Editar Perfil" + "</font>"));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
