package net.amigochi.amigochi.features.notifications;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by sierisimo on 21/06/16.
 */
public class AmountNotification extends FirebaseMessagingService {
    private static final String TAG = "AmountNotification";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "onMessageReceived() called with: " + "remoteMessage = [" + remoteMessage + "]");
    }
}
