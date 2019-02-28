package fr.traore.adama.exoplayersample

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private val YOUTUBEVIDEO_ID = ""
    private val DIALOG_REQUEST_CODE = 1
    var TAG = MainActivity::class.java.simpleName

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
        setContentView(R.layout.activity_main)

        playerView.initialize(BuildConfig.ApiKey, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == DIALOG_REQUEST_CODE && resultCode == RESULT_CANCELED) {
            playerView.initialize(BuildConfig.ApiKey, this)
        }
    }

    override fun onStart() {
        super.onStart()

    }


    private val playerStateListener = object : YouTubePlayer.PlayerStateChangeListener{
        override fun onAdStarted() {
            Toast.makeText(this@MainActivity, "Ad started", Toast.LENGTH_SHORT).show()
        }

        override fun onLoading() {}

        override fun onVideoStarted() {
            Toast.makeText(this@MainActivity, "Video started", Toast.LENGTH_SHORT).show()
        }

        override fun onLoaded(p0: String?) {}

        override fun onVideoEnded() {
            Toast.makeText(this@MainActivity, "Video ended", Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {}
    }

    private val playerPlaybackListener = object : YouTubePlayer.PlaybackEventListener{

        override fun onSeekTo(p0: Int) {}

        override fun onBuffering(p0: Boolean) {}

        override fun onPlaying() {
            Toast.makeText(this@MainActivity, "OnPlaying", Toast.LENGTH_SHORT).show()
        }

        override fun onStopped() {
            Toast.makeText(this@MainActivity, "OnStopped", Toast.LENGTH_SHORT).show()
        }

        override fun onPaused() {
            Toast.makeText(this@MainActivity, "OnPause", Toast.LENGTH_SHORT).show()
        }

    }

}
