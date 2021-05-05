package principal.app

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

/* lo que estamos diciendo es que la clase picker que revise un parametro
* extiende del DialogFragment una clase interna de android y por
* ultimo agregamos un listerner, implementa esta funcion cuando seleccionemos una fecha en el calendario*/

class Picker_Date (val listener: (day:Int, month:Int, yer:Int) -> Unit):DialogFragment(),
    DatePickerDialog.OnDateSetListener{
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener(dayOfMonth,month,year) /* cuando el usuario seleccione una fecha se llama al metodo OnDate y el metodo llama al listener
                                               y este ejecuta un codigo  */
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val c = Calendar.getInstance()  /* en esta variable tenemos un calendatio */
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        /* aca tenemos el dia actual , mes actual y el anno actual */

        /*ahora vamos a crear una instancia del objeto lo que va ver el usuario*/

        val picker = DatePickerDialog(activity as Context, this, year, month, day)
        return picker
    }

}