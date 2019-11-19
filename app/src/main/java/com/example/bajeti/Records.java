package com.example.bajeti;

import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;


public class Records extends AppCompatActivity {
    ListView listView;
    SQLiteDatabase database;
    ArrayList<DataModel> list;
    ListAdapter listAdapter;
    com.example.bajeti.DatabaseClass databaseClass;

    protected void onCreate(Bundle savedInstanceState, Bundle instanceState1) {
        final Bundle savedInstanceState1 = savedInstanceState;
        super.onCreate(savedInstanceState1);
        setContentView(R.layout.activity_records);
        listView = findViewById(R.id.list_view);

        list = new ArrayList<>();
        databaseClass = new com.example.bajeti.DatabaseClass(Records.this);
        listAdapter = new list_adapter(this, R.layout.list_row, list);
        listView.setAdapter(listAdapter);

        database = databaseClass.getReadableDatabase();

        Cursor cr = database.rawQuery("Select * from expense ", null);

        StringBuilder stringBuilder = new StringBuilder();
        while (cr.moveToNext()) {
            int id = cr.getInt(0);
            String amount = cr.getString(1);
            String category = cr.getString(2);
            String description = cr.getString(3);
            String date = cr.getString(4);
            String image = cr.getString(5);
            String spinner = cr.getString(6);
            list.add(new DataModel(id+"", amount, category, date));
        }

        Bundle BundleinstanceState1 = savedInstanceState1;
        super.onCreate(savedInstanceState1);
        setContentView(R.layout.activity_records);
    }

    private class list_adapter implements ListAdapter {
        public list_adapter(Records records, int list_row, ArrayList<DataModel> list) {
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEnabled(int i) {
            return false;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }

        @Override
        public int getItemViewType(int i) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }
}


