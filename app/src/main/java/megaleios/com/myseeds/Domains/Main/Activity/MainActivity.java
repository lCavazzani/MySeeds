package megaleios.com.myseeds.Domains.Main.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import megaleios.com.myseeds.Adapters.FragmentAdapter;
import megaleios.com.myseeds.Domains.Main.Fragment.AccountFragment;
import megaleios.com.myseeds.Domains.Main.Fragment.ExplorerFragment;
import megaleios.com.myseeds.Domains.Main.Fragment.HistoryFragment;
import megaleios.com.myseeds.Domains.Main.Fragment.NotificationFragment;
import megaleios.com.myseeds.Helpers.BottomNavigationViewHelper;
import megaleios.com.myseeds.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.requestFocus();
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new ExplorerFragment(), "Explorar", false);
        adapter.addFragment(new NotificationFragment(), "Notificacoes", false);
        adapter.addFragment(new HistoryFragment(), "Historico", false);
        adapter.addFragment(new AccountFragment(), "Conta", false);
       // viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.action_explorer:
                        Log.i("BottomNav", "exp");
                        viewPager.setCurrentItem(0);
                        final Menu bottomMenu = bottomNavigationView.getMenu();
                        final MenuItem targetMenuItem = bottomMenu.getItem(0);
                        targetMenuItem.setChecked(true);
                        break;
                    case R.id.action_notification:
                        Log.i("BottomNav", "not");
                        viewPager.setCurrentItem(1);
                        final Menu bottomMenu1 = bottomNavigationView.getMenu();
                        final MenuItem targetMenuItem1 = bottomMenu1.getItem(1);
                        targetMenuItem1.setChecked(true);
                        break;
                    case R.id.action_history:
                        Log.i("BottomNav", "hist");
                        viewPager.setCurrentItem(2);
                        final Menu bottomMenu2 = bottomNavigationView.getMenu();
                        final MenuItem targetMenuItem2 = bottomMenu2.getItem(2);
                        targetMenuItem2.setChecked(true);
                        break;
                    case R.id.action_account:
                        Log.i("BottomNav", "acc");
                        viewPager.setCurrentItem(3);
                        final Menu bottomMenu4 = bottomNavigationView.getMenu();
                        final MenuItem targetMenuItem4 = bottomMenu4.getItem(3);
                        targetMenuItem4.setChecked(true);
                        break;
                    case R.id.action_search:
                        Log.i("BottomNav", "Search");
                        break;
                }
                return true;
            }
        });
    }
}
