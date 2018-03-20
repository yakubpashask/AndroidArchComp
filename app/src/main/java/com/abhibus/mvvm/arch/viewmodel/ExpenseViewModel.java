package com.abhibus.mvvm.arch.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

/**
 * Created by yakub on 19/03/18.
 */

public class ExpenseViewModel extends AndroidViewModel {

    public ExpenseViewModel(@NonNull Application application) {
        super(application);
    }
}
