package principal.app.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import principal.app.databinding.FragmentBookingBinding

class BookingFragment : BaseFragment<FragmentBookingBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentBookingBinding.inflate(inflater, container, false)

}