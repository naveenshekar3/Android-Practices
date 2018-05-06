package com.example.root.jsonparsingapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ListView mListView;
    private MyContactAsync mMyContactAsync;
    private JsonObjectRequest mRequestObject;
    private MyAdapter mMyAdapter;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView=(ListView)findViewById(R.id.listView);
//        mMyContactAsync=new MyContactAsync(MainActivity.this,mListView);
//        mMyContactAsync.execute("https://api.androidhive.info/contacts/");

        mRequestObject=new JsonObjectRequest("https://api.androidhive.info/contacts/", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ContactList contactList=new Gson().fromJson(response.toString(),ContactList.class);//converting Gson to POJO class
                mMyAdapter=new MyAdapter(MainActivity.this,(ArrayList<Contact>)contactList.getContacts());
                mListView.setAdapter(mMyAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        JsonParsingApp.getmInstance().getmRequestQueue().add(mRequestObject);
    }
}
