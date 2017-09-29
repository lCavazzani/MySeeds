package megaleios.com.myseeds.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

import megaleios.com.myseeds.Domains.Main.Fragment.ExplorerFragment;
import megaleios.com.myseeds.Models.Institution;
import megaleios.com.myseeds.R;
import megaleios.com.myseeds.Util.SessionManager;


/**
 * Created by user on 14/07/17.
 */

public class InstitutionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Institution> dataInstitution;
    private Context mContext;
    public static final int ITEM_TYPE_COURSE = 2;
    private Institution feedInstitution;
    private SessionManager sessionManager;
    private ExplorerFragment fragment;


    public InstitutionAdapter(Context context, List<Institution> data, ExplorerFragment feedFragment) {
        this.dataInstitution = data;
        mContext = context;
        this.fragment = feedFragment;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        View view = null;
        sessionManager = new SessionManager(mContext);

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_institution_explorer, parent, false);
        return new InstitutionAdapter.ViewHolder(view);



    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int itemType = getItemViewType(position);

        feedInstitution = dataInstitution.get(position);
        ViewHolder post = (ViewHolder) holder;
        post.feed = feedInstitution;
    //    post.textmy_int.setText((CharSequence) feedInstitution.getCompany());
     //   post.textView_int.setText(feedInstitution.getTotalContributions());
    }


    @Override
    public int getItemViewType(int position) {
        return ITEM_TYPE_COURSE;
    }

    @Override
    public int getItemCount() {
        return dataInstitution.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Institution feed;
        ImageView menu;

        @BindView(R.id.textmy_int)
        TextView textmy_int;
        @BindView(R.id.textView_int)
        TextView textView_int;
        @BindView(R.id.qnt_campaing)
        TextView qnt_campaing;
        @BindView(R.id.textView2_int)
        TextView textView2_int;

        ViewHolder(View view) {
            super(view);


            ButterKnife.bind(this, view);
        }
    }

    private String getDate(long time) {
        Date date = new Date(time * 1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd/MM/yyyy "); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));

        return sdf.format(date);
    }

}
