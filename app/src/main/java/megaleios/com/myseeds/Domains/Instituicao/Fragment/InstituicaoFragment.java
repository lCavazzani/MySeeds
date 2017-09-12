package megaleios.com.myseeds.Domains.Instituicao.Fragment;
import android.content.res.Configuration;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

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
        final EditText button = (EditText) view.findViewById(R.id.button);
        final EditText contribuir_button = (EditText) view.findViewById(R.id.contribuir_button);
        final EditText button_contribuir = (EditText) view.findViewById(R.id.button_contribuir);
        final LinearLayout finish_contribuir = (LinearLayout) view.findViewById(R.id.finish_contribuir);
        final LinearLayout add_more = (LinearLayout) view.findViewById(R.id.add_more);
        button.setRawInputType(Configuration.KEYBOARD_12KEY);
        contribuir_button.setRawInputType(Configuration.KEYBOARD_12KEY);
        button_contribuir.setRawInputType(Configuration.KEYBOARD_12KEY);
        button.addTextChangedListener(new TextWatcher(){
            DecimalFormat dec = new DecimalFormat("0.00");
            @Override
            public void afterTextChanged(Editable arg0) {
                if(!arg0.toString().equals(current)){
                    button.removeTextChangedListener(this);

                    String cleanString = arg0.toString().replaceAll("[$,.]", "");

                    double parsed = Double.parseDouble(cleanString);
                    String formatted = NumberFormat.getCurrencyInstance().format((parsed/100));

                    current = formatted;
                    button.setText(formatted);
                    button.setSelection(formatted.length());

                    button.addTextChangedListener(this);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                add_more.setVisibility(View.VISIBLE);
            }
            private String current = "";
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals(current)){
                    button.removeTextChangedListener(this);

                    String cleanString = s.toString().replaceAll("[$,.]", "");

                    double parsed = Double.parseDouble(cleanString);
                    String formatted = NumberFormat.getCurrencyInstance().format((parsed/100));

                    current = formatted;
                    button.setText(formatted);
                    button.setSelection(formatted.length());

                    button.addTextChangedListener(this);
                }
            }
        });
        contribuir_button.addTextChangedListener(new TextWatcher(){
            DecimalFormat dec = new DecimalFormat("0.00");
            @Override
            public void afterTextChanged(Editable arg0) {
                if(!arg0.toString().equals(current)){
                    button.removeTextChangedListener(this);

                    String cleanString = arg0.toString().replaceAll("[$,.]", "");

                    double parsed = Double.parseDouble(cleanString);
                    String formatted = NumberFormat.getCurrencyInstance().format((parsed/100));

                    current = formatted;
                    contribuir_button.setText(formatted);
                    contribuir_button.setSelection(formatted.length());

                    contribuir_button.addTextChangedListener(this);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                add_more.setVisibility(View.VISIBLE);
            }
            private String current = "";
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
        button_contribuir.addTextChangedListener(new TextWatcher(){
            DecimalFormat dec = new DecimalFormat("0.00");
            @Override
            public void afterTextChanged(Editable arg0) {
                if(!arg0.toString().equals(current)){
                    button.removeTextChangedListener(this);

                    String cleanString = arg0.toString().replaceAll("[$,.]", "");

                    double parsed = Double.parseDouble(cleanString);
                    String formatted = NumberFormat.getCurrencyInstance().format((parsed/100));

                    current = formatted;
                    button_contribuir.setText(formatted);
                    button_contribuir.setSelection(formatted.length());

                    button_contribuir.addTextChangedListener(this);
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

            }
        });
        button.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    finish_contribuir.setVisibility(View.VISIBLE);
                    add_more.setVisibility(View.VISIBLE);
                }
            }
        });
        contribuir_button.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    finish_contribuir.setVisibility(View.VISIBLE);
                    add_more.setVisibility(View.VISIBLE);
                }
            }
        });
        button_contribuir.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    finish_contribuir.setVisibility(View.VISIBLE);
                    add_more.setVisibility(View.VISIBLE);
                }
            }
        });




        return view;


    }
}
