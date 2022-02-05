package com.frn.frnstore.feature.product

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.frn.frnstore.data.Comment
import com.frn.frnstore.databinding.ItemCommentBinding

class CommentAdapter(val showAll: Boolean = false) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    var comments = ArrayList<Comment>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var itemCommentBinding = ItemCommentBinding.bind(itemView)
        fun bindComment(comment: Comment) {
            itemCommentBinding.content.text = comment.content
            itemCommentBinding.title.text = comment.title
            itemCommentBinding.dateComment.text = comment.date
            itemCommentBinding.emailComment.text = comment.author.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemCommentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).root
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindComment(comments[position])
    }

    override fun getItemCount(): Int = if(comments.size > 3 && !showAll) 3 else comments.size

}