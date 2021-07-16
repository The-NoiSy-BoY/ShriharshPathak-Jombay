package com.noisylabs.interview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val responseText = findViewById<TextView>(R.id.response_text)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.breakingbadapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val breakingBadApi = retrofit.create(BreakingBadApi::class.java)

        val call = breakingBadApi.getCharacter(10, 0)

        call.enqueue(object : Callback<List<BreakingBadData>> {
            override fun onResponse(
                call: Call<List<BreakingBadData>>,
                response: Response<List<BreakingBadData>>
            ) {
                if (!response.isSuccessful) return
                Log.i(TAG, "onResponse: ${response.body().toString()}")
                responseText.text = response.body().toString()
            }

            override fun onFailure(call: Call<List<BreakingBadData>>, t: Throwable) {
                Log.i(TAG, "onFailure: Connection failed: ${t.message}")
            }
        })
    }
}