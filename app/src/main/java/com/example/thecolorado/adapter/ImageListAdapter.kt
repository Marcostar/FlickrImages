package com.example.thecolorado.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.thecolorado.data.FlickrImage
import com.example.thecolorado.databinding.PhotoListItemBinding

class ImageListAdapter(val clickListener: ImageClick) :
    ListAdapter<FlickrImage, ImageListAdapter.ViewHolder>(ImageListDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }


    class ViewHolder private constructor(val binding: PhotoListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FlickrImage, clickListener: ImageClick) {
            binding.imageList = item
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PhotoListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }


}

class ImageListDiffCallback : DiffUtil.ItemCallback<FlickrImage>() {

    override fun areItemsTheSame(oldItem: FlickrImage, newItem: FlickrImage): Boolean {
        return oldItem.title == newItem.title
    }


    override fun areContentsTheSame(oldItem: FlickrImage, newItem: FlickrImage): Boolean {
        return oldItem == newItem
    }


}

class ImageClick(val row: (FlickrImage) -> Unit) {

    fun onClick(image: FlickrImage) = row(image)
}