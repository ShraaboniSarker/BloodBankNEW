package com.example.shraaboni.bloodbanknew;

import android.util.Log;

import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by shraaboni on 10/7/2017.
 */
public class MyFirebaseInstanceID extends FirebaseInstanceIdService {
    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. This call is initiated by the
     * InstanceID provider.
     */
    @Override
    public void onTokenRefresh() {
        try {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            AppEventsLogger.setPushNotificationsRegistrationId(refreshedToken);
        } catch (Exception e) {
            Log.e("test", "Failed to complete token refresh", e);
        }
    }
}
