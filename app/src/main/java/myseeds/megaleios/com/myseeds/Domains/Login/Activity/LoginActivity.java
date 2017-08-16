package myseeds.megaleios.com.myseeds.Domains.Login.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import myseeds.megaleios.com.myseeds.Domains.Login.Fragment.LoginFragment;
import myseeds.megaleios.com.myseeds.R;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.rootContainer, new LoginFragment()).commit();

    }
}
