package com.abhibus.mvvm.arch.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import com.abhibus.mvvm.arch.ABApplication;
import com.abhibus.mvvm.arch.db.dao.ExpenseDao;

/**
 * Created by yakub on 14/03/18.
 */

/**
 * App Database with version name and Entities(Objects that needs to be stored)
 */

@Database(version = 1, entities = { ExpenseEntity.class })
public abstract class AppDatabase extends RoomDatabase{

    Context context;

    abstract public ExpenseDao expenseDao();

    public static AppDatabase appDatabase;

    public static AppDatabase getAppDatabase() {
        if(appDatabase == null){
            appDatabase = Room.databaseBuilder(ABApplication.getGlobalContext(),AppDatabase.class,"AB_DB")
                    .build();
//            .addMigrations(Migration_1_2)
        }
        return appDatabase;
    }
    /*
        Migration queries to be added here
     */
    static final Migration Migration_1_2  =new Migration(1,2) {

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE ExpenseEntity ADD COLOUMN NAME");
        }
    };


    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}
