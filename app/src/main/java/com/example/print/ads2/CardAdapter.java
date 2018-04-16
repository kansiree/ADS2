package com.example.print.ads2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {
    Context context;
    ArrayList<String> list;
    onClickAdapter item;

    interface onClickAdapter{
        public void onClickItem(int position,View view);
    }

    private onClickAdapter getItem() {
        return item;
    }

    private void setItem(onClickAdapter item) {
        this.item = item;
    }
    public void setItemClick(CardAdapter.onClickAdapter item){setItem(item);}

    public CardAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_main,parent,false);
        CardViewHolder viewHolder = new CardViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CardViewHolder holder, final int position) {
        holder.textView.setText(list.get(position));
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getItem().onClickItem(position,view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
