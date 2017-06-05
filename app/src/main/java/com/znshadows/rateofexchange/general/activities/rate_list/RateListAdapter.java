package com.znshadows.rateofexchange.general.activities.rate_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.znshadows.rateofexchange.R;
import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;

import java.util.List;


/**
 * Created by Evolution on 3/9/17.
 */

public class RateListAdapter extends RecyclerView.Adapter<RateListAdapter.ViewHolder> {

    private Context context;
    List<UnifiedBankResponce> dataList;

    /**
     * ViewHolder class will contain row view for RecyclerView
     */


    public RateListAdapter(Context context, List<UnifiedBankResponce> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_rate_list, null);

        return new ViewHolder(view);

    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.code.setText(dataList.get(position).getCode());
        holder.rateBuy.setText("" + dataList.get(position).getBuy());
        if(dataList.get(position).getSale() == UnifiedBankResponce.NO_VALUE){
             holder.rateSale.setVisibility(View.GONE);
        } else {
            holder.rateSale.setText("" + dataList.get(position).getSale());
        }
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {


        TextView code;
        TextView rateBuy;
        TextView rateSale;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            code = (TextView) itemView.findViewById(R.id.code);
            rateBuy = (TextView) itemView.findViewById(R.id.rateBuy);
            rateSale = (TextView) itemView.findViewById(R.id.rateSale);
        }
    }
}
