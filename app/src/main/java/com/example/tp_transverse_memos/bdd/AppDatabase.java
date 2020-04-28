package com.example.tp_transverse_memos.bdd;

import com.example.tp_transverse_memos.objects.MemoDAO;
import com.example.tp_transverse_memos.objects.MemoDTO;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MemoDTO.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract MemoDAO memosDAO();

}
