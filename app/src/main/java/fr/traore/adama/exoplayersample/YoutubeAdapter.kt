package fr.traore.adama.exoplayersample

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.youtube_item.view.*
import java.util.*

class YoutubeAdapter(private val activity: Activity) : RecyclerView.Adapter<YoutubeAdapter.YoutubeHolder>() {

    private var data: List<Item> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeHolder {
        return YoutubeHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.youtube_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: YoutubeHolder, position: Int) = holder.bind(data[position], activity)

    fun refreshData(data: List<Item>) {
        this.data = data
        notifyDataSetChanged()
    }

    class YoutubeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item, activity: Activity) = with(itemView) {
            Picasso.get().load(item.snippet.thumbnails.default.url).into(imv_movie)
            txv_title.text = item.snippet.title.substring(0,15)
            txv_subtext.text = item.snippet.description
            action_button_1.setOnClickListener {
                YoutubeActivity.launch(activity, item.id.videoId)
            }
        }
    }
}