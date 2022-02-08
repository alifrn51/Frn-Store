package com.frn.frnstore.common


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frn.frnstore.R
import com.frn.frnstore.feature.auth.AuthActivity
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class FrnFragment : Fragment(), FrnView {

    override val rootView: CoordinatorLayout?
        get() = view as CoordinatorLayout?
    override val viewContext: Context?
        get() = context

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this);
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}

abstract class FrnActivity : AppCompatActivity(), FrnView {

    override val rootView: CoordinatorLayout?
        get() {
            val viewGroup = window.decorView.findViewById(android.R.id.content) as ViewGroup
            if (viewGroup !is CoordinatorLayout) {
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

}

interface FrnView {

    val rootView: CoordinatorLayout?
    val viewContext: Context?
    fun setProgressIndicator(mustShow: Boolean) {
        rootView?.let {
            var loadingView = it.findViewById<View>(R.id.loading_view)

            if (loadingView == null && mustShow) {
                loadingView =
                    LayoutInflater.from(viewContext).inflate(R.layout.view_loading, rootView, false)
                it.addView(loadingView)
            }

            loadingView.visibility = if (mustShow) View.VISIBLE
            else View.GONE
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun showError(frnException: FrnException) {
        viewContext?.let {
            when (frnException.type) {
                FrnException.Type.SIMPLE -> showSnackBar(
                    frnException.serverMessage ?: it.getString(frnException.userFriendlyMessage)
                )

                FrnException.Type.AUTH -> {
                    it.startActivity(Intent(it, AuthActivity::class.java))
                    Toast.makeText(it , frnException.serverMessage , Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }
    }

    fun showSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
        rootView?.let {
            Snackbar.make(it, message, duration).show()
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