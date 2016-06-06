/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.codelabs.agera.step4;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

import java.util.concurrent.ThreadPoolExecutor;

final class ThreadPoolIdlingResource implements IdlingResource {

    @NonNull
    private final ThreadPoolExecutor mThreadPoolExecutor;

    @NonNull
    private final String mName;

    @Nullable
    private ResourceCallback mCallback;

    private ThreadPoolIdlingResource(@NonNull final ThreadPoolExecutor executor,
            @NonNull final String name) {
        mThreadPoolExecutor = executor;
        mName = name;
    }

    public static IdlingResource newThreadPoolIdlingResource(
            @NonNull final ThreadPoolExecutor executor, @NonNull final String name) {
        return new ThreadPoolIdlingResource(executor, name);
    }

    @Override
    public String getName() {
        return "ThreadPool: " + mName;
    }

    @Override
    public synchronized boolean isIdleNow() {
        if (mThreadPoolExecutor.getQueue().isEmpty() && mThreadPoolExecutor.getActiveCount() <= 0) {
            if (mCallback != null) {
                mCallback.onTransitionToIdle();
            }
            return true;
        }
        return false;
    }

    @Override
    public void registerIdleTransitionCallback(@Nullable final ResourceCallback resourceCallback) {
        mCallback = resourceCallback;
    }
}