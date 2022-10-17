package com.vbgroups.vbtantrav1.Fragments;

import com.vbgroups.vbtantrav1.Notifications.MyResponse;
import com.vbgroups.vbtantrav1.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAo35YYoc:APA91bGgEEBO9QvctpLxAnFQT-Cz9TiI-4gE6C1qYpYwiiWA5bllYb7U1rDs4NgwY-3bMBSrku1UYu74SfOwb7O_HdKADLKOfMnHFzqb95o0JlZeUZ0a7EVOnEye-bShDLp541WzjyTy"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
