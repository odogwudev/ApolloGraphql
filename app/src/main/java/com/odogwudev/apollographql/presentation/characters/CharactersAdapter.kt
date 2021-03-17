package com.odogwudev.apollographql.presentation.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.odogwudev.apollographql.R
import com.odogwudev.apollographql.databinding.ItemCharacterBinding
import com.odogwudev.apollographql.domain.models.SingleCharacterModel

class CharactersAdapter(
    private val list: List<SingleCharacterModel>,
    private val clickAction: (SingleCharacterModel) -> Unit
) :
    RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(list[position])
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(private val view: ItemCharacterBinding) :
        RecyclerView.ViewHolder(view.root) {
        init {
            view.root.setOnClickListener { clickAction(list[adapterPosition]) }
        }

        fun bindTo(singleCharacterModel: SingleCharacterModel) {
            with(view) {
                singleCharacterModel.apply {
                    tvCharacter.text = name
                    imgCharacter.load(image) {
                        crossfade(true)
                        placeholder(R.drawable.ic_baseline_filter_hdr_24)
                    }
                }
            }
        }
    }
}