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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.codelabs.agera.R;
import com.google.android.agera.MutableRepository;
import com.google.android.agera.Repositories;
import com.google.android.agera.Repository;
import com.google.android.agera.Updatable;

public class Step2Activity extends AppCompatActivity {

    public static final String VALUE_KEY = "VALUE_KEY";

    private final MutableRepository<Integer> valueRepository = Repositories.mutableRepository(0);

    private Repository<String> textValueRepository;

    private Updatable mTextValueUpdatable;

    private TextView mValueTv;

    private Button mIncrementBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step2);

        mValueTv = (TextView) findViewById(R.id.step2_value_tv);
        mIncrementBt = (Button) findViewById(R.id.increment_bt);

        // Set onClickListener

        // Create complex repository:
        // textValueRepository = Repositories.repositoryWithInitialValue("N/A")

        // Create updatable
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Add updatables here.
    }

    @Override
    protected void onStop() {
        // Remove updatables here.
        super.onStop();
    }
}
