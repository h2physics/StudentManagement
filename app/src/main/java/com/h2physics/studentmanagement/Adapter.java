package com.h2physics.studentmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.h2physics.studentmanagement.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YukiNoHara on 10/25/2017.
 */

public class Adapter extends BaseAdapter {
    private List<Student> mList;
    private Context mContext;

    public Adapter(Context context){
        this.mContext = context;
        mList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Student getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_student, viewGroup, false);
        }

        Student student = mList.get(i);

        TextView tvId = view.findViewById(R.id.tv_id);
        TextView tvName = view.findViewById(R.id.tv_name);
        tvId.setText(student.getId());
        tvName.setText(student.getName());

        return view;
    }

    public void setData(List<Student> list){
        mList = list;
        notifyDataSetChanged();
    }
}
