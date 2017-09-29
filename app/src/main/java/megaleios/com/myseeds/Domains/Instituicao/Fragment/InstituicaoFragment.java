package megaleios.com.myseeds.Domains.Instituicao.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import megaleios.com.myseeds.Adapters.NewCampaignAdapter;
import megaleios.com.myseeds.Adapters.NewInstitutionsAdapter;
import megaleios.com.myseeds.Domains.PaymentConfirm.Activity.PaymentConfirmActivity;
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_instituicao, container, false);

        Intent intent = getActivity().getIntent();
        mID = intent.getStringExtra("id");
        textView3 = (TextView) view.findViewById(R.id.textView3);
        lorem = (TextView) view.findViewById(R.id.lorem);

        final EditText button_camp_1 = (EditText) view.findViewById(R.id.button);
        final EditText button_camp_2 = (EditText) view.findViewById(R.id.contribuir_button);
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

        button_camp_1.setRawInputType(Configuration.KEYBOARD_12KEY);
        button_camp_2.setRawInputType(Configuration.KEYBOARD_12KEY);
        button_contribuir.setRawInputType(Configuration.KEYBOARD_12KEY);

        button_camp_1.addTextChangedListener(new TextWatcher(){
            //DecimalFormat dec = new DecimalFormat("0.00");
            @Override
            public void afterTextChanged(final Editable arg0) {
                if(!arg0.toString().equals(current)){
//                            String bottom_value = total_value.getText().toString();
//                            String number_total  = bottom_value.replaceAll("[^0-9]", "");//remove $
//                            String value_arg = arg0.toString();
//                            String number_arg  = value_arg.replaceAll("[^0-9]", "");//remove $
//
//                            int final_int = Integer.parseInt(number_arg)+ Integer.parseInt(number_total);
//                            total_value.setText(Integer.toString(final_int));
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                finish_contribuir.setVisibility(View.VISIBLE);
                add_more.setVisibility(View.VISIBLE);
            }
            private String current = "";
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals(current)){
//                   +
                }
            }
        });

        more_money_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sum_value = total_value.getText().toString();
                String sum_number  = sum_value.replaceAll("[^0-9]", "");
                String view_value = more_money_three.getText().toString();
                String view_number  = view_value.replaceAll("[^0-9]", "");
                int final_int = Integer.parseInt(view_number)+ Integer.parseInt(sum_number);
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
            }
        });
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
      //  JSONArray campaing = data.getJSONArray("campaigns");
        JSONArray institutions = data.getJSONArray("institutions");

        textView3.setText(data.getString("name"));
        lorem.setText(data.getString("description"));
        TextView lorem;
        TextView textView_int;
        TextView number_campaign;

//        rootArray = rootObj.getJSONArray("value");
////
//        adapter = new NewCampaignAdapter(getContext(), campaing);
//        adapterInstitution = new NewInstitutionsAdapter(getContext(), institutions);
//
////
////        mSuggestionListViewBemEstar.setFocusable(false);
////
//        mCampanhas.setAdapter(adapterNew);
//        mInstituicao.setAdapter(adapterInstitution);
    }
}
