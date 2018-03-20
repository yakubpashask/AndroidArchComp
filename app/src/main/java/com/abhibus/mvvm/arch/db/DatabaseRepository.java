package com.abhibus.mvvm.arch.db;

/**
 * Created by yakub on 19/03/18.
 */

public class DatabaseRepository {


    private static DatabaseRepository sInstance;
    AppDatabase appDatabase;
//    MediatorLiveData<List<ExpenseEntity>> observableExpenseList;
    /*public DatabaseRepository(final AppDatabase database) {
        appDatabase =database;
        observableExpenseList = new MediatorLiveData<>();

        observableExpenseList.addSource(appDatabase.expenseDao().getExpenses(),expenseEntities ->{
            observableExpenseList.postValue(expenseEntities);
        });



    }*/

    /*public static DatabaseRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (DatabaseRepository.class) {
                if (sInstance == null) {
                    sInstance = new DatabaseRepository(database);
                }
            }
        }
        return sInstance;
    }*/




}
