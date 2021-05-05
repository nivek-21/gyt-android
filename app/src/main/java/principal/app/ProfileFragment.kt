package principal.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val login = inflater.inflate(R.layout.fragment_login, container, false)
        login.findViewById<Button>(R.id.register_btn).setOnClickListener {
            Navigation.findNavController(login)
                .navigate(R.id.action_fragment_login_to_fragment_register)
        }
        return login
    }

}