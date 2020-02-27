package com.example.thecolorado.screens

import android.app.Application
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.thecolorado.R
import com.example.thecolorado.adapter.ImageClick
import com.example.thecolorado.adapter.ImageListAdapter
import com.example.thecolorado.databinding.ColoradoPhotosFragmentBinding


class ColoradoPhotos : Fragment() {


    private lateinit var binding: ColoradoPhotosFragmentBinding

    private val viewModel: ColoradoPhotosViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, ColoradoPhotosViewModel.Factory(activity.application))
            .get(ColoradoPhotosViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.colorado_photos_fragment, container, false
        )

        binding.setLifecycleOwner(this)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(ColoradoPhotosViewModel::class.java)

        binding.coloradoPhotoViewModel = viewModel


        val adapter = ImageListAdapter(ImageClick {
            Toast.makeText(activity, it.title, Toast.LENGTH_LONG).show()
        })

        val manager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        manager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        binding.photoList.layoutManager = manager
        binding.photoList.adapter = adapter


        viewModel.imageList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

    }

}
