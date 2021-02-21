package com.demoapp.weddingprofile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demoapp.weddingprofile.databinding.ProfileListItemBinding
import com.demoapp.weddingprofile.model.ResultsItem
import com.demoapp.weddingprofile.viewmodel.ProfileViewModel

class ProfileListAdapter(private val viewModel: ProfileViewModel) :
    ListAdapter<ResultsItem, ProfileListAdapter.ViewHolder>(ArticleDifCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ProfileListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: ProfileViewModel, item: ResultsItem) {
            binding.viewmodel = viewModel
            binding.profileItem = item
            binding.executePendingBindings()
            if (item.responseSaved == true) {
//                viewModel.toastLike(item.isLiked!!)
                binding.tvProfileMessage.visibility = View.VISIBLE
                if (item.isLiked!!) {
                    binding.tvProfileMessage.text = "Profile is accepted"
                } else {
                    binding.tvProfileMessage.text = "Profile is declined"
                }
            }else{
                binding.tvProfileMessage.visibility = View.GONE
            }
            /*binding.ivAccept.setOnClickListener {
                View.OnClickListener {
                    viewModel.likePerson(true, item)
                    it.visibility = View.GONE
                    viewModel.toastLike(true)
                    binding.executePendingBindings()
                }
            }

            binding.ivDecline.setOnClickListener { view ->
                View.OnClickListener {
                    viewModel.likePerson(false,item)
                    it.visibility = View.GONE
                    viewModel.toastLike(false)
                    binding.executePendingBindings()
                }
            }*/
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProfileListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class ArticleDifCallback : DiffUtil.ItemCallback<ResultsItem>() {
    override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
        return oldItem == newItem
    }
}
