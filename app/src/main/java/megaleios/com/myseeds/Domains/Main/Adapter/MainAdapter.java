package megaleios.com.myseeds.Domains.Main.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import megaleios.com.myseeds.Domains.Main.Fragment.AccountFragment;
import megaleios.com.myseeds.Domains.Main.Fragment.ExplorerFragment;
import megaleios.com.myseeds.Domains.Main.Fragment.HistoryFragment;
import megaleios.com.myseeds.Domains.Main.Fragment.NotificationFragment;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class MainAdapter extends FragmentPagerAdapter {

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return ExplorerFragment.newInstance();
            case 1:
                return NotificationFragment.newInstance();
            case 2 :
                return HistoryFragment.newInstance();
            case 3:
                return AccountFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

}
