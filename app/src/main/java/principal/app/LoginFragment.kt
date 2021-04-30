package principal.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = inflater.inflate(R.layout.fragment_login, container, false)

        fragment.findViewById<Button>(R.id.register_btn).setOnClickListener {
            Navigation.findNavController(fragment)
                .navigate(R.id.action_fragment_login_to_fragment_register)
        }

        // val emailInput = fragment.findViewById<EditText>(R.id.email_input)
        // val passwordInput = fragment.findViewById<EditText>(R.id.password_input)
        val loginBtn: Button = fragment.findViewById<Button>(R.id.login_btn)

        loginBtn.isEnabled = false

        return fragment
    }

}