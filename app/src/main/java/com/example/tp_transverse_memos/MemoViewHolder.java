package com.example.tp_transverse_memos;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MemoViewHolder extends RecyclerView.ViewHolder
{

    // Vue intitulé mémo :
    private TextView textViewIntitule = null;


    /**
     * Constructeur.
     * @param itemView Vue item
     */
    public MemoViewHolder(View itemView)
    {
        super(itemView);
        textViewIntitule = itemView.findViewById(R.id.memo_intitule);
    }

    /**
     * Getter textView intitulé.
     * @return TextView intitulé
     */
    public TextView getTextViewIntitule()
    {
        return textViewIntitule;
    }

}