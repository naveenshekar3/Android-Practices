package com.example.root.jsonparsingapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MyContactAsync extends AsyncTask<String, String, String>{

    private Context mContext;
    private ListView mListView;
    private ProgressDialog mProgressDialog;
    private MyAdapter mMyAdapter;

    public MyContactAsync(Context mContext, ListView mListView) {
        this.mContext = mContext;
        this.mListView = mListView;
        mProgressDialog=new ProgressDialog(mContext);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
    }


    @Override
    protected String doInBackground(String... params) {
        String result=null;
        StringBuilder stringBuilder=new StringBuilder();
        try {
            URL url=new URL(params[0]);
            URLConnection urlConnection=url.openConnection();
            urlConnection.connect();

            InputStream inputStream=urlConnection.getInputStream();
            Reader reader=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(reader);

            while ((result=bufferedReader.readLine())!=null){
                stringBuilder.append(result);

            }

            result=stringBuilder.toString();

        }catch (Exception e){

        }

        return result;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }



    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        try{

            JSONObject jsonObject=new JSONObject(result);//we are converting the result which are coming in string to an JSON object.
            JSONArray jsonArray=jsonObject.getJSONArray("contacts");

            ArrayList<Contact> contactArrayList=new ArrayList<>();

            for (int i=0;i<jsonArray.length();i++)
            {
                Contact contact=new Contact();
                contact.setId(jsonArray.getJSONObject(i).getString("id"));//getting first object(contact) and first string (id)
                contact.setName(jsonArray.getJSONObject(i).getString("name"));//getting first object(contact) and string (name)
                contact.setEmail(jsonArray.getJSONObject(i).getString("email"));//getting first object(contact) and string (email)
                contactArrayList.add(contact);
            }

            mMyAdapter=new MyAdapter(mContext,contactArrayList);
            mListView.setAdapter(mMyAdapter);

        }catch (Exception e){
            e.printStackTrace();

        }
        mProgressDialog.dismiss();
    }
}
