package megaleios.com.myseeds.Domains.Main.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class NotificationFragment extends Fragment{

    public NotificationFragment() {
        // Required empty public constructor
    }

    public static NotificationFragment newInstance() {

        Bundle args = new Bundle();

        NotificationFragment fragment = new NotificationFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
