/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.trantordev.androidbankapp.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.trantordev.androidbankapp.data.AppDatabase
import com.trantordev.androidbankapp.data.Login
import com.trantordev.androidbankapp.util.USER_DATA_FILENAME

import kotlinx.coroutines.coroutineScope

class UserSeedingDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(USER_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val userType = object : TypeToken<List<Login>>() {}.type
                    val userList: List<Login> = Gson().fromJson(jsonReader, userType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.loginDao().insertAll(userList)

                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private val TAG = UserSeedingDatabaseWorker::class.java.simpleName
    }
}