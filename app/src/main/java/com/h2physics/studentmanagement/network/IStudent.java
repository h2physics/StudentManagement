package com.h2physics.studentmanagement.network;

import com.h2physics.studentmanagement.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by YukiNoHara on 10/25/2017.
 */

public interface IStudent {
    @GET("Students/GetAllStudent")
    Call<List<Student>> getStudents();

    @POST("Students/CreateStudent")
    Call<Student> createStudent(@Body Student student);

    @POST("Students/EditStudent")
    Call<Student> editStudent(@Body Student student);

    @DELETE("Students/DeleteStudent/{id}")
    Call<Student> deleteStudent(@Path("id") String id);


}
