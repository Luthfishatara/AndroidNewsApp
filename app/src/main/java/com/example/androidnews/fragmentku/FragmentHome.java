package com.example.androidnews.fragmentku;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidnews.R;
import com.example.androidnews.adapter.MyAdapter;
import com.example.androidnews.retrofitconfig.GetJsonAll;
import com.example.androidnews.retrofitconfig.RetrofitConfigToJson;
import com.example.androidnews.retrofitjson.News;
import com.example.androidnews.retrofitjson.NewsList;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment {

    GetJsonAll getJsonAll;
    List<News> newsList;
    String title, description;

    RecyclerView recycler_view;
    MyAdapter myAdapter;
    GridLayoutManager gm;
    SpinKitView spinKitView1;

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

         recycler_view = view.findViewById(R.id.recycler_view);
        gm = new GridLayoutManager(getContext(), 2);
        recycler_view.setLayoutManager(gm);
        newsList = new ArrayList<>();
        myAdapter = new MyAdapter(getContext(), newsList);
        recycler_view.setAdapter(myAdapter);
        spinKitView1 = view.findViewById(R.id.spin_kit1);

        getJsonAll = RetrofitConfigToJson.getResponses();

        getJsonAll.getNewsList("id", "133c9504b213421b9b80b51fdcc5eb3b").enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {

                spinKitView1.setVisibility(View.VISIBLE);

                Log.d("berhasil", response + "");

                newsList = response.body().getArticles();

                title = newsList.get(0).getTitle();
                description = newsList.get(0).getDescription();
                Log.d("titleBerita", "Judul: " + title + " " +  "Deskripsi: " + description);

                myAdapter = new MyAdapter(getContext(), newsList);
                recycler_view.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();

                spinKitView1.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Log.d("gagal", t + "");

            }
        });


        return view;

    }
}
