package megaleios.com.myseeds.Domains.FinishedPayment.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;

import butterknife.BindView;
import butterknife.ButterKnife;
import megaleios.com.myseeds.Domains.FinishedPayment.Fragment.FinishedPaymentFragment;
import megaleios.com.myseeds.Domains.Instituicao.Fragment.InstituicaoFragment;
import megaleios.com.myseeds.Domains.PaymentConfirm.Fragment.PaymentConfirmFragment;
import megaleios.com.myseeds.R;

/**
 * Created by leonardo on 11/09/17.
 */

public class FinishedPaymentActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_payment);

        ButterKnife.bind(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.rootContainer, new FinishedPaymentFragment()).commit();

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