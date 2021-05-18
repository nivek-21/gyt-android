package principal.app

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*
import principal.app.services.RetrofitService
import principal.app.services.dto.Auth
import principal.app.services.dto.Register
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RegisterFragment : Fragment() {

    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            register_button.isEnabled =
                register_full_name_input.text.isNotEmpty() && register_email_input.text.isNotEmpty() && register_password_input.text.isNotEmpty() && register_password_confirmation_input.text.isNotEmpty()
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = inflater.inflate(R.layout.fragment_register, container, false)

        fragment.findViewById<Button>(R.id.login_btn).setOnClickListener {
            Navigation.findNavController(fragment)
                .navigate(R.id.action_fragment_register_to_fragment_login)
        }

        val etName: EditText = fragment.register_full_name_input
        val etEmail: EditText = fragment.register_email_input
        val etPassword: EditText = fragment.register_password_input
        val etConfirmPassword: EditText = fragment.register_password_confirmation_input

        etName.addTextChangedListener(watcher)
        etEmail.addTextChangedListener(watcher)
        etPassword.addTextChangedListener(watcher)
        etConfirmPassword.addTextChangedListener(watcher)

        fragment.findViewById<Button>(R.id.register_button).setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            if (password == confirmPassword) {
                val retrofitService = Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
                    .create(RetrofitService::class.java)

                val register = Register(name, email, password, confirmPassword, "android_device")

                retrofitService.register(register).enqueue(object : Callback<Auth> {
                    override fun onResponse(call: Call<Auth>, response: Response<Auth>) {
                        val body = response.body()

                        if (body != null) {
                            context?.let { it1 ->
                                val preferences = Preferences(it1)

                                preferences.saveUser(body.user)
                                preferences.saveToken(body.token)
                                preferences.logged()
                            }

                            Navigation.findNavController(fragment)
                                .navigate(R.id.action_fragment_register_to_fragment_profile)
                        } else {
                            Toast.makeText(
                                context,
                                "Datos erroneos!",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }

                    }

                    override fun onFailure(call: Call<Auth>, t: Throwable) {
                        Toast.makeText(
                            context,
                            "El app está teniendo problemas!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }

                })

            } else {
                Toast.makeText(context, "La contraseña digitada es diferente!", Toast.LENGTH_SHORT)
                    .show()
            }


        }

        return fragment
    }
}