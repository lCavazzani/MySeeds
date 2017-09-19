package megaleios.com.myseeds.Profile.ProfileActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;

import butterknife.BindView;
import butterknife.ButterKnife;
import megaleios.com.myseeds.Domains.Forgot.Fragment.ForgotFragment;
import megaleios.com.myseeds.R;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class ProfileActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.forgotContainer, new ForgotFragment()).commit();

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + "Perfil" + "</font>"));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
