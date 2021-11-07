package com.example.idrive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;

import com.example.idrive.Adapters.PostAdapter;
import com.example.idrive.ModelResponse.PostModel;
import com.example.idrive.Util.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout mySwipeRefreshLayout;
    private EndlessRecyclerViewScrollListener scrollListener;
    RecyclerView recyclerView;
    PostAdapter adapter;
    ArrayList<PostModel> list;
    int perPage = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySwipeRefreshLayout = findViewById(R.id.swiperefresh);

        recyclerView = findViewById(R.id.recyclerView);

        list = new ArrayList<>();

        adapter = new PostAdapter(list, this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        /*
         * Sets up a SwipeRefreshLayout.OnRefreshListener that is invoked when the user
         * performs a swipe-to-refresh gesture.
         */
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {

                            }
                        }, 2*1000);
                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        adapter.clearCache();
                        list.clear();
                        adapter.notifyDataSetChanged();
                        myUpdateOperation(0);
                    }
                }
        );

        // Retain an instance so that you can call `resetState()` for fresh searches
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                loadNextDataFromApi(page,totalItemsCount);
            }
        };
        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(scrollListener);
        myUpdateOperation(0);
    }

    protected void myUpdateOperation(int totalItems){
        Call<List<PostModel>> call = RetrofitClient
                .getInstance()
                .getApi()
                .posts();

        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, retrofit2.Response<List<PostModel>> response) {
                List<PostModel> res = response.body();
                Log.i("DEBUG","loaded from "+totalItems+" to "+Math.min(res.size(),totalItems+perPage));
                List<PostModel> postToAdd = res.subList(totalItems, Math.min(res.size(),totalItems+perPage));
                if(postToAdd!=null && postToAdd.size()!=0) {
                    Log.i("DEBUG","added size "+postToAdd.size());
                    list.addAll(postToAdd);
                }
                adapter.notifyDataSetChanged();
                mySwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Log.i("ERROR", "onRefresh "+t.toString());
                mySwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    protected void loadNextDataFromApi(int page,int totalItemCount){
        myUpdateOperation(list.size());
    }
}