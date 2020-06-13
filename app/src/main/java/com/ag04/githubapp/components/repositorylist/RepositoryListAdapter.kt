package com.ag04.githubapp.components.repositorylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ag04.githubapp.R
import com.ag04.githubapp.components.base.adapter.BaseRecyclerViewAdapter
import com.ag04.githubapp.data.model.Repository
import com.ag04.githubapp.databinding.ItemRepositoryBinding
import com.bumptech.glide.Glide

/**
 * Created by akovar on 10/06/2020.
 */
class RepositoryListAdapter :
    BaseRecyclerViewAdapter<Repository, RepositoryListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_repository, parent, false)
        )
    }

    inner class ViewHolder(itemView: View) :
        BaseRecyclerViewAdapter<Repository, ViewHolder>.BaseVH(itemView) {

        private var binding = ItemRepositoryBinding.bind(itemView)

        init {
            binding.root.setOnClickListener {
                (onItemClickListener as OnRepositoryClickListener)
                    .onClick(getItemAt(adapterPosition)!!)
            }

            binding.imageOwnerAvatar.setOnClickListener {
                (onItemClickListener as OnRepositoryClickListener)
                    .onAvatarClick(getItemAt(adapterPosition)!!)
            }
        }

        override fun performBind(item: Repository) {
            binding.textRepositoryName.text = item.name
            binding.textOwnerName.text = item.user.login
            binding.textWatchersCount.text = item.watchersCount.toString()
            binding.textForksCount.text = item.forksCount.toString()
            binding.textOpenIssuesCount.text = item.openIssuesCount.toString()
            binding.textLastUpdated.text = itemView.context.getString(
                R.string.text_repository_last_updated,
                item.updatedAt
            )

            Glide.with(itemView)
                .load(item.user.avatarUrl)
                .circleCrop()
                .into(binding.imageOwnerAvatar)
        }
    }
}
