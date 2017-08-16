package megaleios.com.myseeds.Domains.Main.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class ExplorerFragment extends Fragment{

    public ExplorerFragment() {
        // Required empty public constructor
    }

    public static ExplorerFragment newInstance() {

        Bundle args = new Bundle();

        ExplorerFragment fragment = new ExplorerFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
