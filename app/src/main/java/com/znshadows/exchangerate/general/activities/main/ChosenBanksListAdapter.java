package com.znshadows.exchangerate.general.activities.main;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.znshadows.exchangerate.App;
import com.znshadows.exchangerate.R;
import com.znshadows.exchangerate.general.models.BANKS;
import com.znshadows.exchangerate.general.models.ChosenBank;
import com.znshadows.exchangerate.general.models.UnifiedBankResponse;
import com.znshadows.exchangerate.mvp.presenters.IMainPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Natali Zakharchenko on 30.05.2017.
 */

public class ChosenBanksListAdapter extends RecyclerView.Adapter<ChosenBanksListAdapter.ViewHolder> {
    private OnClickListener onItemClickListener = null;
    private Context context;
    private List<ChosenBank> dataList;
    @Inject
    IMainPresenter presenter;

    interface OnClickListener {
        void onClick(BANKS bank);
    }

    /**
     * ViewHolder class will contain row view for RecyclerView
     */


    public ChosenBanksListAdapter(Context context, List<ChosenBank> dataList) {
        App.getAppComponent().inject(this);
        this.context = context;
        this.dataList = dataList;
    }

    public ChosenBanksListAdapter setOnItemClickListener(OnClickListener onItemClickListener) {
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
        ChosenBank chosenBank = dataList.get(position);
        holder.logo.setImageResource(context.getResources().obtainTypedArray(R.array.banks_logo).getResourceId(chosenBank.getBank().ordinal(), -1));
        holder.name.setText(context.getResources().getStringArray(R.array.bankNames)[chosenBank.getBank().ordinal()]);

        List<String> curencies = dataList.get(position).getCurencies();
        if (curencies.size() > 0) {
            showCurrencies(holder, chosenBank, curencies);
        } else {
            holder.arrow.setVisibility(View.INVISIBLE);
        }
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener((v) -> {
                onItemClickListener.onClick(chosenBank.getBank());

            });
        }

    }

    private void showCurrencies(ViewHolder holder, ChosenBank chosenBank, List<String> curencies) {
        //Adding header
        holder.addChild(LayoutInflater.from(context).inflate(R.layout.item_rate_list, null));

        for (int i = 0; i < curencies.size(); i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_rate_list, null);
            holder.addChild(view);
            TextView code = view.findViewById(R.id.code);
            code.setText(curencies.get(i));
            ((TextView) view.findViewById(R.id.rateBuy)).setText("");
            ((TextView) view.findViewById(R.id.rateSale)).setText("");
        }
        holder.startChildsProgressBar();

        presenter.getBankRates(chosenBank, (rates) -> fillChildViewsWithData(holder, rates));

        holder.showState();
    }

    private void fillChildViewsWithData(ViewHolder holder, List<UnifiedBankResponse> rates) {
        //view with index of 0 is a header
        if(rates != null && rates.size() > 0) {
            for (int i = 1; i < holder.childs.size(); i++) {
                View view = holder.childs.get(i);
                //adjust rates index so header does not populates with data
                UnifiedBankResponse bankResponce = rates.get(i - 1);
                ((TextView) view.findViewById(R.id.code)).setText(bankResponce.getCode());
                ((TextView) view.findViewById(R.id.rateBuy)).setText("" + bankResponce.getBuy());

                if (bankResponce.getSale() == UnifiedBankResponse.NO_VALUE) {
                    view.findViewById(R.id.rateSale).setVisibility(GONE);
                } else {
                    ((TextView) view.findViewById(R.id.rateSale)).setText("" + bankResponce.getSale());
                }
            }
            holder.finishChildsProgressBar();
        }
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return dataList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        private LinearLayout childsHolder;
        ImageView logo;
        TextView name;
        ImageView arrow;
        List<View> childs = new ArrayList<>();
        private boolean isOpen = false;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            this.itemView = itemView;
            childsHolder = itemView.findViewById(R.id.childsHolder);
            logo = itemView.findViewById(R.id.logo);
            name = itemView.findViewById(R.id.name);
            arrow = itemView.findViewById(R.id.arrow);

            showState();
            arrow.setOnClickListener((v) -> {
                isOpen = !isOpen;
                showState();
            });
        }

        void addChild(View view) {
            childs.add(view);
            childsHolder.addView(view);
        }

        private void showState() {
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

        private void startChildsProgressBar() {
            for (View child : childs) {
                child.findViewById(R.id.itemDataLoadingProgress).setVisibility(VISIBLE);
            }
        }

        private void finishChildsProgressBar() {
            for (View child : childs) {
                child.findViewById(R.id.itemDataLoadingProgress).setVisibility(GONE);
            }
        }
    }

}

