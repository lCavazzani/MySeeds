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

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Date;

import megaleios.com.myseeds.Models.ValueDonationCampaign;
import megaleios.com.myseeds.R;

/**
 * Created by leonardoCavazzani on 2/22/17.
 */

public class MyCardsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    JSONArray mPages;

    public MyCardsAdapter(Context context, JSONArray pages){
        mContext = context;
        mPages = pages;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.card_my_cards, parent, false);
        MyCardsAdapter.MyHolder holder=new MyCardsAdapter.MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        MyCardsAdapter.MyHolder myHolder= (MyCardsAdapter.MyHolder) holder;
        try {
            JSONObject mpackid = mPages.getJSONObject(position);
            myHolder.number_card.setText(mpackid.optString("number"));
            myHolder.name_card.setText(mpackid.optString("name"));
            myHolder.date_card.setText(mpackid.optString("expMonth")+"/"+mpackid.optString("expYear"));
            myHolder.flag.setText(mpackid.optString("flag"));

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

        TextView number_card;
        TextView name_card;
        TextView date_card;
        TextView flag;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            name_card = (TextView) itemView.findViewById(R.id.name_card);
            number_card = (TextView) itemView.findViewById(R.id.textView);
            date_card = (TextView) itemView.findViewById(R.id.date_card);
            flag = (TextView) itemView.findViewById(R.id.textmy);
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