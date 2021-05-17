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
import kotlinx.android.synthetic.main.fragment_login.view.*
import principal.app.services.RetrofitService
import principal.app.services.dto.Auth
import principal.app.services.dto.Login
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class LoginFragment : Fragment() {

//    private var sharedPreferences: SharedPreferences? = null

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
        val login = inflater.inflate(R.layout.fragment_login, container, false)

//        val prefs = Preferences(login.context)

        login.findViewById<Button>(R.id.register_btn_login).setOnClickListener {
            Navigation.findNavController(login)
                .navigate(R.id.action_fragment_login_to_fragment_register)
        }

        login.email_input.addTextChangedListener(loginTextWatcher)
        login.password_input.addTextChangedListener(loginTextWatcher)

//        sharedPreferences = activity?.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)

        login.findViewById<Button>(R.id.login_btn).setOnClickListener {

            val retrofitService = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(RetrofitService::class.java)

            val email = login.findViewById<EditText>(R.id.email_input).text
            val password = login.findViewById<EditText>(R.id.password_input).text

            val data = Login(email.toString(), password.toString(), "android_device")

            retrofitService.login(data)
                .enqueue(object : Callback<Auth> {
                    override fun onResponse(call: Call<Auth>, response: Response<Auth>) {
                        Log.i("Testing", response.body().toString())

                        val body = response.body();

                        if (body != null) {
//                            val editor = sharedPreferences?.edit()
//                            editor?.putString("isLogged", "true")
//                            editor?.apply()

                            context?.let { it1 ->
                                val preferences = Preferences(it1)

                                preferences.saveUser(body.user)
                                preferences.saveToken(body.token)
                                preferences.logged()
                            }

                            Navigation.findNavController(login)
                                .navigate(R.id.action_fragment_login_to_fragment_profile)

                        } else {
                            Toast.makeText(context, "¡El usuario no existe!", Toast.LENGTH_SHORT)
                                .show()
                        }

                    }

                    override fun onFailure(call: Call<Auth>, t: Throwable) {
//                        val editor = sharedPreferences?.edit()
//                        editor?.putString("logged", "false")
//                        editor?.apply()

                        context?.let { it -> Preferences(it).loggedout() }

                        Log.e("Testing", t.toString())
                        Log.e("Testing", "Login Failed!")
                    }
                })

        }

        return login
    }

}