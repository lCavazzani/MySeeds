package megaleios.com.myseeds.Domains.Main.Fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import megaleios.com.myseeds.Adapters.CampanhasAdapter;
import megaleios.com.myseeds.Components.SmoothRecyclerView;
import megaleios.com.myseeds.Domains.Instituicao.Activity.InstituicaoActivity;
import megaleios.com.myseeds.Domains.Register.Activity.RegisterActivity;
import megaleios.com.myseeds.Helpers.GridSpacingItemDecoration;
import megaleios.com.myseeds.Helpers.RecyclerItemClickListener;
import megaleios.com.myseeds.R;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class ExplorerFragment extends Fragment{
    JSONObject obj;

    private SmoothRecyclerView mCampanhas;
    JSONArray rootArray = new JSONArray();
    private CampanhasAdapter adapter;
    String JSONTEST = "{data.context: https://ahazou.search.windows.net/indexes('feeds')/$metadata#docs, value: [{@search.score: 1,},{@search.score: 1,}]}";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_explorer, container, false);

        GridLayoutManager linearLayoutManager
                = new GridLayoutManager (getContext(),1,GridLayoutManager.HORIZONTAL,false);
        mCampanhas= (SmoothRecyclerView)view.findViewById(R.id.campanhas_list);
        mCampanhas.setLayoutManager(linearLayoutManager);
        mCampanhas.addItemDecoration(new GridSpacingItemDecoration(12, dpToPx(4), true));
        mCampanhas.setItemAnimator(new DefaultItemAnimator());

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

        CardView card = (CardView) view.findViewById(R.id.instituicao_list);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), InstituicaoActivity.class);
                startActivity(i);
            }
        });
//        card2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), InstituicaoActivity.class);
//                startActivity(i);
//            }
//        });
        String json = "{\"value\":[{\"id\": \"Campanha do agasalho\"},{\"id\": \"Campanha festa junina\"},{\"id\": \"Campanha de Natal\"},{\"id\": \"Campanha ação de graças\"}]}";

        try {
            obj = new JSONObject(json);

            Log.d("My App", obj.toString());

        } catch (Throwable tx) {
            Log.e("My App", "Could not parse malformed JSON: \"" + json + "\"");
        }
        try {
            updateDisplay(obj.toString());
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return view;
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
//
   private void updateDisplay(String jsonData) throws JSONException, IOException {
        JSONObject rootObj = new JSONObject(jsonData);

        rootArray = rootObj.getJSONArray("value");

        adapter = new CampanhasAdapter(getContext(), rootArray);

        mCampanhas.setFocusable(false);

        mCampanhas.setAdapter(adapter);
    }
//   public void Campanhas (String search) {
//        //     pd = ProgressDialog.show(getContext(), "Carregando Pacotes...",
//        //   "", true);
//        String requestUrl = "";
//        JsonObject json = new JsonObject();
//        json.addProperty("search", "*");
//        json.addProperty("top", "10");
//
//        final JSONObject props = new JSONObject();
//
//        String jsonString = json.toString();
//        RequestBody body = RequestBody.create(JSON, jsonString);
//
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder()
//                    .addHeader("api-key", "")
//                    .addHeader("Content-Type", "application/json")
//                    .post(body)
//                    .url(requestUrl)
//                    .build();
//
//            Log.i("Azure",requestUrl);
//
//            Call call = client.newCall(request);
//            call.enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    //alertUserAboutError();
//                    Log.i("Azure", "Fail");
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    try {
//                        final String jsonData = response.body().string();
//                        if (response.isSuccessful()) {
//
//                            if(getActivity()==null)
//                                return;
//
//                            getActivity().runOnUiThread(new Runnable() {
//
//                                @Override
//                                public void run() {
//                                    try {
//                                        Log.i("Azure1212", jsonData);
//                                        updateDisplay(jsonData);
////                                        pd.dismiss();
//                                    } catch (JSONException | IOException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            });
//                        } else {
//                            //alertUserAboutError();
//                        }
//                    } catch (IOException ignored) {
//                    }
//                }
//            });
//
//
//    }

}
