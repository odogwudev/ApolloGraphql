package com.odogwudev.apollographql.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.odogwudev.apollographql.databinding.ItemEpisodeBinding
import com.odogwudev.apollographql.domain.models.EpisodeModel

class EpisodesAdapter(private val list: List<EpisodeModel>) :
    RecyclerView.Adapter<EpisodesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemEpisodeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(list[position])
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(private val view: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bindTo(episodeModel: EpisodeModel) {
            view.tvEpisode.text = episodeModel.name
        }
    }
}