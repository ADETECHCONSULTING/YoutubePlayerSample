package fr.traore.adama.exoplayersample

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_youtube.*

class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private var YOUTUBEVIDEO_ID = "aqz-KE-bpKQ"
    private val DIALOG_REQUEST_CODE = 1
    var TAG = YoutubeActivity::class.java.simpleName

    companion object {
        val EXTRA_VID_ID = "vid.id"
        fun launch(activity: Activity, extraId: String){
            val intent = Intent(activity, YoutubeActivity::class.java)
            intent.putExtra(EXTRA_VID_ID, extraId)
            activity.startActivity(intent)
            activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
        Toast.makeText(this, "Initialized YoutubePlayer successfully", Toast.LENGTH_LONG).show()
        if(!wasRestored){
            player?.loadVideo(YOUTUBEVIDEO_ID)
            player?.setPlayerStateChangeListener(playerStateListener)
            player?.setPlaybackEventListener(playerPlaybackListener)
        }else{
            player?.play()
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, result: YouTubeInitializationResult?) {
        if (result?.isUserRecoverableError == true) {
            result.getErrorDialog(this, DIALOG_REQUEST_CODE).show()
        } else {
            Toast.makeText(this, "The YouTubePlayer initialization error $(result)", Toast.LENGTH_LONG).show()
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)

        if(intent.hasExtra(EXTRA_VID_ID)){
            YOUTUBEVIDEO_ID = intent.getStringExtra(EXTRA_VID_ID)
        }

        playerView.initialize(BuildConfig.ApiKey, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == DIALOG_REQUEST_CODE && resultCode == RESULT_CANCELED) {
            playerView.initialize(BuildConfig.ApiKey, this)
        }
    }


    private val playerStateListener = object : YouTubePlayer.PlayerStateChangeListener{
        override fun onAdStarted() {
            Toast.makeText(this@YoutubeActivity, "Ad started", Toast.LENGTH_SHORT).show()
        }

        override fun onLoading() {}

        override fun onVideoStarted() {
            Toast.makeText(this@YoutubeActivity, "Video started", Toast.LENGTH_SHORT).show()
        }

        override fun onLoaded(p0: String?) {}

        override fun onVideoEnded() {
            Toast.makeText(this@YoutubeActivity, "Video ended", Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {}
    }

    private val playerPlaybackListener = object : YouTubePlayer.PlaybackEventListener{

        override fun onSeekTo(p0: Int) {}

        override fun onBuffering(p0: Boolean) {}

        override fun onPlaying() {
            Toast.makeText(this@YoutubeActivity, "OnPlaying", Toast.LENGTH_SHORT).show()
        }

        override fun onStopped() {
            Toast.makeText(this@YoutubeActivity, "OnStopped", Toast.LENGTH_SHORT).show()
        }

        override fun onPaused() {
            Toast.makeText(this@YoutubeActivity, "OnPause", Toast.LENGTH_SHORT).show()
        }

    }

}
