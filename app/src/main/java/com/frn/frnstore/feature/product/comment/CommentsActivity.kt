package com.frn.frnstore.feature.product.comment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frn.frnstore.FrnActivity
import com.frn.frnstore.R
import com.frn.frnstore.common.EXTRA_KEY_DATA
import com.frn.frnstore.common.EXTRA_KEY_ID
import com.frn.frnstore.data.Comment
import com.frn.frnstore.feature.product.CommentAdapter
import kotlinx.android.synthetic.main.activity_commnts.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CommentsActivity : FrnActivity() {
    val commentsViewModel: CommentsViewModel by viewModel {
        parametersOf(
            intent.extras!!.getInt(
                EXTRA_KEY_ID
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commnts)

        commentsViewModel.progressBarLiveData.observe(this){
            setProgressIndicator(it)
        }

        listComments.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        commentsViewModel.commentsLiveData.observe(this) {
            val commentAdapter = CommentAdapter(showAll = true)
            commentAdapter.comments = it as ArrayList<Comment>
            listComments.adapter = commentAdapter
        }
    }
}