package com.example.lasai;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


import com.example.lasai.fragments.SecretActivity;
import com.example.lasai.retrofit.RetrofitProvider;
import com.example.lasai.retrofit.Tips;
import com.example.lasai.retrofit.TipsViewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_view_container);

        getTips();



        Button startActivity = (Button) findViewById(R.id.main_activity_button);
        startActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecretActivity.class);
                startActivity(intent);
            }
        });
    }

    public void getTips(){
        Call<List<Tips>> listCall = RetrofitProvider.getInstance().getAllTips();
        listCall.enqueue(new Callback<List<Tips>>() {
            @Override
            public void onResponse(Call<List<Tips>> call, Response<List<Tips>> response) {
                if(response.isSuccessful()){
                    List<Tips> tips = response.body();
                    TipsViewAdapter adapter = new TipsViewAdapter(MainActivity.this, tips);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Tips>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}