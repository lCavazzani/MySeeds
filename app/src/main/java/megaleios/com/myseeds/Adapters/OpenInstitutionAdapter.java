package megaleios.com.myseeds.Adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import megaleios.com.myseeds.Domains.Instituicao.Activity.InstituicaoActivity;
import megaleios.com.myseeds.Domains.Instituicao.Fragment.InstituicaoFragment;
import megaleios.com.myseeds.Models.ValueDonationCampaign;
import megaleios.com.myseeds.R;

/**
 * Created by leonardoCavazzani on 2/22/17.
 */

public class OpenInstitutionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    JSONArray mPages;
    InstituicaoFragment fragment;




    public OpenInstitutionAdapter(Context context, JSONArray pages, InstituicaoFragment fragment){
        mContext = context;
        mPages = pages;
        this.fragment = fragment;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.card_campaing_donate, parent, false);
        OpenInstitutionAdapter.MyHolder holder=new OpenInstitutionAdapter.MyHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        OpenInstitutionAdapter.MyHolder myHolder= (OpenInstitutionAdapter.MyHolder) holder;

        try {
            JSONObject mpackid = mPages.getJSONObject(position);
            String murl = mpackid.optString("name");
            String totalDonation = mpackid.optString("totalDonation");
            String price = mpackid.optString("price");
            myHolder.textmy.setText(murl);
            myHolder.textView.setText("R$ "+ totalDonation+" de "+price);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
            Date data = new Date(Long.parseLong(mpackid.optString("endCampaign")) * 1000L);
            myHolder.date_end.setText(format.format(data).toString());
            myHolder.campaign_id.setText(mpackid.optString("id"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;// not using this
    }

    @Override
    public int getItemCount() {
        return mPages.length();
    }


    private static class ViewHolder{
        TextView textmy;
    }
    class MyHolder extends RecyclerView.ViewHolder{

        TextView textmy;
        TextView textView;
        TextView date_end;
        EditText button;
        TextView campaign_id;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textmy = (TextView) itemView.findViewById(R.id.textmy);
            textView = (TextView) itemView.findViewById(R.id.textView);
            date_end = (TextView) itemView.findViewById(R.id.date_end);
            button = (EditText) itemView.findViewById(R.id.button);
            campaign_id = (TextView) itemView.findViewById(R.id.campaign_id);


            button.setRawInputType(Configuration.KEYBOARD_12KEY);

            button.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {

                        ValueDonationCampaign user = ValueDonationCampaign.getInstance();
                        user.setCampanha1(v.getText().toString());
                        fragment.addvalueCampaign(v.getText().toString());
                    }
                    return false;
                }
            });
            button.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if (hasFocus) {
//                    String sum_value = total_value.getText().toString();
//                    String sum_number  = sum_value.replaceAll("[^0-9]", "");
//                    if(!button_contribuir.getText().equals("")){
//                        int final_int = Integer.parseInt(button_contribuir.getText().toString())- Integer.parseInt(sum_number);
//                    }
                        button.setText("");
                        button.setHint("R$ 0,00");
                        button.setVisibility(View.VISIBLE);
                       // fragment.showBottom();
                    } else {
                    }
                }
            });


        }

    }

    public JSONObject getItem(int position) {
        try {
            return mPages.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}