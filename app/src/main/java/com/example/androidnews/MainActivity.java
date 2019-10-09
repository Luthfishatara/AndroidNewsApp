package com.example.androidnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.androidnews.adapter.MyAdapter;
import com.example.androidnews.fragmentku.FragmentAbout;
import com.example.androidnews.fragmentku.FragmentCategory;
import com.example.androidnews.fragmentku.FragmentHome;
import com.example.androidnews.retrofitconfig.GetJsonAll;
import com.example.androidnews.retrofitconfig.RetrofitConfigToJson;
import com.example.androidnews.retrofitjson.News;
import com.example.androidnews.retrofitjson.NewsList;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        fragmentClick(new FragmentHome());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int userId = menuItem.getItemId();
        Fragment fragment = null;

        switch (userId){

            case R.id.home:
                fragment = new FragmentHome();
                break;

            case R.id.category:
                fragment = new FragmentCategory();
                break;

            case R.id.about:
                fragment = new FragmentAbout();
                break;

        }

        return fragmentClick(fragment);
    }

    private boolean fragmentClick(Fragment fragment){

        if (fragment != null){

            getSupportFragmentManager().beginTransaction().replace(R.id.ganti, fragment).commit();

        }

        return false;

    }

}
