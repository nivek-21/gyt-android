package principal.app

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import java.util.regex.Pattern

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

    private val passwordTextWatcher = object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if(validate(password_input.text.toString()))
                login_btn.isEnabled = true
            else{

                login_btn.isEnabled = false
                password_input.setError("Invalid Regex")
            }
        }

        override fun afterTextChanged(s: Editable?) {
        }


    }


    private fun validate(text: String?): Boolean {
        var p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%!\\-_?&])(?=\\S+\$).{8,}")
        var m = p.matcher(text)
        Log.i("test",m.matches().toString())
        return m.matches()
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
        fragment.password_input.addTextChangedListener(passwordTextWatcher)

        return fragment
    }

}