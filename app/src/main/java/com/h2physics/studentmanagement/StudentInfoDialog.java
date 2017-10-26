package com.h2physics.studentmanagement;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.h2physics.studentmanagement.model.Student;

/**
 * Created by YukiNoHara on 10/25/2017.
 */

public class StudentInfoDialog extends Dialog implements View.OnClickListener {
    private EditText edtId;
    private EditText edtName;
    private Button btnUpdate;
    private Button btnDelete;
    private Student mStudent;
    private OnClickListener mListener;
    private boolean isAdd = false;

    public StudentInfoDialog(@NonNull Context context, Student student, OnClickListener listener) {
        super(context);
        this.mStudent = student;
        this.mListener = listener;
    }

    public StudentInfoDialog(@NonNull Context context, OnClickListener listener) {
        super(context);
        this.mListener = listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_info);
        initView();
        initData();
    }

    private void initView() {
        edtId = findViewById(R.id.edt_id);
        edtName = findViewById(R.id.edt_name);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);
    }

    private void initData() {
        if (mStudent != null) {
            edtId.setFocusable(false);
            edtId.setText(mStudent.getId());
            edtName.setText(mStudent.getName());
            btnUpdate.setOnClickListener(this);
            btnDelete.setOnClickListener(this);
        } else {
            isAdd = true;
            edtId.setFocusable(true);
            btnUpdate.setText("Add");
            btnDelete.setVisibility(View.GONE);
            btnUpdate.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_update: {
                if (!TextUtils.isEmpty(edtId.getText()) || !TextUtils.isEmpty(edtName.getText())) {
                    mListener.update(new Student(edtId.getText().toString(), edtName.getText().toString()));
                    this.dismiss();
                }
                break;
            }
            case R.id.btn_delete: {
                mListener.delete(edtId.getText().toString());
                this.dismiss();
                break;
            }
        }
    }

    public interface OnClickListener {
        void update(Student student);

        void delete(String id);
    }
}
