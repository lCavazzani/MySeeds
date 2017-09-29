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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import megaleios.com.myseeds.Adapters.CampanhasAdapter;
import megaleios.com.myseeds.Adapters.InstitutionAdapter;
import megaleios.com.myseeds.Adapters.NewCampaignAdapter;
import megaleios.com.myseeds.Adapters.NewInstitutionsAdapter;
import megaleios.com.myseeds.Components.SmoothRecyclerView;
import megaleios.com.myseeds.Domains.Instituicao.Activity.InstituicaoActivity;
import megaleios.com.myseeds.Helpers.GridSpacingItemDecoration;
import megaleios.com.myseeds.Helpers.RecyclerItemClickListener;
import megaleios.com.myseeds.Models.Campaing;
import megaleios.com.myseeds.Models.Institution;
import megaleios.com.myseeds.R;
import megaleios.com.myseeds.Service.RequestService;
import megaleios.com.myseeds.Util.SessionManager;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class ExplorerFragment extends Fragment{
    JSONObject obj;

    private SmoothRecyclerView mCampanhas;
    private SmoothRecyclerView mInstituicao;

    JSONArray rootArray = new JSONArray();
    private CampanhasAdapter adapter;
    private NewInstitutionsAdapter adapterInstitution;

    List<Campaing> dataCampaing;
    List<Institution> dataInstitution;


    private ArrayList<Campaing> listFeed = new ArrayList<Campaing>();

    String JSONTEST = "{data.context: https://ahazou.search.windows.net/indexes('feeds')/$metadata#docs, value: [{@search.score: 1,},{@search.score: 1,}]}";
    private SessionManager sessionManager;
    private NewCampaignAdapter adapterNew;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_explorer, container, false);
        dataCampaing = new ArrayList<Campaing>();
        dataInstitution = new ArrayList<Institution>();

        GridLayoutManager linearLayoutManager
                = new GridLayoutManager (getContext(),1,GridLayoutManager.HORIZONTAL,false);
        mCampanhas= (SmoothRecyclerView)view.findViewById(R.id.campanhas_list);
        mCampanhas.setLayoutManager(linearLayoutManager);
        mCampanhas.addItemDecoration(new GridSpacingItemDecoration(12, dpToPx(8), true));
        mCampanhas.setItemAnimator(new DefaultItemAnimator());
        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(mCampanhas);
        sessionManager = new SessionManager(getContext());

        GridLayoutManager linearLayoutManagerInstituicao
                = new GridLayoutManager (getContext(),1,GridLayoutManager.VERTICAL,false);
        mInstituicao= (SmoothRecyclerView)view.findViewById(R.id.instituicao_list);
        mInstituicao.setLayoutManager(linearLayoutManagerInstituicao);
        mInstituicao.addItemDecoration(new GridSpacingItemDecoration(12, dpToPx(8), true));
        mInstituicao.setItemAnimator(new DefaultItemAnimator());
        mInstituicao.addOnItemTouchListener( new RecyclerItemClickListener(getContext(), mInstituicao ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        TextView id = (TextView) view.findViewById(R.id.institutionID);
                        String institutionID = id.getText().toString();
                        Intent i = new Intent(getActivity(), InstituicaoActivity.class);
                        i.putExtra("id", institutionID);
                        startActivity(i);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        mCampanhas.addOnItemTouchListener( new RecyclerItemClickListener(getContext(), mCampanhas ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent i = new Intent(getActivity(), InstituicaoActivity.class);
                        startActivity(i);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

//        card2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), InstituicaoActivity.class);
//                startActivity(i);
//            }
//        });
        getFeedNew(sessionManager);
        return view;
    }


    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
//
   private void updateDisplay(JsonArray jsonData) throws JSONException, IOException {
//        JSONObject rootObj = new JSONObject(jsonData);

//        rootArray = rootObj.getJSONArray("value");

        adapter = new CampanhasAdapter(getActivity(), dataCampaing, ExplorerFragment.this);
        mCampanhas.setAdapter(adapter);
       adapter.notifyDataSetChanged();
    }

    private void updateDisplayNew(String jsonData) throws JSONException, IOException {

        Log.i("Azu1",jsonData);

        JSONObject rootObj = new JSONObject(jsonData);
        JSONObject data = rootObj.getJSONObject("data");
        JSONArray campaing = data.getJSONArray("campaigns");
        JSONArray institutions = data.getJSONArray("institutions");

        Log.i("Azu1",campaing.toString());

//        rootArray = rootObj.getJSONArray("value");
//
          adapterNew = new NewCampaignAdapter(getContext(), campaing);
         adapterInstitution = new NewInstitutionsAdapter(getContext(), institutions);

//
//        mSuggestionListViewBemEstar.setFocusable(false);
//
          mCampanhas.setAdapter(adapterNew);
          mInstituicao.setAdapter(adapterInstitution);

    }
    public void getFeedNew(SessionManager sessionManager) {
        RequestService.getFeed(getActivity(), "0", 1, new RequestService.CallbackDefault() {
            @Override
            public void onSuccess(JsonObject result) {
                Gson googleJson = new Gson();
                //                    JSONObject jsonObj = new JSONObject(result.toString());
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
