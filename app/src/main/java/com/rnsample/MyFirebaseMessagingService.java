/**********
Created by 
Rashmi Yadav 
**********/




package com.rnsample;

// import android.os.Bundle;
// import android.util.Log;
// import com.clevertap.android.sdk.CleverTapAPI;
// import com.clevertap.android.sdk.pushnotification.NotificationInfo;
// import com.google.firebase.messaging.FirebaseMessagingService;
// import com.google.firebase.messaging.RemoteMessage;
// import java.util.Map;

// public class MyFirebaseMessagingService extends FirebaseMessagingService {
// @Override
// public void onMessageReceived(RemoteMessage message) {
// try {
// if (message.getData().size() > 0) {
// Bundle extras = new Bundle();
// for (Map.Entry<String, String> entry : message.getData().entrySet()) {
// extras.putString(entry.getKey(), entry.getValue());
// }
// Log.e("TAG", "notification received with message = " + extras.toString());
// NotificationInfo info = CleverTapAPI.getNotificationInfo(extras);

// if (info.fromCleverTap) {
// CleverTapAPI.createNotification(getApplicationContext(), extras);
// }
// }
// } catch (Throwable t) {
// Log.d("MYFCMLIST", "Error parsing FCM message", t);
// }
// }

// @Override
// public void onNewToken(String token) {
// CleverTapAPI.getDefaultInstance(this).pushFcmRegistrationId(token, true);
// }
// }

//Parth's code

import io.invertase.firebase.messaging.ReactNativeFirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.clevertap.android.sdk.CleverTapAPI;
import android.os.Bundle;
import android.util.Log;
import java.util.Map;
import com.clevertap.android.sdk.pushnotification.NotificationInfo;

public class MyFirebaseMessagingService extends ReactNativeFirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage message){
        try {
            if (message.getData().size() > 0) {
                Bundle extras = new Bundle();
                for (Map.Entry<String, String> entry : message.getData().entrySet()) {
                    extras.putString(entry.getKey(), entry.getValue());
                }
                NotificationInfo info = CleverTapAPI.getNotificationInfo(extras);
                if (info.fromCleverTap) {
                    CleverTapAPI.createNotification(getApplicationContext(), extras);
                } else {
                    // not from CleverTap handle yourself or pass to another provider
                    super.onMessageReceived(message);
                }
            }
        } catch (Throwable t) {
            Log.d("MYFCMLIST", "Error parsing FCM message", t);
        }
    }
}