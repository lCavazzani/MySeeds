package megaleios.com.myseeds.Domains.Main.Fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import megaleios.com.myseeds.Adapters.HistoryAdapter;
import megaleios.com.myseeds.Components.SmoothRecyclerView;
import megaleios.com.myseeds.Domains.HistoryWebView.Activity.HistoryWebViewActivity;
import megaleios.com.myseeds.Helpers.GridSpacingItemDecoration;
import megaleios.com.myseeds.Helpers.RecyclerItemClickListener;
import megaleios.com.myseeds.R;
import megaleios.com.myseeds.Service.RequestService;
import megaleios.com.myseeds.Util.SessionManager;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class NotificationFragment extends Fragment{

    private SmoothRecyclerView mHistory;
    private HistoryAdapter adapter;
    private SessionManager sessionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_notification , container, false);

        GridLayoutManager linearLayoutManager
                = new GridLayoutManager (getContext(),1,GridLayoutManager.VERTICAL,false);
        mHistory= (SmoothRecyclerView)view.findViewById(R.id.notification_list);
        mHistory.setLayoutManager(linearLayoutManager);
        mHistory.addItemDecoration(new GridSpacingItemDecoration(12, dpToPx(8), true));
        mHistory.setItemAnimator(new DefaultItemAnimator());
        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(mHistory);
        sessionManager = new SessionManager(getContext());
        getNotification(sessionManager);

        mHistory.addOnItemTouchListener( new RecyclerItemClickListener(getContext(), mHistory ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        TextView id = (TextView) view.findViewById(R.id.historyId);
                        Intent intent = new Intent(getActivity(), HistoryWebViewActivity.class);
                        intent.putExtra("id", id.getText().toString());
                        startActivity(intent);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        return view;
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void updateDisplayNew(String jsonData) throws JSONException, IOException {

        Log.i("Notification",jsonData);

        JSONObject rootObj = new JSONObject(jsonData);
        JSONArray data = rootObj.getJSONArray("data");
//        JSONArray campaing = data.getJSONArray("donatations");
//        JSONArray institutions = data.getJSONArray("institutions");


//        rootArray = rootObj.getJSONArray("value");
//
//        adapter = new HistoryAdapter(getContext(), data);
//
////
////        mSuggestionListViewBemEstar.setFocusable(false);
////
//        mHistory.setAdapter(adapter);

    }
    public void getNotification(SessionManager sessionManager) {
        RequestService.getNotification(getActivity(), sessionManager.getUsuario().getId(), 1, new RequestService.CallbackDefault() {
            @Override
            public void onSuccess(JsonObject result) {
                Gson googleJson = new Gson();
                final String jsonData = result.toString();
                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            //pd.dismiss();
                            updateDisplayNew(jsonData);
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


}