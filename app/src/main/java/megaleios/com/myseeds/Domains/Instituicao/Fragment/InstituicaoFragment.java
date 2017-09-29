package megaleios.com.myseeds.Domains.Instituicao.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
import java.util.Locale;

import megaleios.com.myseeds.Adapters.CampanhasAdapter;
import megaleios.com.myseeds.Adapters.NewCampaignAdapter;
import megaleios.com.myseeds.Adapters.NewInstitutionsAdapter;
import megaleios.com.myseeds.Adapters.OpenInstitutionAdapter;
import megaleios.com.myseeds.Components.SmoothRecyclerView;
import megaleios.com.myseeds.Domains.PaymentConfirm.Activity.PaymentConfirmActivity;
import megaleios.com.myseeds.Helpers.GridSpacingItemDecoration;
import megaleios.com.myseeds.Models.Campaing;
import megaleios.com.myseeds.Models.Institution;
import megaleios.com.myseeds.R;
import megaleios.com.myseeds.Service.RequestService;
import megaleios.com.myseeds.Util.CustomMaskUtil;
import megaleios.com.myseeds.Util.MaskType;
import megaleios.com.myseeds.Util.MaskUtil;
import megaleios.com.myseeds.Util.SessionManager;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class InstituicaoFragment extends Fragment{

    @RequiresApi(api = Build.VERSION_CODES.N)
    private SessionManager sessionManager;
    String mID;
    TextView textView3;
    TextView lorem;
    TextView textView_int;
    TextView number_campaign;
    TextView address;
    TextView limit;
    private SmoothRecyclerView mCampanhas;
    private OpenInstitutionAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_instituicao, container, false);

        Intent intent = getActivity().getIntent();
        mID = intent.getStringExtra("id");
        textView3 = (TextView) view.findViewById(R.id.textView3);
        lorem = (TextView) view.findViewById(R.id.lorem);
        textView_int = (TextView) view.findViewById(R.id.textView_int);
        number_campaign = (TextView) view.findViewById(R.id.number_campaign);
        address = (TextView) view.findViewById(R.id.adress);
        limit = (TextView) view.findViewById(R.id.limit);


        final EditText button_contribuir = (EditText) view.findViewById(R.id.button_contribuir);
        final LinearLayout finish_contribuir = (LinearLayout) view.findViewById(R.id.finish_contribuir);
        final LinearLayout add_more = (LinearLayout) view.findViewById(R.id.add_more);
        final TextView total_value = (TextView) view.findViewById(R.id.total_value);
        final TextView more_money_one = (TextView) view.findViewById(R.id.more_money_one);
        final TextView more_money_two = (TextView) view.findViewById(R.id.more_money_two);
        final TextView more_money_three = (TextView) view.findViewById(R.id.more_money_three);
        final Button button2 = (Button) view.findViewById(R.id.button2);
        final ScrollView scrollview = (ScrollView) view.findViewById(R.id.scrollview);
        int total_carrinho = 0;
        CheckBox check_terms = (CheckBox) view.findViewById(R.id.check_terms);
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        button_contribuir.setRawInputType(Configuration.KEYBOARD_12KEY);

        button_contribuir.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    String sum_value = total_value.getText().toString();
                    String sum_number  = sum_value.replaceAll("[^0-9]", "");
                    int final_int = Integer.parseInt(v.getText().toString())+ Integer.parseInt(sum_number);
                    total_value.setText(String.valueOf(final_int));
                }
                return false;
            }
        });
        button_contribuir.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
//                    String sum_value = total_value.getText().toString();
//                    String sum_number  = sum_value.replaceAll("[^0-9]", "");
//                    if(!button_contribuir.getText().equals("")){
//                        int final_int = Integer.parseInt(button_contribuir.getText().toString())- Integer.parseInt(sum_number);
//                    }
                    button_contribuir.setText("");
                    button_contribuir.setHint("0,00");
                    finish_contribuir.setVisibility(View.VISIBLE);
                    add_more.setVisibility(View.VISIBLE);
                } else {
                }
            }
        });
        button_contribuir.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        more_money_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sum_value = total_value.getText().toString();
                String sum_number  = sum_value.replaceAll("[^0-9]", "");
                String view_value = more_money_three.getText().toString();
                String view_number  = view_value.replaceAll("[^0-9]", "");
                int final_int = Integer.parseInt(view_number + sum_number);
                total_value.setText(String.valueOf(final_int));
            }
        });
        more_money_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sum_value = total_value.getText().toString();
                String sum_number  = sum_value.replaceAll("[^0-9]", "");
                String view_value = more_money_two.getText().toString();
                String view_number  = view_value.replaceAll("[^0-9]", "");
                int final_int = Integer.parseInt(view_number)+ Integer.parseInt(sum_number);
                total_value.setText(String.valueOf(final_int));
            }
        });
        more_money_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sum_value = total_value.getText().toString();
                String sum_number  = sum_value.replaceAll("[^0-9]", "");
                String view_value = more_money_one.getText().toString();
                String view_number  = view_value.replaceAll("[^0-9]", "");
                int final_int = Integer.parseInt(view_number)+ Integer.parseInt(sum_number);
                total_value.setText(String.valueOf(final_int));
            }
        });
        check_terms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    String sum_value = total_value.getText().toString();
                    String sum_number  = sum_value.replaceAll("[^0-9]", "");
                    int final_int = 2+ Integer.parseInt(sum_number);
                    total_value.setText(String.valueOf(final_int));
                }else{
                    String sum_value = total_value.getText().toString();
                    String sum_number  = sum_value.replaceAll("[^0-9]", "");
                    int final_int = Integer.parseInt(sum_number)-2;
                    total_value.setText(String.valueOf(final_int));
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PaymentConfirmActivity.class);
                startActivity(i);
                //WEBVIEW
            }
        });
        GridLayoutManager linearLayoutManager
                = new GridLayoutManager (getContext(),1,GridLayoutManager.VERTICAL,false);
        mCampanhas= (SmoothRecyclerView)view.findViewById(R.id.campaign_list);
        mCampanhas.setLayoutManager(linearLayoutManager);
        mCampanhas.addItemDecoration(new GridSpacingItemDecoration(12, dpToPx(8), true));
        mCampanhas.setItemAnimator(new DefaultItemAnimator());
        getFeedNew(sessionManager);
        return view;
    }

    public void getFeedNew(SessionManager sessionManager) {
        RequestService.getDetailsInstitution(getActivity(), mID, 1, new RequestService.CallbackDefault() {
            @Override
            public void onSuccess(JsonObject result) {
                Gson googleJson = new Gson();
                //                    JSONObject jsonObj = new JSONObject(result.toString());
                final String jsonData = result.toString();
                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        //pd.dismiss();
                        try {
                            updateDisplayNew(jsonData);
                        } catch (JSONException e) {
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

    private void updateDisplayNew(String jsonData) throws JSONException {

        JSONObject rootObj = new JSONObject(jsonData);
        JSONObject data = rootObj.getJSONObject("data");
        JSONArray campaing = data.getJSONArray("campaings");
        JSONArray institutions = data.getJSONArray("institutions");


        textView3.setText(data.getString("name"));
        lorem.setText(data.getString("description"));
        textView_int.setText(data.optString("totalContributions"));
        number_campaign.setText(data.optString("totalCampaigns"));
        address.setText(institutions.getJSONObject(0).getString("address"));
        limit.setText(data.optString("minimumContribution"));
        TextView lorem;
        TextView textView_int;
        TextView number_campaign;

//        rootArray = rootObj.getJSONArray("value");
////
        adapter = new OpenInstitutionAdapter(getContext(), campaing);
//        adapterInstitution = new NewInstitutionsAdapter(getContext(), institutions);
//
////
////        mSuggestionListViewBemEstar.setFocusable(false);
////
         mCampanhas.setAdapter(adapter);
//        mInstituicao.setAdapter(adapterInstitution);
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
