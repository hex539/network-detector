package me.hex539.networkdetector.service;

import static android.net.ConnectivityManager.*;
import static android.net.NetworkCapabilities.*;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.util.*;

import me.hex539.networkdetector.R;

public class DetectorService extends Service {

  private static final String TAG = DetectorService.class.getSimpleName();
  private static final int SERVICE_ID = 654321;

  private ConnectivityManager connectivityManager = null;
  private boolean requested = false;

  @Override
  public void onCreate() {
    super.onCreate();

    connectivityManager = (ConnectivityManager) this
        .getSystemService(Context.CONNECTIVITY_SERVICE);

    final Notification notification = new Notification.Builder(this)
        .setContentTitle(getString(R.string.network_detector))
        .setSmallIcon(R.drawable.app_icon)
        .build();
    startForeground(SERVICE_ID, notification);

    destroyRequest();
    createRequest();

    Log.e(TAG, "Starting");
    revalidate();
  }

  @Override
  public void onDestroy() {
    destroyRequest();
    super.onDestroy();
  }

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  private void createRequest() {
    if (!requested) {
      connectivityManager.requestNetwork(request, callback);
      connectivityManager.addDefaultNetworkActiveListener(listener);
      requested = true;
    }
  }

  private void destroyRequest() {
    if (requested) {
      connectivityManager.removeDefaultNetworkActiveListener(listener);
      connectivityManager.unregisterNetworkCallback(callback);
      requested = false;
    }
  }

  private void revalidate() {
    final NetworkInfo info = connectivityManager.getActiveNetworkInfo();
    final boolean isConnected = (info != null && info.isConnected());
    boolean isValidated = false;
    if (isConnected) {
      final NetworkCapabilities caps = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
      isValidated = caps.hasCapability(NET_CAPABILITY_INTERNET) && caps.hasCapability(NET_CAPABILITY_VALIDATED);
    } else {
      isValidated = false;
    }
    Log.e(TAG, "revalidate connected=" + isConnected + " validated=" + isValidated);
  }

  private final NetworkRequest request = new NetworkRequest.Builder()
      .addCapability(NET_CAPABILITY_INTERNET)
//      .addCapability(NET_CAPABILITY_FOREGROUND) // API 28+
      .addCapability(NET_CAPABILITY_NOT_RESTRICTED)
      .build();

  private final NetworkCallback callback = new NetworkCallback() {
    @Override
    public void onAvailable(Network network) {
      Log.e(TAG, "onAvailable " + network);
      revalidate();
    }

    @Override
    public void onCapabilitiesChanged(Network network, NetworkCapabilities capabilities) {
      Log.e(TAG, "onCapabilitiesChanged " + network + " " + capabilities);
      revalidate();
    }

    @Override
    public void onLinkPropertiesChanged(Network network, LinkProperties capabilities) {
      Log.e(TAG, "onLinkPropertiesChanged " + network + " " + capabilities);
      revalidate();
    }

    @Override
    public void onLost(Network network) {
      Log.e(TAG, "onLost " + network);
      revalidate();
    }
  };

  private final OnNetworkActiveListener listener = new OnNetworkActiveListener() {
    @Override
    public void onNetworkActive() {
      Log.e(TAG, "onNetworkActive");
      revalidate();
    }
  };
}
