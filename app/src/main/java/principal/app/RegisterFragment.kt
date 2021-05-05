package principal.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = inflater.inflate(R.layout.fragment_register, container, false)

        fragment.findViewById<Button>(R.id.login_btn).setOnClickListener {
            Navigation.findNavController(fragment).navigate(R.id.action_fragment_register_to_fragment_login)
        }

        return fragment
    }
}