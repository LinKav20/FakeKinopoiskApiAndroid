package com.github.flinkou.kinopoisktop.ui.topfilms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.flinkou.kinopoisktop.DI
import com.github.flinkou.kinopoisktop.R
import com.github.flinkou.kinopoisktop.databinding.FragmentTopFilmsBinding
import com.github.flinkou.kinopoisktop.entities.FilmInterface
import com.github.flinkou.kinopoisktop.entities.FilmItem
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class TopFilmsFragment : Fragment(R.layout.fragment_top_films) {
    private val component by lazy {
        TopFilmsComponent.create()
    }

    private lateinit var binding: FragmentTopFilmsBinding
    private val viewModel by viewModels<TopFilmsViewModel> { component.viewModelFactory() }

    private val adapter = MyAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopFilmsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            recyclerView.adapter = adapter
            viewModel.data.observe(viewLifecycleOwner, Observer {
                adapter.items = it
            })
        }
    }
}