package com.h2physics.studentmanagement.network;

/**
 * Created by YukiNoHara on 10/25/2017.
 */

public interface Callback<T> {
    void onSuccess(T t);
    void onFailed();
}
