package com.translate.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.translate.R;
import com.translate.bean.DictBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DongZ on 2015/10/13 0013.
 */
public class LoctAdapter extends RecyclerView.Adapter<LoctAdapter.ViewHolder>
{
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
        holder.bindView(dictBeans.get(position));
    }


    public void setItems(List<DictBean> dictBeans) {
        this.dictBeans = dictBeans;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return dictBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView org;
        private TextView phrases;

        public ViewHolder(View itemView) {
            super(itemView);
            org = (TextView) itemView.findViewById(R.id.org);
            phrases = (TextView) itemView.findViewById(R.id.phrases);
        }

        public void bindView(final DictBean dictBean){
            org.setText(dictBean.dict.getOrg());
            phrases.setText(dictBean.dict.getPhrases());
        }
    }
}
