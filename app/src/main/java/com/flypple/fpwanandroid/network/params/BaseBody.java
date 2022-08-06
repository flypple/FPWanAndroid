package com.flypple.fpwanandroid.network.params;

import android.os.Bundle;
import android.text.TextUtils;

import java.util.Set;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by qiqinglin on 2022/7/17
 */
public class BaseBody {
    private Bundle params;

    public BaseBody() {
        params = new Bundle();
    }

    public void putParams(String key, String value) {
        params.putString(key, value);
    }

    public RequestBody create() {
        StringBuilder sb = new StringBuilder();
        if (params.size() > 0) {
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                String value = params.getString(key);
                if (!TextUtils.isEmpty(sb)) {
                    sb.append("&");
                }
                sb.append(key).append("=").append(value);
            }
        }
        RequestBody requestBody =
                RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), sb.toString());

        return requestBody;
    }
}
