package principal.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import principal.app.ui.helpers.DatePicker
import principal.app.ui.helpers.RecyclerAdapter
import principal.app.databinding.FragmentHomeBinding
import principal.app.ui.viewmodels.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val recyclerView: RecyclerView = binding.recyclerView

        viewModel.tours.observe(viewLifecycleOwner, Observer {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(view.context)
                adapter = RecyclerAdapter(it)
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = adapter
            }
        })

        val datePicker1: EditText = binding.datePicker1
        val datePicker2: EditText = binding.datePicker2

        datePicker1.setOnClickListener { showDatePickerDialog(datePicker1) }
        datePicker2.setOnClickListener { showDatePickerDialog(datePicker2) }

        binding.searchBtn.setOnClickListener {
            val place = binding.place.text.toString()
            val startHour = binding.datePicker1.toString()
            val endHour = binding.datePicker2.toString()

            viewModel.search(place, startHour, endHour)
        }

        viewModel.getAll()
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