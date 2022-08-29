/**
 * Assignment #: InClass05
 * File Name: Group25_InClass05 --- MainActivity.java
 * Full Name: Kristin Pflug
 */

package com.example.group25_inclass05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements AppCategoriesFragment.AppCategoriesFragmentListener, AppsListFragment.AppsListFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.rootView, new AppCategoriesFragment()).commit();
    }

    public void categorySelected(String category){
        getSupportFragmentManager().beginTransaction().replace(R.id.rootView, AppsListFragment.newInstance(category)).addToBackStack(null).commit();
    }

    @Override
    public void displayAppDetails(DataServices.App appSelected) {
        getSupportFragmentManager().beginTransaction().replace(R.id.rootView, AppDetailsFragment.newInstance(appSelected)).addToBackStack(null).commit();
    }
}