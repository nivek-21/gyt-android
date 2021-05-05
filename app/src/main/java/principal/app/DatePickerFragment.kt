package principal.app
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import java.time.Month

class DatePickerFragment : Fragment () {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val date = inflater.inflate(R.layout.date_picker,container,false)
        date.findViewById<Button>(R.id.etDate).setOnClickListener{showDatePickerDialog()}

        return inflater.inflate(R.layout.date_picker, container, false)


    }

    private fun showDatePickerDialog(){

            val datePicker = Picker_Date{day, month, year -> onDateSelected(day, month, year)} /* creamos una instancia de datePicker y le pasamos la funcion por parametros */
             datePicker.show(parentFragmentManager,"datePicker")
    }

    fun onDateSelected(day:Int, month:Int, year:Int){



    }

}
