package com.abhibus.mvvm.arch.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.abhibus.mvvm.arch.model.Expense;

/**
 * Created by yakub on 13/03/18.
 */


@Entity
public class ExpenseEntity implements Expense {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private double amount;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getDate() {
        return date;
    }
}
