package fr.traore.adama.exoplayersample

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface IYoutubeApiInterface{
    @GET("youtube/v3/search")
    fun getFirst50VideosForChannelId(
        @Query("key") apiKey: String = BuildConfig.ApiKey,
        @Query("channelId") channelId: String,
        @Query("part") part: String = "snippet,id",
        @Query("maxResults") maxResult: Int
    ) : Call<ModelChannel>
}