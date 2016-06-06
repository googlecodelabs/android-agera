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

package com.example.android.codelabs.agera.step2;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.content.pm.ActivityInfo;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.codelabs.agera.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class Step2ActivityFinalTest {
    @Rule
    public ActivityTestRule<Step2ActivityFinal> mTasksActivityTestRule =
            new ActivityTestRule<>(Step2ActivityFinal.class);

    @Test
    public void incrementButtonPressed_textIsUpdated() {
        onView(withId(R.id.step2_value_tv)).check(matches(withText("0")));
        onView(withId(R.id.increment_bt)).perform(click());
        onView(withId(R.id.step2_value_tv)).check(matches(withText("1")));
    }

    @Test
    public void rotationPersistence() {
        mTasksActivityTestRule.getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        onView(withId(R.id.step2_value_tv)).check(matches(withText("0")));
        onView(withId(R.id.increment_bt)).perform(click());
        onView(withId(R.id.step2_value_tv)).check(matches(withText("1")));

        mTasksActivityTestRule.getActivity().setRequestedOrientation(SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.increment_bt)).perform(click());
        onView(withId(R.id.step2_value_tv)).check(matches(withText("2")));
    }


}