package megaleios.com.myseeds.Adapters;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import megaleios.com.myseeds.R;

/**
 * Created by leonardoCavazzani on 2/22/17.
 */

public class NewCampaignAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    JSONArray mPages;




    public NewCampaignAdapter(Context context, JSONArray pages){
        mContext = context;
        mPages = pages;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.card_explorer, parent, false);
        MyHolder holder=new MyHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder= (MyHolder) holder;

        try {
            JSONObject mpackid = mPages.getJSONObject(position);
            String murl = mpackid.optString("name");
            String totalDonation = mpackid.optString("totalDonation");
            String price = mpackid.optString("price");
            myHolder.textmy.setText(murl);
            myHolder.textView.setText("R$ "+ totalDonation+" de "+price);
            myHolder.date_end.setText(mpackid.optString("endCampaign"));
            myHolder.textView2.setText(mpackid.getJSONObject("company").getString("name"));

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
        TextView textView2;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textmy = (TextView) itemView.findViewById(R.id.textmy);
            textView = (TextView) itemView.findViewById(R.id.textView);
            date_end = (TextView) itemView.findViewById(R.id.date_end);
            textView2 = (TextView) itemView.findViewById(R.id.textView2);

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