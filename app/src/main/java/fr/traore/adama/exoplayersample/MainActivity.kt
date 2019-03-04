package fr.traore.adama.exoplayersample

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.youtube.player.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    var TAG = MainActivity::class.java.simpleName
    var CHANNEL_ID = "UCNoI_AU3zbmPsXqqOmJR0kw"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = YoutubeAdapter(this)
        rcvMain.adapter = adapter
        rcvMain.layoutManager = LinearLayoutManager(this)

        val vidsRequest = RestApi().getChannelVideos(CHANNEL_ID)

        vidsRequest.enqueue(object: Callback<ModelChannel>{
            override fun onResponse(call: Call<ModelChannel>, response: Response<ModelChannel>) {
                Toast.makeText(this@MainActivity, "OK : "+response.body()?.items?.size, Toast.LENGTH_SHORT).show()
                adapter.refreshData(response.body()?.items!!)
            }

            override fun onFailure(call: Call<ModelChannel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "NOT OK : "+t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })

    }

}
