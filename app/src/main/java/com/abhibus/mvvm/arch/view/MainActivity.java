package com.abhibus.mvvm.arch.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.abhibus.mvvm.arch.R;
import com.abhibus.mvvm.arch.adapter.ExpenseRVAdapter;
import com.abhibus.mvvm.arch.db.AppDatabase;
import com.abhibus.mvvm.arch.db.ExpenseEntity;
import com.abhibus.mvvm.arch.viewmodel.ExpenseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yakub on 19/03/18.
 */

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView ;
    private LiveData<List<ExpenseEntity>> entities = new MutableLiveData<>();
    private MutableLiveData<List<ExpenseEntity>> mutableLiveData = new MutableLiveData<>();
    private ExpenseRVAdapter adapter;
    private EditText expenseTitle,expenseAmount,expenseDesc;
    private Button saveBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * This is where ViewModel Class comes into picture. get the view model class and get the Mediator Live data and observ on it for the updated content
         */
        ExpenseViewModel expenseViewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);

        // Get the Observable from the View model
        MediatorLiveData<List<ExpenseEntity>> observableEntities = expenseViewModel.getObservableEntities();
        //Observer will listen and will get trigger every time there is any change in the ExpenseEntity Table
        observableEntities.observe(this,entities->{
                adapter.setEntities(entities);
                adapter.notifyDataSetChanged();
                //observableEntities.removeSource(expenseViewModel.getObservableEntities());
                //observableEntities.removeObservers(this);
        });

        recyclerView = findViewById(R.id.recylerView);
        adapter = new ExpenseRVAdapter(entities.getValue()!=null?entities.getValue():new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        expenseTitle = findViewById(R.id.expenseTitle);
        expenseAmount = findViewById(R.id.expenseAmount);
        expenseDesc = findViewById(R.id.expenseDesc);
        saveBtn = findViewById(R.id.saveBtn);

       /* mutableLiveData.observe(MainActivity.this, entities1 -> {
            adapter.setEntities(entities1);
            adapter.notifyDataSetChanged();

        });*/
        saveBtn.setOnClickListener(view -> {
            ExpenseEntity expenseEntity = new ExpenseEntity();
            if (expenseTitle.getText() != null && !expenseTitle.getText().toString().isEmpty())
                expenseEntity.setTitle(expenseTitle.getText().toString());
            else
                expenseTitle.setError("Please enter the Title");

            if (expenseAmount.getText() != null && !expenseAmount.getText().toString().isEmpty())
                expenseEntity.setAmount(Double.parseDouble(expenseAmount.getText().toString()));
            else
                expenseTitle.setError("Please enter the Amount");
            //insert the Expense
            AsyncTask.execute(() -> {
                AppDatabase.getAppDatabase().expenseDao().insert(expenseEntity);
//                getExpenses();
                expenseTitle.setText("");
                expenseAmount.setText("");
            });
            // Without Mediator get the expense
//            AsyncTask.execute(() -> {
//                getExpenses();
//            });
        });

    }
    // if there is no Mediator we should call the below method and set the data everytime.
    void getExpenses(){
        AsyncTask.execute(()->{
            synchronized (this) {
                entities =AppDatabase.getAppDatabase().expenseDao().getExpenses();
                entities.observe(MainActivity.this,expenseEntities -> {
                    mutableLiveData.setValue(expenseEntities);
                });

            }

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // We need to remove the observers when the activity is destroyed.
        entities.removeObservers(this);
        mutableLiveData.removeObservers(this);

    }
}
