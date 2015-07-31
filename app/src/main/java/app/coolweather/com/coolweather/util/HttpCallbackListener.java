package app.coolweather.com.coolweather.util;

/**
 * Created by zhy on 2015/7/31.
 */
public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
