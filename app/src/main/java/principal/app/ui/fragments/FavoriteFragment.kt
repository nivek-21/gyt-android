package principal.app.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import principal.app.databinding.FragmentFavoritesBinding

class FavoriteFragment : BaseFragment<FragmentFavoritesBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentFavoritesBinding.inflate(inflater, container, false)

}