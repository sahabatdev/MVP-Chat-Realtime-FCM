package com.sahabatdeveloper.realtimechatsahabatdeveloper;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    final String TAG = MyFirebaseInstanceIdService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        //Mendapatkan Instance dan Memperoleh Token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        //Menampilkan Token pada Log
        Log.d(TAG, "Token Saya : "+ refreshedToken);

        //Method berikut ini digunakan untuk memperoleh token dan mennyimpannya ke server aplikasi
//        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
        //Disini kita biarkan kosong saja
    }
}