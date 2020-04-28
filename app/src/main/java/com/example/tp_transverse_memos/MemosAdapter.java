package com.example.tp_transverse_memos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp_transverse_memos.objects.MemoDTO;

import java.util.Collections;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MemosAdapter extends RecyclerView.Adapter<MemoViewHolder>{
    // Liste d'objets métier :
    private List<MemoDTO> listeMemos = null;


    /**
     * Constructeur.
     * @param listeMemos Liste de mémos
     */
    public MemosAdapter(List<MemoDTO> listeMemos)
    {
        this.listeMemos = listeMemos;
    }

    @Override
    public MemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View viewMemo = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memo, parent, false);
        return new MemoViewHolder(viewMemo);
    }

    @Override
    public void onBindViewHolder(MemoViewHolder holder, int position)
    {
        holder.getTextViewIntitule().setText(listeMemos.get(position).getIntitule());
    }

    @Override
    public int getItemCount()
    {
        return listeMemos.size();
    }

    /**
     * Ajout d'un mémo à la liste.
     * @param memo Mémo
     */
    public void ajouterMemo(MemoDTO memo)
    {
        listeMemos.add(0, memo);
        notifyItemInserted(0);
    }

    public MemoDTO getItem(int position){
        return listeMemos.get(position);
    }

    public boolean onItemMove(int positionDebut, int positionFin)   {
        Collections.swap(listeMemos, positionDebut, positionFin);
        notifyItemMoved(positionDebut, positionFin);
        return true;
    }

    // Appelé une fois à la suppression.
    public void onItemDismiss(int position)   {
        if (position > -1)      {
            listeMemos.remove(position);
            notifyItemRemoved(position);
        }
    }
}