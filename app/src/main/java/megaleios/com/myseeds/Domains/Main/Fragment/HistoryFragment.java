package megaleios.com.myseeds.Domains.Main.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class HistoryFragment extends Fragment{

    public HistoryFragment() {
        // Required empty public constructor
    }

    public static HistoryFragment newInstance() {

        Bundle args = new Bundle();

        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
