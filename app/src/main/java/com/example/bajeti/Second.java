package com.example.bajeti;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // on click image method for income class
        ImageView imageIncome = findViewById(R.id.expenditureImage);
        imageIncome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(Second.this, "Enter your expenditure", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Second.this, ExpenditureActivity.class);
                startActivity(intent);

            }
        });

        //on click image method for expense class
        ImageView imageExpense = findViewById(R.id.expenseImage);
        imageExpense.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(Second.this,"Enter your new expense", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Second.this, ExpenseActivity.class);
                startActivity(intent);
            }
        });

        //on click image method for history class
        ImageView records = findViewById(R.id.Imagerecord);
        records.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
              Toast.makeText(Second.this,"You clicked on frequent records", Toast.LENGTH_LONG).show();
               Intent intent= new Intent(Second.this, Records.class);
                startActivity(intent);
            }
        });

        //on click image method for about application
        ImageView imageSettings = findViewById(R.id.aboutImage);
        imageSettings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(Second.this,"You clicked on about Money Manger application", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(Second.this, About.class);
           }
        });
        }
        }



