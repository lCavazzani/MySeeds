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

import java.util.Locale;

import megaleios.com.myseeds.Domains.PaymentConfirm.Activity.PaymentConfirmActivity;
import megaleios.com.myseeds.R;
import megaleios.com.myseeds.Util.CustomMaskUtil;
import megaleios.com.myseeds.Util.MaskType;
import megaleios.com.myseeds.Util.MaskUtil;

/**
 * Created by ulyssesboumann on 16/08/17.
 */

public class InstituicaoFragment extends Fragment{

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_instituicao, container, false);
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

        CheckBox check_terms = (CheckBox) view.findViewById(R.id.check_terms);
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        button_camp_1.setRawInputType(Configuration.KEYBOARD_12KEY);
        button_camp_2.setRawInputType(Configuration.KEYBOARD_12KEY);
        button_contribuir.setRawInputType(Configuration.KEYBOARD_12KEY);
//        button_camp_1.addTextChangedListener(new TextWatcher(){
//            DecimalFormat dec = new DecimalFormat("0.00");
//            @Override
//            public void afterTextChanged(final Editable arg0) {
//                if(!arg0.toString().equals(current)){
//                            String bottom_value = total_value.getText().toString();
//                            String number_total  = bottom_value.replaceAll("[^0-9]", "");//remove $
//                            String value_arg = arg0.toString();
//                            String number_arg  = value_arg.replaceAll("[^0-9]", "");//remove $
//
//                            int final_int = Integer.parseInt(number_arg)+ Integer.parseInt(number_total);
//                            total_value.setText(Integer.toString(final_int));
//                }
//            }
//            @Override
//            public void beforeTextChanged(CharSequence s, int start,
//                                          int count, int after) {
//                finish_contribuir.setVisibility(View.VISIBLE);
//                add_more.setVisibility(View.VISIBLE);
//            }
//            private String current = "";
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(!s.toString().equals(current)){
////                   +
//                }
//            }
//        });
//        button_camp_2.addTextChangedListener(new TextWatcher(){
//            DecimalFormat dec = new DecimalFormat("0.00");
//            @Override
//            public void afterTextChanged(Editable arg0) {
//                if(!arg0.toString().equals(current)){
//                    String finalvalue_total = total_value.getText().toString();
//                    String number_total  = finalvalue_total.replaceAll("[^0-9]", "");
//                    String finalvalue_arg = arg0.toString();
//                    String number_arg  = finalvalue_arg.replaceAll("[^0-9]", "");
//
//                    int final_int = Integer.parseInt(number_arg)+ Integer.parseInt(number_total);
//                    NumberFormat format = NumberFormat.getCurrencyInstance();
//                    total_value.setText(Integer.toString(final_int));
//                }
//
//            }
//            @Override
//            public void beforeTextChanged(CharSequence s, int start,
//                                          int count, int after) {
//                finish_contribuir.setVisibility(View.VISIBLE);
//                add_more.setVisibility(View.VISIBLE);
//            }
//            private String current = "";
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(!s.toString().equals(current)){
////                   +
//                }
//            }
//        });
        button_contribuir.addTextChangedListener(new TextWatcher(){
            DecimalFormat dec = new DecimalFormat("0.00");
            @Override
            public void afterTextChanged(Editable arg0) {
                if(!arg0.toString().equals(current)){
                    String finalvalue_total = total_value.getText().toString();
                    String number_total  = finalvalue_total.replaceAll("[^0-9]", "");
                    String finalvalue_arg = button_contribuir.getText().toString();
                    String number_arg  = finalvalue_arg.replaceAll("[^0-9]", "");

                    if(!number_arg.equals("")&&!number_total.equals("")){
                        int final_int = Integer.parseInt(number_arg +number_total);
                        NumberFormat format = NumberFormat.getCurrencyInstance();
                        total_value.setText(Integer.toString(final_int));
                    }
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

        button_contribuir.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    finish_contribuir.setVisibility(View.VISIBLE);
                    add_more.setVisibility(View.VISIBLE);
                    scrollview.smoothScrollTo(0,view.getTop());
                }
                else{
                    String finalvalue_total = total_value.getText().toString();
                    String number_total  = finalvalue_total.replaceAll("[^0-9]", "");
                    String finalvalue_arg = button_contribuir.getText().toString();
                    String number_arg  = finalvalue_arg.replaceAll("[^0-9]", "");

                    if(!number_arg.equals("")&&!number_total.equals("")){
                        int final_int = Integer.parseInt(number_arg)+ Integer.parseInt(number_total);
                        NumberFormat format = NumberFormat.getCurrencyInstance();
                        total_value.setText(Integer.toString(final_int));
                    }
                }
            }
        });
        button_contribuir.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String finalvalue_total = total_value.getText().toString();
                    String number_total  = finalvalue_total.replaceAll("[^0-9]", "");
                    String finalvalue_arg = button_contribuir.getText().toString();
                    String number_arg  = finalvalue_arg.replaceAll("[^0-9]", "");

                    if(!number_arg.equals("")&&!number_total.equals("")){
                        int final_int = Integer.parseInt(number_arg)+ Integer.parseInt(number_total);
                        NumberFormat format = NumberFormat.getCurrencyInstance();
                        total_value.setText(Integer.toString(final_int));
                    }
                }
                return false;
            }
        });

        button_camp_1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    finish_contribuir.setVisibility(View.VISIBLE);
                    scrollview.smoothScrollTo(0,view.getTop());
                }
                else{
                    String finalvalue_total = total_value.getText().toString();
                    String number_total  = finalvalue_total.replaceAll("[^0-9]", "");
                    String finalvalue_arg = button_camp_1.getText().toString();
                    String number_arg  = finalvalue_arg.replaceAll("[^0-9]", "");

                    if(!number_arg.equals("")&&!number_total.equals("")){
                        int final_int = Integer.parseInt(number_arg)+ Integer.parseInt(number_total);
                        NumberFormat format = NumberFormat.getCurrencyInstance();
                        total_value.setText(Integer.toString(final_int));
                    }
                }
            }
        });
        button_camp_1.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String finalvalue_total = total_value.getText().toString();
                    String number_total  = finalvalue_total.replaceAll("[^0-9]", "");
                    String finalvalue_arg = button_camp_1.getText().toString();
                    String number_arg  = finalvalue_arg.replaceAll("[^0-9]", "");

                    if(!number_arg.equals("")&&!number_total.equals("")){
                        int final_int = Integer.parseInt(number_arg)+ Integer.parseInt(number_total);
                        NumberFormat format = NumberFormat.getCurrencyInstance();
                        total_value.setText(Integer.toString(final_int));
                    }
                }
                return false;
            }
        });

        button_camp_2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    finish_contribuir.setVisibility(View.VISIBLE);
                    scrollview.smoothScrollTo(0,view.getTop());
                }
                else{
                    String finalvalue_total = total_value.getText().toString();
                    String number_total  = finalvalue_total.replaceAll("[^0-9]", "");
                    String finalvalue_arg = button_camp_2.getText().toString();
                    String number_arg  = finalvalue_arg.replaceAll("[^0-9]", "");

                    if(!number_arg.equals("")&&!number_total.equals("")){
                        int final_int = Integer.parseInt(number_arg)+ Integer.parseInt(number_total);
                        NumberFormat format = NumberFormat.getCurrencyInstance();
                        total_value.setText(Integer.toString(final_int));
                    }
                }
            }
        });
        button_camp_2.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String finalvalue_total = total_value.getText().toString();
                    String number_total  = finalvalue_total.replaceAll("[^0-9]", "");
                    String finalvalue_arg = button_camp_2.getText().toString();
                    String number_arg  = finalvalue_arg.replaceAll("[^0-9]", "");

                    if(!number_arg.equals("")&&!number_total.equals("")){
                        int final_int = Integer.parseInt(number_arg)+ Integer.parseInt(number_total);
                        NumberFormat format = NumberFormat.getCurrencyInstance();
                        total_value.setText(Integer.toString(final_int));
                    }
                }
                return false;
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
        return view;
    }


}
