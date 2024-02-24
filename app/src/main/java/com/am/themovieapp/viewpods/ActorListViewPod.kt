package com.am.themovieapp.viewpods

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.am.themovieapp.adapter.ActorAdapter
import com.am.themovieapp.data.vos.ActorVO
import kotlinx.android.synthetic.main.view_pod_actor_list.view.*

class ActorListViewPod @JvmOverloads constructor(context: Context?, attrs: AttributeSet? =null) : RelativeLayout(context, attrs) {
    lateinit var mAdapter: ActorAdapter
    override fun onFinishInflate() {
        setUPActorRecyclerView()
        super.onFinishInflate()
    }

    private fun setUPActorRecyclerView() {
        mAdapter = ActorAdapter()
        rvBestActors.adapter = mAdapter
        rvBestActors.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    fun setUpActorViewPod(backgroundColorReference: Int, titleText: String, moreTitleText: String) {
        setBackgroundColor(ContextCompat.getColor(context,backgroundColorReference))
        tvBestActors.text = titleText
        tvMoreActors.text = moreTitleText
        tvMoreActors.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    fun setData(it: List<ActorVO>) {
        mAdapter.setNewData(it)
    }
}