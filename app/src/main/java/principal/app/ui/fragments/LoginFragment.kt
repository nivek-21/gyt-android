package principal.app.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import principal.app.R
import principal.app.databinding.FragmentLoginBinding
import java.util.regex.Pattern
import principal.app.ui.viewmodels.AuthViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private lateinit var viewModel: AuthViewModel

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    private val loginTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.loginBtn.isEnabled =
                binding.emailInput.text.isNotEmpty() && binding.passwordInput.text.isNotEmpty()
        }

        override fun afterTextChanged(s: Editable?) {
            //
        }
    }

    private val passwordTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (validate(binding.passwordInput.text.toString()))
                binding.loginBtn.isEnabled = true
            else {
                binding.loginBtn.isEnabled = false
                binding.passwordInput.error = "¡Formato de contraseña inválido!"
            }
        }

        override fun afterTextChanged(s: Editable?) {
            //
        }
    }

    private fun validate(text: String?): Boolean {
        var p =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%!\\-_?&])(?=\\S+\$).{8,}")
        var m = p.matcher(text)
        return m.matches()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        binding.registerBtnLogin.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_login_to_fragment_register)
        }

//        binding.emailInput.addTextChangedListener(loginTextWatcher)
//        binding.passwordInput.addTextChangedListener(passwordTextWatcher)

        viewModel.auth.observe(viewLifecycleOwner, {

            preferences.saveUser(viewModel.auth.value!!.user)
            preferences.saveToken(viewModel.auth.value!!.token)
            preferences.logged()

            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_login_to_fragment_home)
        })

        viewModel.notification.observe(viewLifecycleOwner, {
            Toast.makeText(context, viewModel.notification.value, Toast.LENGTH_SHORT)
                .show()
        })

        binding.loginBtn.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()

            viewModel.login(email, password)
        }
    }
}