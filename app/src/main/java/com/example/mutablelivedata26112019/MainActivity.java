package com.example.mutablelivedata26112019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MutableLiveData<String> mutableLiveDataString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mutableLiveDataString = new MutableLiveData<>();

        // Cách lấy dữ liệu
        mutableLiveDataString.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });

        // Truyền dữ liệu cho mutable
        // 1 : Set value (Chỉ dành cho mainthread)
//        mutableLiveDataString.setValue("Hello main");
        // 2 : Post value ( Dành cho luồng phụ , vẫn chạy được trên luồng chính)
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                mutableLiveDataString.setValue("Hello main");
            }
        });
        thread.start();
    }

}
