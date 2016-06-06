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

package com.example.android.codelabs.agera.step1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.codelabs.agera.R;
import com.google.android.agera.Observable;
import com.google.android.agera.Receiver;
import com.google.android.agera.Supplier;
import com.google.android.agera.Updatable;

import java.util.ArrayList;
import java.util.List;


public class Step1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step1);

        // Create a Supplier-Observable
        MyDataSupplier myDataSupplier = new MyDataSupplier();
        //Repository<String> myDataSupplier = Repositories.mutableRepository("Initial value");

        // Create an Updatable
        Updatable updatable = new Updatable() {
            @Override
            public void update() {
                Log.d("AGERA", myDataSupplier.get());
            }
        };

        // Connect the dots:
        myDataSupplier.addUpdatable(updatable);

        myDataSupplier.accept("Hello Agera!");
    }

    private static class MyDataSupplier implements Observable, Supplier<String>, Receiver<String> {

        List<Updatable> mUpdatables = new ArrayList<>();

        private String mValue;

        @Override
        public void addUpdatable(@NonNull Updatable updatable) {
            mUpdatables.add(updatable);
        }

        @Override
        public void removeUpdatable(@NonNull Updatable updatable) {
            mUpdatables.remove(updatable);
        }

        @NonNull
        @Override
        public String get() {
            return mValue;
        }

        @Override
        public void accept(@NonNull String value) {
            mValue = value;
            for(Updatable updatable : mUpdatables) {
                updatable.update();
            }
        }
    }

}
