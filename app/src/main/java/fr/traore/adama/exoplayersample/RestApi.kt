package fr.traore.adama.exoplayersample

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RestApi {

    private var youtubeApi: IYoutubeApiInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        youtubeApi = retrofit.create(IYoutubeApiInterface::class.java)
    }

    fun getChannelVideos(channelId: String) : Call<ModelChannel> {
        return youtubeApi.getFirst50VideosForChannelId(channelId = channelId, maxResult = 50)
    }

}