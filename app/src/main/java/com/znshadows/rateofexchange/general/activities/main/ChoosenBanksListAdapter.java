package com.znshadows.rateofexchange.general.activities.main;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.R;
import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.general.models.ChoosenBank;
import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;
import com.znshadows.rateofexchange.mvp.presenters.IMainPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by kostya on 30.05.2017.
 */

public class ChoosenBanksListAdapter extends RecyclerView.Adapter<ChoosenBanksListAdapter.ViewHolder> {
    private OnClickListener onItemClickListener = null;
    private Context context;
    private List<ChoosenBank> dataList;
    @Inject
    IMainPresenter presenter;
    interface OnClickListener {
        void onClick(BANKS bank);
    }

    /**
     * ViewHolder class will contain row view for RecyclerView
     */


    public ChoosenBanksListAdapter(Context context, List<ChoosenBank> dataList) {
        App.getAppComponent().inject(this);
        this.context = context;
        this.dataList = dataList;
    }

    public ChoosenBanksListAdapter setOnItemClickListener(OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
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

        View view = LayoutInflater.from(context).inflate(R.layout.item_banks_list, null);

        return new ViewHolder(view);

    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChoosenBank choosenBank = dataList.get(position);
        holder.logo.setImageResource(context.getResources().obtainTypedArray(R.array.banks_logo).getResourceId(choosenBank.getBank().ordinal(), -1));
        holder.name.setText(context.getResources().getStringArray(R.array.bankNames)[choosenBank.getBank().ordinal()]);
        List<String> curencies = dataList.get(position).getCurencies();
        if(curencies.size() > 0){
            for (int i = 0; i < curencies.size(); i++) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_rate_list, null);
                holder.addChild(view);
                TextView code = (TextView)view.findViewById(R.id.code);
                code.setText(curencies.get(i));
            }
            holder.startChildsProgressBar();
            presenter.getBankRates(choosenBank,(rates)->{

                for (int i = 0; i < holder.childs.size(); i++) {
                    View view = holder.childs.get(i);
                    ((TextView) view.findViewById(R.id.code)).setText(rates.get(i).getCode());
                    ((TextView) view.findViewById(R.id.rateBuy)).setText(""+rates.get(i).getBuy());

                    if(rates.get(i).getSale() == UnifiedBankResponce.NO_VALUE){
                        view.findViewById(R.id.rateSale).setVisibility(GONE);
                    } else {
                        ((TextView) view.findViewById(R.id.rateSale)).setText(""+rates.get(i).getSale());
                    }
                }
                holder.finishChildsProgressBar();
            });
        holder.showState();
        }
        //holder.itemDataLoadingProgress.setVisibility(VISIBLE);

        //holder.code.setText(choosenBank.getCurency());
        //holder.rate.setText(choosenBank.getCurency());
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener((v) -> {
                onItemClickListener.onClick(choosenBank.getBank());
            });
        }
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        private LinearLayout childsHolder;
        ImageView logo;
        TextView name;
        ImageView arrow;
        List<View> childs = new ArrayList<>();
        private boolean isOpen = false;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            this.itemView = itemView;
            childsHolder = (LinearLayout) itemView.findViewById(R.id.childsHolder);
            logo = (ImageView) itemView.findViewById(R.id.logo);
            name = (TextView) itemView.findViewById(R.id.name);
            arrow = (ImageView) itemView.findViewById(R.id.arrow);

            showState();
            arrow.setOnClickListener((v) -> {
                isOpen = !isOpen;
                showState();
            });
        }
public void addChild(View view){
    childs.add(view);
    childsHolder.addView(view);
}

        private void showState(){
            if (isOpen) {
                open();
            } else {
                close();
            }
        }

        private void close() {
            animateDrawable();

            for (int i = 0; i < childs.size(); i++) {
                childs.get(i).setVisibility(GONE);
            }
        }

        private void open() {
            animateReverseDrawable();

            for (int i = 0; i < childs.size(); i++) {
                childs.get(i).setVisibility(VISIBLE);
            }

        }

        private void animateDrawable() {
            int MAX_LEVEL = 10000;
            Drawable drawable = arrow.getDrawable();
            ObjectAnimator anim = ObjectAnimator.ofInt(drawable, "level", 0, MAX_LEVEL);
            anim.start();
        }

        private void animateReverseDrawable() {
            int MAX_LEVEL = 10000;
            Drawable drawable = arrow.getDrawable();
            ObjectAnimator anim = ObjectAnimator.ofInt(drawable, "level", MAX_LEVEL, 0);
            anim.start();


        }

        public void startChildsProgressBar() {
            for (View child : childs) {
                child.findViewById(R.id.itemDataLoadingProgress).setVisibility(VISIBLE);
            }
        }
        public void finishChildsProgressBar() {
            for (View child : childs) {
                child.findViewById(R.id.itemDataLoadingProgress).setVisibility(GONE);
            }
        }
    }

}

