/*
 * Copyright (C) 2016 Qiujuer <qiujuer@live.cn>
 * WebSite http://www.qiujuer.net
 * Created 1/1/2016
 * Changed 1/6/2016
 * Version 1.0.0
 * Author Qiujuer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.qiujuer.common.okhttp.impl;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import net.qiujuer.common.okhttp.core.HttpCallback;
import net.qiujuer.genius.kit.util.UiKit;

/**
 * This callback to call method by UI thread
 */
public abstract class UiCallback<T> extends HttpCallback<T> {
    @Override
    protected void dispatchFailure(final Request request, final Response response, final Exception e) {
        UiKit.runOnMainThreadSync(new Runnable() {
            @Override
            public void run() {
                onFailure(request, response, e);
            }
        });
    }

    @Override
    protected void dispatchSuccess(final T response, final int code) {
        UiKit.runOnMainThreadSync(new Runnable() {
            @Override
            public void run() {
                onSuccess(response, code);
            }
        });
    }

    @Override
    public void dispatchProgress(final long current, final long count) {
        UiKit.runOnMainThreadSync(new Runnable() {
            @Override
            public void run() {
                onProgress(current, count);
            }
        });
    }
}
