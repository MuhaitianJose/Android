package com.muhaitian.record.localsocket;

import com.muhaitian.record.entity.TestLocalSocket;

/**
 * Created by wangkang001 on 2017/7/8.
 */

public interface LocalSocketServerCallback {
    void sendLocalSocket(TestLocalSocket testLocalSocket);
}
