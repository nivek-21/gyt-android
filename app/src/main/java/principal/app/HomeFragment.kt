package principal.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView: RecyclerView = view.recyclerView

        layoutManager = LinearLayoutManager(view.context)
        recyclerView.layoutManager = layoutManager

        adapter = RecyclerAdapter()
        recyclerView.adapter = adapter

        val datePicker1: EditText = view.date_picker_1
        val datePicker2: EditText = view.date_picker_2

        datePicker1.setOnClickListener { showDatePickerDialog(datePicker1) }
        datePicker2.setOnClickListener { showDatePickerDialog(datePicker2) }

        return view
    }

    private fun showDatePickerDialog(editText: EditText) {
        val datePicker = DatePicker { day, month, year ->
            onDateSelected(day, month, year, editText)
        }

        datePicker.show(parentFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int, editText: EditText) {
        editText.setText("$day / $month / $year")
    }
}