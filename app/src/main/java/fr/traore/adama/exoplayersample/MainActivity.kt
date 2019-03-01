package fr.traore.adama.exoplayersample

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import com.google.android.youtube.player.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var TAG = MainActivity::class.java.simpleName
    var CHANNEL_ID = "UCNoI_AU3zbmPsXqqOmJR0kw"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        YoutubeActivity.launch(this)
    }

}
