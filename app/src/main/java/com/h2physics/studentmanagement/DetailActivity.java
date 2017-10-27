package com.h2physics.studentmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.h2physics.studentmanagement.model.Student;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Student student = (Student) getIntent().getSerializableExtra("Student");
        TextView textViewId = (TextView) findViewById(R.id.tv_id);
        TextView textViewName = (TextView) findViewById(R.id.tv_name);
        textViewId.setText(student.getId());
        textViewName.setText(student.getName());

    }
}
