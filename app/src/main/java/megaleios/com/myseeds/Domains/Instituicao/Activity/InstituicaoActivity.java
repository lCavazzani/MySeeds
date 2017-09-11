package megaleios.com.myseeds.Domains.Instituicao.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import megaleios.com.myseeds.Domains.Instituicao.Fragment.InstituicaoFragment;
import megaleios.com.myseeds.Domains.Login.Fragment.LoginFragment;
import megaleios.com.myseeds.R;

/**
 * Created by leonardo on 11/09/17.
 */

public class InstituicaoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instituicao);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.rootContainer, new InstituicaoFragment()).commit();
    }
}
