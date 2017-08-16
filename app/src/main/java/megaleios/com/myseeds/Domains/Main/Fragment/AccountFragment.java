package megaleios.com.myseeds.Domains.Main.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class AccountFragment extends Fragment{

    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance() {

        Bundle args = new Bundle();

        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
