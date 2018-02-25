package com.lovoo.android.tvmaze.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lovoo.android.tvmaze.R
import com.lovoo.android.tvmaze.data.models.episode.Episode
import kotlinx.android.synthetic.main.main_item_list.view.*

class MainAdapter(private val episodes: List<Episode>, private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(episodes[position])
    }

    override fun getItemCount() = episodes.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(episode: Episode) {
            itemView.episode_number_textview.text = episode.name

            itemView.setOnClickListener {
                onItemClickListener.onClick(episode)
            }
        }
    }
    interface OnItemClickListener {
        fun onClick(episode: Episode)
    }

}