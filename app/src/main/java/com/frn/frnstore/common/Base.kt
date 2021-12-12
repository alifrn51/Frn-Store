package com.frn.frnstore

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class FrnFragment : Fragment(), FrnView {

    override val rootView: CoordinatorLayout?
        get() = view as CoordinatorLayout?
    override val viewContext: Context?
        get() = context
}

abstract class FrnActivity : AppCompatActivity(), FrnView {

    override val rootView: CoordinatorLayout?
        get() = window.decorView.rootView as CoordinatorLayout?
    override val viewContext: Context?
        get() = this

}

interface FrnView {

    val rootView: CoordinatorLayout?
    val viewContext: Context?
    fun setProgressIndicator(mustShow: Boolean) {
        rootView?.let {
            var loadingView = it.findViewById<View>(R.id.loading_view)
            if (loadingView == null && mustShow) {
                loadingView = LayoutInflater.from(viewContext).inflate(R.layout.view_loading , rootView , false)
                it.addView(loadingView)
            }

            loadingView.visibility = if (mustShow) View.VISIBLE
            else View.GONE
        }
    }
}

abstract class FrnViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}