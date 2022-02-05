package com.frn.frnstore


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
        get() {
          val viewGroup = window.decorView.findViewById(android.R.id.content) as ViewGroup
            if (viewGroup !is CoordinatorLayout){
                viewGroup.children.forEach {
                    if (it is CoordinatorLayout)
                        return it
                }
                throw IllegalAccessException("RootView must be instance of CoordinatorLayout!")
            } else
                return viewGroup
        }
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

    val _progressBarLiveData = MutableLiveData<Boolean>()
    val progressBarLiveData: LiveData<Boolean>
        get() = _progressBarLiveData


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}