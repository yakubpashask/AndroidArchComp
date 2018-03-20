package com.abhibus.mvvm.arch.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.abhibus.mvvm.arch.db.ExpenseEntity;

import java.util.List;

/**
 * Created by yakub on 14/03/18.
 *
 * Expense DAO mediator for all the action you perform on the Entity/Tables you mentioned in App Database
 */

@Dao
public interface ExpenseDao {

    @Insert
    void insert(ExpenseEntity expenseEntity);

    @Insert
    void insertAll(List<ExpenseEntity> expenseEntities);


    @Delete
    void delete(ExpenseEntity expenseEntity);

    @Update(onConflict = 3)
    void update(ExpenseEntity expenseEntity);


    @Query("SELECT * FROM ExpenseEntity")
    LiveData<List<ExpenseEntity>> getExpenses();

    @Query("SELECT * FROM ExpenseEntity WHERE id =:expenseID")
    ExpenseEntity getExpense(int expenseID);

}
