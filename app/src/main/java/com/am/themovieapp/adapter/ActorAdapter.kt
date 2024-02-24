package com.am.themovieapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.am.themovieapp.data.vos.ActorVO
import com.am.themovieapp.databinding.ViewHolderActorBinding
import com.am.themovieapp.viewholders.ActorViewHolder

class ActorAdapter: RecyclerView.Adapter<ActorViewHolder>() {
    private var mActor: List<ActorVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = ViewHolderActorBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        if (mActor.isNotEmpty()) {
            holder.bindData(mActor[position])
        }
    }

    override fun getItemCount(): Int {
        return mActor.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(actor: List<ActorVO>) {
        mActor = actor
        notifyDataSetChanged()
    }
}