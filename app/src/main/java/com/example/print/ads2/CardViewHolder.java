package com.example.print.ads2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CardViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    RelativeLayout card;
    public CardViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.card_text);
        card = itemView.findViewById(R.id.card_page);
    }
}
