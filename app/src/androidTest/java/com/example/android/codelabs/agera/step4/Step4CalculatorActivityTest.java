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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.filters.Suppress;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.SeekBar;

import com.example.android.codelabs.agera.R;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
@LargeTest
@Suppress // Remove this line to enable
public class Step4CalculatorActivityTest {

    private IdlingResource mIdlingResource;

    @Rule
    public ActivityTestRule<CalculatorActivity> mTasksActivityTestRule =
            new ActivityTestRule<CalculatorActivity>(CalculatorActivity.class) {

                @Override
                protected Intent getActivityIntent() {
                    Intent intent = null;
                    //Create an Intent to tell the activity to disable animations.
                    return intent;
                }
            };

    @Before
    public void registerIdlingResource() {
        // Register the idling resource
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

    @Test
    public void initial_shows_NA() {
        String notAvailableResource = mTasksActivityTestRule.getActivity()
                .getResources().getString(R.string.not_available);
        onView(withId(R.id.textViewResult)).check(matches(withText(notAvailableResource)));
    }

    @Test
    public void calculator_add() throws InterruptedException {
        onView(withId(R.id.radioButtonAdd)).perform(click());
        onView(withId(R.id.seekBar1)).perform(setProgress(10));
        onView(withId(R.id.seekBar2)).perform(setProgress(32));
        onView(withId(R.id.textViewResult)).check(matches(withText("42")));
    }

    @Test
    public void divisionByZero() {
        String divZeroResource = mTasksActivityTestRule.getActivity()
                .getResources().getString(R.string.div_zero);
        onView(withId(R.id.seekBar1)).perform(setProgress(50));
        onView(withId(R.id.seekBar2)).perform(setProgress(0));
        onView(withId(R.id.radioButtonDiv)).perform(click());
        onView(withId(R.id.textViewResult)).check(matches(withText(divZeroResource)));
    }

    public static ViewAction setProgress(int progress) {
        return new ViewAction() {
            @Override
            public void perform(UiController uiController, View view) {
                SeekBar seekBar = (SeekBar) view;
                seekBar.setProgress(progress);
            }
            @Override
            public String getDescription() {
                return "Set a progress on a SeekBar";
            }
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(SeekBar.class);
            }
        };
    }
}