package principal.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import principal.app.Preferences
import principal.app.R
import principal.app.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProfileBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logoutBtn.setOnClickListener {

            preferences.loggedout()

            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_profile_to_fragment_login)
        }

        val isLogged: Boolean = preferences.getLogged() == true

        if (!isLogged) {
            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_profile_to_fragment_login)
        }
    }

}