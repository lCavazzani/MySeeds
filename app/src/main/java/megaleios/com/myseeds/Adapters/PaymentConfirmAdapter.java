package megaleios.com.myseeds.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import megaleios.com.myseeds.Domains.PaymentConfirm.Activity.PaymentConfirmActivity;
import megaleios.com.myseeds.R;

public class PaymentConfirmAdapter extends BaseAdapter {
    String [] result;
    String [] result_value;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;
    public PaymentConfirmAdapter(PaymentConfirmActivity mainActivity, String[] prgmNameList, String[] prgmNameList_value) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        result_value=prgmNameList_value;
        context=mainActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView title;
        TextView value;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.listitem_payment_confirm,null);
        holder.title=(TextView) rowView.findViewById(R.id.title);
        holder.title.setText(result[position]);
        holder.value=(TextView) rowView.findViewById(R.id.value);
        holder.value.setText(result_value[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
                if(position==0){
                }
            }
        });
        return rowView;
    }

}