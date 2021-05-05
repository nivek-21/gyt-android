package principal.app

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {

    private val loginTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            login_btn.isEnabled = email_input.text.isNotEmpty() && password_input.text.isNotEmpty()
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = inflater.inflate(R.layout.fragment_login, container, false)

        fragment.register_btn.setOnClickListener {
            Navigation.findNavController(fragment)
                .navigate(R.id.action_fragment_login_to_fragment_register)
        }

        fragment.email_input.addTextChangedListener(loginTextWatcher)
        fragment.password_input.addTextChangedListener(loginTextWatcher)

        return fragment
    }

}