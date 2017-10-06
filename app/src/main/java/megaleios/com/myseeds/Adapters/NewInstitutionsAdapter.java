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

import megaleios.com.myseeds.R;

/**
 * Created by leonardoCavazzani on 2/22/17.
 */

public class NewInstitutionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    JSONArray mPages;




    public NewInstitutionsAdapter(Context context, JSONArray pages){
        mContext = context;
        mPages = pages;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.card_institution_explorer, parent, false);
        MyHolder holder=new MyHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder= (MyHolder) holder;

        try {
            JSONObject mpackid = mPages.getJSONObject(position);
            myHolder.textmy_int.setText(mpackid.getJSONObject("company").getString("name"));
            myHolder.textView_int.setText(mpackid.optString("totalContributions")+ " contribuições");
            myHolder.qnt_campaing.setText(mpackid.optString("totalCampaigns")+" campanhas");
            myHolder.textView2_int.setText(mpackid.getString("address"));
            myHolder.institutionID.setText(mpackid.getString("id"));
            Glide.with(mContext).load(mpackid.getJSONObject("company").optString("logo")).into(myHolder.imageView2);

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

        TextView textmy_int;
        TextView textView_int;
        TextView qnt_campaing;
        TextView textView2_int;
        TextView institutionID;
        ImageView imageView2;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textmy_int = (TextView) itemView.findViewById(R.id.textmy_int);
            textView_int = (TextView) itemView.findViewById(R.id.textView_int);
            qnt_campaing = (TextView) itemView.findViewById(R.id.qnt_campaing);
            textView2_int = (TextView) itemView.findViewById(R.id.textView2_int);
            institutionID = (TextView) itemView.findViewById(R.id.institutionID);
            imageView2 = (ImageView) itemView.findViewById(R.id.imageView2);
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