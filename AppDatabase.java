package com.example.experiment2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {WordBean.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "words.db";
    private static volatile AppDatabase instance;

    static synchronized AppDatabase getInstance(Context context) {
        if(instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static AppDatabase create(final Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

    public abstract WordDao wordDao();
    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };
}