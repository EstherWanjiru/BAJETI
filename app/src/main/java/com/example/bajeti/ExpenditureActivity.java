package com.example.bajeti;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ExpenditureActivity extends AppCompatActivity {
    EditText mEdtAmount,mEdtDate;
    Spinner mSpnCategory;
    private int mYear, mMonth, mDay;
    ProgressDialog dialog;
    Button mBtnSubmit,mBtnExpenses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure);

        mEdtAmount = findViewById(R.id.edtAmount);
        mEdtDate = findViewById(R.id.edtDate);
        mSpnCategory = findViewById(R.id.spnCategory);
        mBtnSubmit = findViewById(R.id.btnSubmit);
        mBtnExpenses = findViewById(R.id.btnExpenses);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Submitting");
        dialog.setMessage("Please wait...");

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = mEdtAmount.getText().toString().trim();
                String category = mSpnCategory.getSelectedItem().toString().trim();
                String date = mEdtDate.getText().toString().trim();
                long time = System.currentTimeMillis();
                String timeConv = String.valueOf(time);

                if (amount.isEmpty()){
                    mEdtAmount.setError("Enter Amount");
                }else if (date.isEmpty()){
                    mEdtDate.setError("Enter Date");
                }else {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Expenses/"+date+"/"+timeConv);
                    DataModel model = new DataModel(timeConv,amount,category,date);
                    dialog.show();
                    ref.setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            dialog.dismiss();
                            if (task.isSuccessful()){
                                Toast.makeText(ExpenditureActivity.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();

                            }else {
                                Toast.makeText(ExpenditureActivity.this, "Submitting Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        mBtnExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = mEdtDate.getText().toString().trim();
                if (date.isEmpty()){
                    mEdtDate.setError("Enter Date");
                }else {
                    Intent expenses = new Intent(getApplicationContext(),ExpenseActivity.class);
                    expenses.putExtra("date", date);
                    startActivity(expenses);
                }

            }
        });
    }

    public void date(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        mEdtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
