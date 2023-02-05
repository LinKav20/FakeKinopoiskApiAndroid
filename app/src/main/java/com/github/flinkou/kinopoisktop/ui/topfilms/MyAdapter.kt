package com.github.flinkou.kinopoisktop.ui.topfilms

import androidx.recyclerview.widget.DiffUtil
import com.github.flinkou.kinopoisktop.entities.FilmInterface
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MyAdapter(
    fragment: TopFilmsFragment
) : AsyncListDifferDelegationAdapter<FilmInterface>(DIFF_CALLBACK) {

    init {
        delegatesManager.addDelegate(TopFilmsDelegates.filmItemDelegate(fragment))
            .addDelegate(TopFilmsDelegates.progressFilmItemDelegate())
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FilmInterface>() {
            override fun areItemsTheSame(oldItem: FilmInterface, newItem: FilmInterface): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: FilmInterface,
                newItem: FilmInterface
            ): Boolean =
                oldItem.equals(newItem)

        }
    }
}