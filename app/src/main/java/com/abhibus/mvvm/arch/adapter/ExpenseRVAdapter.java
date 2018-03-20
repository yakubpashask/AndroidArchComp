package com.abhibus.mvvm.arch.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abhibus.mvvm.arch.R;
import com.abhibus.mvvm.arch.db.ExpenseEntity;

import java.util.List;

/**
 * Created by yakub on 19/03/18.
 */

public class ExpenseRVAdapter extends RecyclerView.Adapter<ExpenseRVAdapter.ViewHolder> {


    private List<ExpenseEntity> entities;

    public List<ExpenseEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<ExpenseEntity> entities) {
        this.entities = entities;
    }

    public ExpenseRVAdapter(List<ExpenseEntity> entities) {
        this.entities = entities;
    }

    @Override
    public ExpenseRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        if(entities!=null && entities.getValue()!=null)
        holder.titleView.setText(entities.get(position).getTitle());
        holder.amountView.setText(""+entities.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
//        if(entities!=null && entities.getValue()!=null)
            return entities.size();
//        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleView;
        TextView amountView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.titleTextView);
            amountView = itemView.findViewById(R.id.amountTextView);

        }
    }
}
