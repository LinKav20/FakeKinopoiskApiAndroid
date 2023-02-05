package com.github.flinkou.kinopoisktop.ui.filminfo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.github.flinkou.kinopoisktop.databinding.FragmentFilmInfoBinding
import com.github.flinkou.kinopoisktop.di.AppComponent


class FilmInfoFragment : Fragment() {
    private val component by lazy {
        FilmInfoComponent.create()
    }

    private lateinit var binding: FragmentFilmInfoBinding
    private val viewModel by viewModels<FilmInfoViewModel> { component.viewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilmInfoBinding.inflate(layoutInflater, container, false)
        initFields()
        binding.backButton.setOnClickListener { navigateBack() }
        return binding.root
    }

    private fun initFields() {
        val id = arguments?.getString("film_id")

        if (id == null) {
            val toast = Toast.makeText(
                context,
                "Something goes wrong",
                Toast.LENGTH_LONG
            ).show()
            navigateBack()
        } else {
            viewModel.loadFilm(id)

            viewModel.film.observe(viewLifecycleOwner, Observer {
                Glide.with(binding.root)
                    .load(it.poster)
                    .centerCrop()
                    .transform(RoundedCorners(10))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.posterImageview)

                binding.titleTextviewFull.text = it.title
                val desc = "Жанры: ${it.genre}\nСтраны: ${it.countries}\n(${it.year})"
                binding.yearTextview.text = desc
                binding.descriptionTextviewFull.text = it.description
            })
        }
    }

    private fun navigateBack() {
        findNavController().popBackStack()
    }
}