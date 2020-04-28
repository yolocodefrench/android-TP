package com.example.tp_transverse_memos.bdd;

import android.content.Context;

import androidx.room.Room;

public class AppDatabaseHelper
{
    // Attributs :
    private static AppDatabaseHelper databaseHelper = null;
    private AppDatabase database;
    // Constructeur :
    private AppDatabaseHelper(Context context)
    {
        database = Room
                .databaseBuilder(context, AppDatabase.class, "courses.db")
                .allowMainThreadQueries()
                .build();
    }
    // Getter instance :
    public static synchronized AppDatabase getDatabase(Context context)
    {
        if (databaseHelper == null)
        {
            databaseHelper = new AppDatabaseHelper(
                    context.getApplicationContext());
        }
        return databaseHelper.database;
    }
}
