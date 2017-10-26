package com.h2physics.studentmanagement.network;

import android.content.Context;

import com.h2physics.studentmanagement.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by YukiNoHara on 10/25/2017.
 */

public class NetworkManagement {

    public static void getStudents(final Callback<List<Student>> callback){
        IStudent iStudent = Client.getInstance().create(IStudent.class);
        Call<List<Student>> call = iStudent.getStudents();
        call.enqueue(new retrofit2.Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if (response.isSuccessful()){
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                callback.onFailed();
            }
        });
    }

    public static void createStudent(final Student student, final Callback<Student> callback){
        IStudent iStudent = Client.getInstance().create(IStudent.class);
        Call<Student> call = iStudent.createStudent(student);
        call.enqueue(new retrofit2.Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if (response.isSuccessful()){
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                callback.onFailed();
            }
        });
    }

    public static void editStudent(Student student, final Callback<Student> callback){
        IStudent iStudent = Client.getInstance().create(IStudent.class);
        Call<Student> call = iStudent.editStudent(student);
        call.enqueue(new retrofit2.Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if (response.isSuccessful()){
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                callback.onFailed();
            }
        });

    }

    public static void deleteStudent(String id, final Callback<Student> callback){
        IStudent iStudent = Client.getInstance().create(IStudent.class);
        Call<Student> call = iStudent.deleteStudent(id);
        call.enqueue(new retrofit2.Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if (response.isSuccessful()){
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                callback.onFailed();
            }
        });

    }

}
