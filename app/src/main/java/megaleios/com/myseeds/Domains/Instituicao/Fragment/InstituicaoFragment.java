package megaleios.com.myseeds.Domains.Instituicao.Fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import megaleios.com.myseeds.R;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class InstituicaoFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_instituicao, container, false);
        return view;
    }
}