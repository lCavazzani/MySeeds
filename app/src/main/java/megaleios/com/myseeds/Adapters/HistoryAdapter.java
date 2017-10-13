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

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import megaleios.com.myseeds.R;

/**
 * Created by leonardoCavazzani on 2/22/17.
 */

public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    JSONArray mPages;




    public HistoryAdapter(Context context, JSONArray pages){
        mContext = context;
        mPages = pages;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.card_history, parent, false);
        MyHolder holder=new MyHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder= (MyHolder) holder;

        try {
            JSONObject mpackid = mPages.getJSONObject(position);
            myHolder.value_history.setText("R$ "+ mpackid.getString("totalDonationAmount"));
            SimpleDateFormat format = new SimpleDateFormat("MM/yy");
            Date data = new Date(Long.parseLong(mpackid.optString("datePayment")) * 1000L);
            myHolder.data_history.setText(format.format(data).toString());
            myHolder.capaimg_name.setText(mpackid.getString("institutionName"));
            myHolder.historyId.setText(mpackid.getString("id"));


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

        TextView value_history;
        TextView data_history;
        TextView capaimg_name;
        TextView historyId;


        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            capaimg_name = (TextView) itemView.findViewById(R.id.capaimg_name);
            historyId = (TextView) itemView.findViewById(R.id.historyId);
            value_history = (TextView) itemView.findViewById(R.id.textView9);
            data_history = (TextView) itemView.findViewById(R.id.textView8);
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