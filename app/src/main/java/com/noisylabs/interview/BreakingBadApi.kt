package com.noisylabs.interview

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BreakingBadApi {
    @GET("characters")
    fun getCharacter(
            @Query("limit") limit: Int,
            @Query("offset") offset: Int
    ) : Call<List<BreakingBadData>>
}