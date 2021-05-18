package principal.app

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isLogged = context?.let { Preferences(it).getLogged() }

        if (isLogged == false) {
            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_profile_to_fragment_login)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val profile = inflater.inflate(R.layout.fragment_profile, container, false)

        profile.logout_btn.setOnClickListener {
            context?.let { it1 -> Preferences(it1).loggedout() }

            Navigation.findNavController(profile)
                .navigate(R.id.action_fragment_profile_to_fragment_login)
        }

        profile.name_field.text = context?.let { Preferences(it).getUser().name }

        return profile
    }

}