package com.am.themovieapp.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.am.themovieapp.BuildConfig
import com.am.themovieapp.R
import com.am.themovieapp.data.vos.ActorVO
import com.am.themovieapp.databinding.ViewHolderActorBinding
import com.bumptech.glide.Glide

class ActorViewHolder(private val binding: ViewHolderActorBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(actorVO: ActorVO) {
        binding.apply {
            Glide.with(itemView.context)
                .load(BuildConfig.IMAGE_BASE_URL + actorVO.profilePath)
                .placeholder(R.drawable.placeholder_wolverine_image)
                .into(ivBestActor)

            tvActorName.text = actorVO.name
        }
    }
}