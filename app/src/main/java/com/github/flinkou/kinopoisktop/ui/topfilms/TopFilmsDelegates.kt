package com.github.flinkou.kinopoisktop.ui.topfilms

import android.app.Activity
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.github.flinkou.kinopoisktop.databinding.ItemFilmBinding
import com.github.flinkou.kinopoisktop.databinding.ItemProgressFilmBinding
import com.github.flinkou.kinopoisktop.entities.FilmInterface
import com.github.flinkou.kinopoisktop.entities.FilmItem
import com.github.flinkou.kinopoisktop.entities.ProgressFilmItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object TopFilmsDelegates {

    fun filmItemDelegate(fragment: TopFilmsFragment) = adapterDelegateViewBinding<FilmItem, FilmInterface, ItemFilmBinding>(
        { inflater, container -> ItemFilmBinding.inflate(inflater, container, false) }
    ) {
        bind {
            Glide.with(binding.root)
                .load(item.poster)
                .centerCrop()
                .transform(RoundedCorners(10))
                .transition(withCrossFade())
                .into(binding.posterImageview)
            binding.titleTextview.text = item.title

            val desc = item.genre + " (${item.year})"
            binding.yearTextview.text = desc

            val positionInTop = "${adapterPosition + 1}."
            binding.numberInTopTextview.text = positionInTop

            binding.filmCard.setOnClickListener{
                Log.d("FILM_INFO", (item.id).toString())
                val action = TopFilmsFragmentDirections.actionTopFilmsFragmentToFilmInfoFragment((item.id).toString())
                fragment.findNavController().navigate(action)
            }
        }

        onViewRecycled {
            if ((binding.root.context as? Activity)?.isDestroyed?.not() == true) {
                Glide.with(binding.root).clear(binding.posterImageview)
            }
        }
    }

    fun progressFilmItemDelegate() =
        adapterDelegateViewBinding<ProgressFilmItem, FilmInterface, ItemProgressFilmBinding>(
            { inflater, container -> ItemProgressFilmBinding.inflate(inflater, container, false) }
        ) {}
}