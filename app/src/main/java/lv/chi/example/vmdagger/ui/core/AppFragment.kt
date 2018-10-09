package lv.chi.example.vmdagger.ui.core

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dagger.android.support.AndroidSupportInjection
import lv.chi.example.vmdagger.ui.core.rx.DisposeOnLifecycleFragment
import lv.chi.example.vmdagger.ui.core.rx.LifecycleDisposables

abstract class AppFragment : Fragment(),
    DisposeOnLifecycleFragment {

    private var viewBinding: ViewDataBinding? = null

    override val lifecycleDisposables = LifecycleDisposables()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = DataBindingUtil.getBinding(view)
    }

    override fun onPause() {
        super<Fragment>.onPause()
        super<DisposeOnLifecycleFragment>.onPause()
    }

    override fun onStop() {
        super<Fragment>.onStop()
        super<DisposeOnLifecycleFragment>.onStop()
    }

    override fun onDestroyView() {
        super<Fragment>.onDestroyView()
        super<DisposeOnLifecycleFragment>.onDestroyView()
        viewBinding?.unbind()
        viewBinding = null
    }

    override fun onDestroy() {
        super<Fragment>.onDestroy()
        super<DisposeOnLifecycleFragment>.onDestroy()
    }

    protected fun <B : ViewDataBinding> viewBinding(): B =
        @Suppress("UNCHECKED_CAST")
        requireNotNull(viewBinding as B) {
            "View is not a data binding layout"
        }

    protected fun navController(): NavController =
        requireNotNull(view?.findNavController()) {
            "NavController not found"
        }
}