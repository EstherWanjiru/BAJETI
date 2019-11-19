package com.example.bajeti;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<DataModel> data;//modify here

    public CustomAdapter(Context mContext, ArrayList<DataModel> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();// # of items in your arraylist
    }
    @Override
    public Object getItem(int position) {
        return data.get(position);// get the actual item
    }
    @Override
    public long getItemId(int id) {
        return id;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_layout, parent,false);//modify here
            viewHolder = new ViewHolder();
            //modify this block of code
            viewHolder.mTvAmount = (TextView) convertView.findViewById(R.id.tvAmount);
            viewHolder.mTvCategory = (TextView) convertView.findViewById(R.id.tvCategory);
            viewHolder.mTvDate = (TextView) convertView.findViewById(R.id.tvDate);
            viewHolder.mTvTotal = (TextView) convertView.findViewById(R.id.tvTotal);
            //Up to here
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //MODIFY THIS BLOCK OF CODE
        DataModel person = data.get(position);//modify here
        viewHolder.mTvAmount.setText("Ksh "+person.getAmount());//modify here
        viewHolder.mTvCategory.setText(person.getCategory());//modify here
        viewHolder.mTvDate.setText(person.getDate());//modify here
        double sum = 0.0;
        int size = getCount();
        for (int i=0; i<size; i++){

            viewHolder.amount = Double.parseDouble(person.getAmount());
        }
        sum = sum+viewHolder.amount;
        viewHolder.mTvTotal.setText(sum+"");
        return convertView;

    }
    static class ViewHolder {
        TextView mTvAmount,mTvCategory,mTvDate,mTvTotal;

        Double amount;
    }

}
