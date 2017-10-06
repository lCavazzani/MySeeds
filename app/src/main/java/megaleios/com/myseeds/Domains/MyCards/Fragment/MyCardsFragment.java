package megaleios.com.myseeds.Domains.MyCards.Fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import megaleios.com.myseeds.Adapters.MyCardsAdapter;
import megaleios.com.myseeds.Adapters.NewCampaignAdapter;
import megaleios.com.myseeds.Adapters.NewInstitutionsAdapter;
import megaleios.com.myseeds.Components.SmoothRecyclerView;
import megaleios.com.myseeds.Helpers.GridSpacingItemDecoration;
import megaleios.com.myseeds.R;
import megaleios.com.myseeds.Service.Core;
import megaleios.com.myseeds.Service.RequestService;
import megaleios.com.myseeds.Util.SessionManager;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class MyCardsFragment extends Fragment {

//    @BindView(R.id.input_email)
//    TextInputEditText inputEmail;
    private SmoothRecyclerView mCards;
    public MaterialDialog loading;

    Unbinder unbinder;
    private MyCardsAdapter adapter;
    //private CardsAdapter adapter;

    public MyCardsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loading = Core.getLoading(getContext());

        View view = inflater.inflate(R.layout.fragment_my_cards, container, false);
        unbinder = ButterKnife.bind(this, view);
        LinearLayout add_card = (LinearLayout) view.findViewById(R.id.add_card);
        GridLayoutManager linearLayoutManager
                = new GridLayoutManager (getContext(),1,GridLayoutManager.VERTICAL,false);
        mCards= (SmoothRecyclerView)view.findViewById(R.id.card_list);
        mCards.setLayoutManager(linearLayoutManager);
        mCards.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(1), true));
        mCards.setItemAnimator(new DefaultItemAnimator());

//        add_card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        SessionManager sessionManager = new SessionManager(getContext());

        getCards(sessionManager);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    public void getCards(SessionManager sessionManager) {
        loading.show();
        RequestService.getCards(getActivity(), sessionManager.getUsuario().getId(), new RequestService.CallbackDefault() {
            @Override
            public void onSuccess(JsonObject result) {
                Gson googleJson = new Gson();
                final String jsonData = result.toString();
                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            //pd.dismiss();
                            updateDisplay(jsonData);
                            loading.dismiss();
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onError() {
            }
        });
    }
    private void updateDisplay(String jsonData) throws JSONException, IOException {

        Log.i("CARDS",jsonData);

        JSONObject rootObj = new JSONObject(jsonData);
        JSONArray data = rootObj.getJSONArray("data");
//        JSONArray campaing = data.getJSONArray("campaigns");
//        JSONArray institutions = data.getJSONArray("institutions");
//
//        Log.i("Azu1",campaing.toString());
//
////        rootArray = rootObj.getJSONArray("value");
////
        adapter = new MyCardsAdapter(getContext(), data);

//
//        mSuggestionListViewBemEstar.setFocusable(false);
//
        mCards.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        SessionManager sessionManager = new SessionManager(getContext());
        getCards(sessionManager);
        super.onResume();
    }


    //    @OnClick(R.id.button_forgot)
//    public void callForgot() {
//
//    }
}
