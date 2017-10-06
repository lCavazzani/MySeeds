package megaleios.com.myseeds.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import megaleios.com.myseeds.Domains.Main.Fragment.ExplorerFragment;
import megaleios.com.myseeds.Models.Campaing;
import megaleios.com.myseeds.R;
import megaleios.com.myseeds.Util.SessionManager;

import static android.R.attr.fragment;
import static android.R.attr.id;

/**
 * Created by user on 14/07/17.
 */

public class CampanhasAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Campaing> data;
    private Context mContext;
    public static final int ITEM_TYPE_POST = 0;
    public static final int ITEM_TYPE_EVENT = 1;
    public static final int ITEM_TYPE_COURSE = 2;
    private Campaing feed;
    private SessionManager sessionManager;
    private ExplorerFragment fragment;


    public CampanhasAdapter(Context context, List<Campaing> data, ExplorerFragment feedFragment) {
        this.data = data;
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

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_explorer, parent, false);
            return new CampanhasAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int itemType = getItemViewType(position);

            feed = data.get(position);
            ViewHolder post = (ViewHolder) holder;
            post.feed = feed;
            post.profileName.setText(feed.getName());
    }


    @Override
    public int getItemViewType(int position) {

        return ITEM_TYPE_COURSE;

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        protected Campaing feed;
        protected ImageView menu;




        @BindView(R.id.textmy)
        TextView profileName;



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
