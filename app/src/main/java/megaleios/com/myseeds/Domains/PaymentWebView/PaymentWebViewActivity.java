package megaleios.com.myseeds.Domains.PaymentWebView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;

import megaleios.com.myseeds.Models.Donation;
import megaleios.com.myseeds.R;
import megaleios.com.myseeds.Util.SessionManager;


public class PaymentWebViewActivity extends AppCompatActivity {

    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_payment);


        WebView webView = (WebView) findViewById(R.id.webviewNews);

//vistaWeb.clearCache(true);
        webView.clearHistory();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + "Doação" + "</font>"));
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));



        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

//        webView.loadUrl("http://myseeds.api.megaleios.kinghost.net/Donation/SendDonation?token="+token);
        final SessionManager sessionManager = new SessionManager(getApplicationContext());
        if (sessionManager.checkLogin()) {
            token = sessionManager.getUsuario().getAccessToken();
        }

                webView.loadUrl("http://myseeds.api.megaleios.kinghost.net/Donatation/SendDonatation?token="+token);


    }


    @Override
    public void onResume() {
        super.onResume();

        // [START screen_view_hit]
//        mTracker.setScreenName("open_news");
//        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        // [END screen_view_hit]
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
