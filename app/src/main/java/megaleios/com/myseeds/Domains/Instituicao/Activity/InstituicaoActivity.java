package megaleios.com.myseeds.Domains.Instituicao.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;

import butterknife.BindView;
import butterknife.ButterKnife;
import megaleios.com.myseeds.Domains.Instituicao.Fragment.InstituicaoFragment;
import megaleios.com.myseeds.R;

/**
 * Created by leonardo on 11/09/17.
 */

public class InstituicaoActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instituicao);

        ButterKnife.bind(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.rootContainer, new InstituicaoFragment()).commit();

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + "" + "</font>"));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
