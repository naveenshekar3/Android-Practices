package com.example.root.jsonparsingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<Contact> contactArrayList;

    public MyAdapter(Context mContext, ArrayList<Contact> contactArrayList) {
        this.mContext = mContext;
        this.contactArrayList = contactArrayList;
        mLayoutInflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return contactArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
        {
            convertView=mLayoutInflater.inflate(R.layout.item_layout,parent,false);
        }
        TextView idTv = (TextView) convertView.findViewById(R.id.id_tv);
        TextView nameTv = (TextView) convertView.findViewById(R.id.name_tv);
        TextView emailTv = (TextView) convertView.findViewById(R.id.email_tv);
        idTv.setText(contactArrayList.get(position).getId());
        nameTv.setText(contactArrayList.get(position).getName());
        emailTv.setText(contactArrayList.get(position).getEmail());

        return convertView;
    }
}
