package com.example.cowcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    //Initialize variables

    Button addButton, rejButton, clearButton;
    EditText breedET, idET;
    TableLayout cowTable;
    private final int maxLength = 3;
    private int cowCount = 0;
    TextView cowView;
    private Bundle savedInstanceState;
    TextView breedCell ;
    TextView idCell ;
    TableRow row ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        breedET = (EditText) findViewById(R.id.breedET);
        breedET.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        idET = (EditText) findViewById(R.id.idET);
        idET.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        cowView = (TextView) findViewById(R.id.cowView);
        addButton = (Button) findViewById(R.id.addButton);
        rejButton = (Button) findViewById(R.id.rejButton);
        clearButton = (Button) findViewById(R.id.clearButton);
        addButton.setBackgroundColor(Color.parseColor("#a6517e"));
        rejButton.setBackgroundColor(Color.parseColor("#a6517e"));
        clearButton.setBackgroundColor(Color.RED);
        cowTable = (TableLayout) findViewById(R.id.simpleTableLayout);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String breed = breedET.getText().toString();
                String id = idET.getText().toString();
                if(TextUtils.isDigitsOnly(breed) && TextUtils.isDigitsOnly(id) &&
                Integer.parseInt(breed) > 0 &&  Integer.parseInt(id) > 0 &&
                        Integer.parseInt(breed) < 999 &&  Integer.parseInt(id)<999) {
                    breedCell = new TextView(MainActivity.this);
                    idCell = new TextView(MainActivity.this);
                    row = new TableRow(MainActivity.this);

                    breedCell.setGravity(Gravity.CENTER);
                    idCell.setGravity(Gravity.CENTER);
                    breedCell.setTextSize(18);
                    idCell.setTextSize(18);
                    breedCell.setText(breed);
                    idCell.setText(id);

                    row.addView(breedCell);
                    row.addView(idCell);
                    cowTable.addView(row);
                    cowCount++;//increment the count
                    cowView.setText("Cows: " + cowCount);// view in the text
                }
                else{
                    Toast toast = Toast.makeText(MainActivity.this, "Incorrect Input", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.LEFT, 50, 50);

                    toast.show();
                }
            }
        });


        rejButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                breedET.setText("");
                idET.setText("");

            }
        });


        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = cowTable.getChildCount();
                for (int i = 1; i < count; i++) {
                    View child = cowTable.getChildAt(i);
                    if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
                }
                cowCount = 0;
                cowView.setText("Cows: " + cowCount);

            }
        });
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        breedET.setText(savedInstanceState.getString(String.valueOf(breedET)));
        idET.setText(savedInstanceState.getString(String.valueOf(idET)));
        savedInstanceState.getStringArray("String1");
        savedInstanceState.getStringArray("String2");
    }
}
