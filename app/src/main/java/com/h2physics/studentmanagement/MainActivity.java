package com.h2physics.studentmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.h2physics.studentmanagement.model.Student;
import com.h2physics.studentmanagement.network.Callback;
import com.h2physics.studentmanagement.network.NetworkManagement;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private ListView mListView;
    private Adapter mAdapter;
    private StudentInfoDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.lv_student);

        mAdapter = new Adapter(this);
        initData();
    }

    private void initData(){
        NetworkManagement.getStudents(new Callback<List<Student>>() {
            @Override
            public void onSuccess(List<Student> students) {
                mAdapter.setData(students);
                mListView.setAdapter(mAdapter);
            }

            @Override
            public void onFailed() {
                Log.e(LOG_TAG, "Failed");
            }
        });
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Student student = mAdapter.getItem(i);
        mDialog = new StudentInfoDialog(this, student, new StudentInfoDialog.OnClickListener() {
            @Override
            public void update(Student s) {
                NetworkManagement.editStudent(s, new Callback<Student>() {
                    @Override
                    public void onSuccess(Student s) {
                        initData();
                    }

                    @Override
                    public void onFailed() {
                        Log.e(LOG_TAG, "Update is failed");
                    }
                });
            }

            @Override
            public void delete(String id) {
                NetworkManagement.deleteStudent(id, new Callback<Student>() {
                    @Override
                    public void onSuccess(Student student) {
                        initData();
                    }

                    @Override
                    public void onFailed() {

                    }
                });
            }
        });
        mDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add: {
                Log.e(LOG_TAG, "Action add");
                mDialog = new StudentInfoDialog(this, new StudentInfoDialog.OnClickListener() {
                    @Override
                    public void update(Student student) {
                        NetworkManagement.createStudent(student, new Callback<Student>() {
                            @Override
                            public void onSuccess(Student student) {
                                Log.e(LOG_TAG, "Success");
                                initData();
                            }

                            @Override
                            public void onFailed() {

                            }
                        });
                    }

                    @Override
                    public void delete(String id) {

                    }
                });
                mDialog.show();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        Student student = mAdapter.getItem(i);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("Student", student);
        startActivity(intent);
        return true;
    }
}
