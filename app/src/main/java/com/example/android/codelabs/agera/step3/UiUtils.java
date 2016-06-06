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

package com.example.android.codelabs.agera.step3;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

class UiUtils {
    /**
     * Starts an animation that will jank if the UI thread is busy.
     * @param animView
     */
    static void startAnimation(View animView) {
        Animation tx = new TranslateAnimation(-350, 350, 0, 0);
        tx.setDuration(1000);
        tx.setRepeatCount(Animation.INFINITE);
        tx.setInterpolator(new AccelerateDecelerateInterpolator());
        tx.setRepeatMode(Animation.REVERSE);
        animView.startAnimation(tx);
    }
}
