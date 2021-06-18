package principal.app.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import principal.app.Constant
import principal.app.Preferences
import principal.app.R
import principal.app.databinding.FragmentRegisterBinding
import principal.app.services.RetrofitService
import principal.app.repositories.responses.dto.Auth
import principal.app.repositories.responses.dto.Register
import principal.app.ui.viewmodels.AuthViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.regex.Pattern

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    private lateinit var viewModel: AuthViewModel

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRegisterBinding.inflate(inflater, container, false)

    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.registerButton.isEnabled =
                binding.registerButton.text.isNotEmpty() && binding.registerEmailInput.text.isNotEmpty() && binding.registerPasswordInput.text.isNotEmpty() && binding.registerPasswordConfirmationInput.text.isNotEmpty()
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
            binding.registerButton.isEnabled =
                binding.registerButton.text.isNotEmpty() && binding.registerEmailInput.text.isNotEmpty() && binding.registerPasswordInput.text.isNotEmpty() && binding.registerPasswordConfirmationInput.text.isNotEmpty()

            if (validate(binding.registerPasswordInput.text.toString()))
                binding.registerButton.isEnabled = true
            else {
                binding.registerButton.isEnabled = false
                binding.registerPasswordInput.error = "¡Formato de contraseña inválido!"
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

        viewModel.notification.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, viewModel.notification.value, Toast.LENGTH_SHORT)
                .show()
        })

        viewModel.auth.observe(viewLifecycleOwner, Observer {
            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_register_to_fragment_login)
        })

        binding.loginBtn.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_register_to_fragment_login)
        }

//        binding.registerPasswordInput.addTextChangedListener(passwordTextWatcher)
//
        val etName: EditText = binding.registerFullNameInput
        val etEmail: EditText = binding.registerEmailInput
        val etPassword: EditText = binding.registerPasswordInput
        val etConfirmPassword: EditText = binding.registerPasswordConfirmationInput
//
//        etName.addTextChangedListener(watcher)
//        etEmail.addTextChangedListener(watcher)
//        etPassword.addTextChangedListener(watcher)
//        etConfirmPassword.addTextChangedListener(watcher)

        binding.registerButton.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            if (password == confirmPassword) {

                viewModel.register(name, email, password, confirmPassword)

//                val register = Register(name, email, password, confirmPassword, "android_device")

//                retrofitService.register(register).enqueue(object : Callback<Auth> {
//                    override fun onResponse(call: Call<Auth>, response: Response<Auth>) {
//                        val body = response.body()
//
//                        if (body != null) {
//                            context?.let { it1 ->
//                                val preferences = Preferences(it1)
//
//                                preferences.saveUser(body.user)
//                                preferences.saveToken(body.token)
//                                preferences.logged()
//                            }
//
//                            Navigation.findNavController(view)
//                                .navigate(R.id.action_fragment_register_to_fragment_profile)
//                        } else {
//                            Toast.makeText(
//                                context,
//                                "Datos erroneos!",
//                                Toast.LENGTH_SHORT
//                            )
//                                .show()
//                        }
//
//                    }
//
//                    override fun onFailure(call: Call<Auth>, t: Throwable) {
//                        Toast.makeText(
//                            context,
//                            "El app está teniendo problemas!",
//                            Toast.LENGTH_SHORT
//                        )
//                            .show()
//                    }
//
//                })

            } else {
                Toast.makeText(context, "La contraseña digitada es diferente!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val fragment = inflater.inflate(R.layout.fragment_register, container, false)
//
//        fragment.findViewById<Button>(R.id.login_btn).setOnClickListener {
//            Navigation.findNavController(fragment)
//                .navigate(R.id.action_fragment_register_to_fragment_login)
//        }
//
//        fragment.findViewById<EditText>(R.id.register_password_input)
//            .addTextChangedListener(passwordTextWatcher)
//
//        val etName: EditText = fragment.register_full_name_input
//        val etEmail: EditText = fragment.register_email_input
//        val etPassword: EditText = fragment.register_password_input
//        val etConfirmPassword: EditText = fragment.register_password_confirmation_input
//
//        etName.addTextChangedListener(watcher)
//        etEmail.addTextChangedListener(watcher)
//        etPassword.addTextChangedListener(watcher)
//        etConfirmPassword.addTextChangedListener(watcher)
//
//        fragment.findViewById<Button>(R.id.register_button).setOnClickListener {
//            val name = etName.text.toString()
//            val email = etEmail.text.toString()
//            val password = etPassword.text.toString()
//            val confirmPassword = etConfirmPassword.text.toString()
//
//            if (password == confirmPassword) {
//                val retrofitService = Retrofit.Builder()
//                    .baseUrl(Constant.BASE_URL)
//                    .addConverterFactory(MoshiConverterFactory.create())
//                    .build()
//                    .create(RetrofitService::class.java)
//
//                val register = Register(name, email, password, confirmPassword, "android_device")
//
//                retrofitService.register(register).enqueue(object : Callback<Auth> {
//                    override fun onResponse(call: Call<Auth>, response: Response<Auth>) {
//                        val body = response.body()
//
//                        if (body != null) {
//                            context?.let { it1 ->
//                                val preferences = Preferences(it1)
//
//                                preferences.saveUser(body.user)
//                                preferences.saveToken(body.token)
//                                preferences.logged()
//                            }
//
//                            Navigation.findNavController(fragment)
//                                .navigate(R.id.action_fragment_register_to_fragment_profile)
//                        } else {
//                            Toast.makeText(
//                                context,
//                                "Datos erroneos!",
//                                Toast.LENGTH_SHORT
//                            )
//                                .show()
//                        }
//
//                    }
//
//                    override fun onFailure(call: Call<Auth>, t: Throwable) {
//                        Toast.makeText(
//                            context,
//                            "El app está teniendo problemas!",
//                            Toast.LENGTH_SHORT
//                        )
//                            .show()
//                    }
//
//                })
//
//            } else {
//                Toast.makeText(context, "La contraseña digitada es diferente!", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }
//
//        return fragment
//    }
}