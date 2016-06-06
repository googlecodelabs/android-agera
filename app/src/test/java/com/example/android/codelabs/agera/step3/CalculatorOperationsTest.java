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

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import android.support.v4.util.Pair;

import com.example.android.codelabs.agera.CalculatorOperations;
import com.example.android.codelabs.agera.R;
import com.google.android.agera.Result;

import org.junit.Test;

public class CalculatorOperationsTest {

    private static final Pair<Integer, Integer> OPERANDS_1_2 = new Pair<>(1, 2);
    private static final Pair<Integer, Integer> OPERANDS_1_0 = new Pair<>(1, 0);

    private static final Result<Integer> OPERATION_ADD = Result.present(R.id.radioButtonAdd);
    private static final Result<Integer> OPERATION_SUB = Result.present(R.id.radioButtonSub);
    private static final Result<Integer> OPERATION_MULT = Result.present(R.id.radioButtonMult);
    private static final Result<Integer> OPERATION_DIV = Result.present(R.id.radioButtonDiv);

    @Test
    public void attemptOperationAdd() throws Exception {
        Pair<Integer, Integer> operands = new Pair<>(1,2);
        Result<Integer> result = CalculatorOperations.attemptOperation(operands, OPERATION_ADD);
        assertThat(result.get(), is(3));
    }

    @Test
    public void attemptOperationSub() throws Exception {
        Result<Integer> result = CalculatorOperations.attemptOperation(OPERANDS_1_2, OPERATION_SUB);
        assertThat(result.get(), is(-1));
    }

    @Test
    public void attemptOperationMult() throws Exception {
        Result<Integer> result =
                CalculatorOperations.attemptOperation(OPERANDS_1_2, OPERATION_MULT);
        assertThat(result.get(), is(2));
    }

    @Test
    public void attemptOperationDiv() throws Exception {
        Result<Integer> result =
                CalculatorOperations.attemptOperation(OPERANDS_1_2, OPERATION_DIV);
        assertThat(result.get(), is(0));
    }

    @Test
    public void attemptOperationDivZero_fails() throws Exception {
        Result<Integer> result = CalculatorOperations.attemptOperation(OPERANDS_1_0, OPERATION_DIV);
        assertTrue(result.failed());
    }
}