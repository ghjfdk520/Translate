package com.translate.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.translate.R;
import com.translate.bean.DictBean;
import com.translate.conf.Common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DongZ on 2015/10/13 0013.
 */
public class LoctAdapter extends RecyclerView.Adapter<LoctAdapter.ViewHolder>
{
    private boolean animateItems = true;
    private static final int ANIMATED_ITEMS_COUNT = 6;
    private int lastAnimatedPosition = -1;

    private List<DictBean> dictBeans ;

    public LoctAdapter() {
        dictBeans = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        runEnterAnimation(holder.itemView,position);
        holder.bindView(dictBeans.get(position));
    }


    public void setItems(List<DictBean> dictBeans) {
        this.dictBeans = dictBeans;
        lastAnimatedPosition = -1;

        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return dictBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        /**/

        private TextView org;
        private TextView phrases;

        public ViewHolder(View itemView) {
            super(itemView);
            org = (TextView) itemView.findViewById(R.id.org);
            phrases = (TextView) itemView.findViewById(R.id.phrases);
        }

        public void bindView(final DictBean dictBean){
            org.setText(dictBean.dict.getOrg());
            phrases.setText(dictBean.dict.getPhrases().get(0));
        }
    }
    private void runEnterAnimation(View view, int position) {
        if (!animateItems || position >= ANIMATED_ITEMS_COUNT - 1) {
            return;
        }

        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            view.setTranslationY(-Common.getInstance().screenHeight);
            view.animate()
                    .translationY(0)
                    .setStartDelay(100 * position)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setDuration(600)
                    .start();
        }
    }
    public void clearData(){
        dictBeans.clear();
        lastAnimatedPosition = -1;
        notifyDataSetChanged();
    }



}
