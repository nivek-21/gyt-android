package principal.app.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import principal.app.Preferences

abstract class BaseFragment<B : ViewBinding> : Fragment() {

    protected lateinit var binding: B

    protected lateinit var preferences: Preferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentBinding(inflater, container)

        preferences =
            Preferences(activity?.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE))

        return binding.root
    }

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B

}