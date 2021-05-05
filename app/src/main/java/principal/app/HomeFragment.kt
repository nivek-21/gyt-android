package principal.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        layoutManager = LinearLayoutManager(view.context)
        recyclerView.layoutManager = layoutManager

        adapter = RecyclerAdapter()
        recyclerView.adapter = adapter

        view.findViewById<EditText>(R.id.date_picker_1).setOnClickListener { showDatePickerDialog() }
        view.findViewById<EditText>(R.id.date_picker_2).setOnClickListener { showDatePickerDialog() }

        return view
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePicker { day, month, year ->
            onDateSelected(day, month, year)
        }

        datePicker.show(parentFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        date_picker_1.setText("$day $month $year")
    }
}