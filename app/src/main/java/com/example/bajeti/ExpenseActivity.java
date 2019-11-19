package com.example.bajeti;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ExpenseActivity extends AppCompatActivity {
    Intent mDate;
    Bundle mBundle;
    String date;
    CustomAdapter adapter;
    ArrayList<DataModel> expenses;
    ListView list;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        mDate= getIntent();
        mBundle= mDate.getExtras();

        if(mBundle!=null)
        {
            date =(String) mBundle.get("date");
        }
        list = findViewById(R.id.listExpenses);
        expenses = new ArrayList<>();
        adapter = new CustomAdapter(this,expenses);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.setMessage("Please wait...");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Expenses/"+date);
        dialog.show();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                expenses.clear();
                for (DataSnapshot snap : dataSnapshot.getChildren()){
                    DataModel expense = snap.getValue(DataModel.class);
                    expenses.add(expense);
                    Collections.reverse(expenses);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ExpenseActivity.this, "Sorry. DB Locked", Toast.LENGTH_SHORT).show();
            }
        });
        list.setAdapter(adapter);



    }
}

