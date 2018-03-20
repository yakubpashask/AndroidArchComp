package com.abhibus.mvvm.arch.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.abhibus.mvvm.arch.db.AppDatabase;
import com.abhibus.mvvm.arch.db.ExpenseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yakub on 19/03/18.
 */

public class ExpenseViewModel extends AndroidViewModel {

    //Mediator live data observes all the live data which are getting emitted

    private MediatorLiveData<List<ExpenseEntity>> observableEntities;
    public ExpenseViewModel(@NonNull Application application) {
        super(application);

        observableEntities = new MediatorLiveData<>();
        observableEntities.setValue(new ArrayList<>());
//        LiveData<List<ExpenseEntity>> expenses = AppDatabase.getAppDatabase().expenseDao().getExpenses();
        observableEntities.addSource(AppDatabase.getAppDatabase().expenseDao().getExpenses(),observableEntities::setValue);

    }

    public MediatorLiveData<List<ExpenseEntity>> getObservableEntities() {
        return observableEntities;
    }
}
