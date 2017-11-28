package com.burakeregar.githubsearch.home.viewholder

import android.view.View
import com.burakeregar.easiestgenericrecycleradapter.base.GenericViewHolder
import com.burakeregar.githubsearch.home.model.RepoItem
import kotlinx.android.synthetic.main.row_repos.view.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by Burak Eregar on 23.05.2017.
 * burakeregar@gmail.com
 * https://github.com/burakeregar
 */
class RepoViewHolder(itemView: View?) : GenericViewHolder<Any>(itemView) {
    private lateinit var item: RepoItem

    override fun bindData(p0: Any?) {
        item = p0 as RepoItem
        with(itemView) {
            rowImageView?.setImageURI(item.owner?.avatarUrl)
            rowName?.text = item.name
            rowDescription?.text = item.description
            rowStarCount?.text = item.stargazersCount.toString()
            rowMarsRoot.setOnClickListener {
                EventBus.getDefault().post(item)
            }
        }
    }
}