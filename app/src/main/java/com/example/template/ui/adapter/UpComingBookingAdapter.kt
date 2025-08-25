import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.template.R
import com.example.template.databinding.RcvItemBookingBinding
import com.example.template.ui.adapter.BookingItemPaidServAdapter
import com.example.template.utils.AppUtils

class UpComingBookingAdapter(
    private val context: Context?,
    private val listner: OnItemClickListner?
) :
    RecyclerView.Adapter<UpComingBookingAdapter.ViewHolder>() {

    var resultList: List<Bookings>? = null
    var screenFrom = ""

    inner class ViewHolder(var binding: RcvItemBookingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Bookings, position: Int) {
            binding.apply {
                if(data.vendor_name.isNullOrEmpty()){
                    tvUserName.text = "Mr Rk"
                }else{
                    tvUserName.text = data.vendor_name
                }
                tvId.text = "Booking ID: #${data.booking_reference}"
                tvLocationName.text = data.court_name
                tvLocationPlace.text = data.venue_location
                val startTime = AppUtils.convertTo12HourFormat(data.start_time.toString())
                val endTime = AppUtils.convertTo12HourFormat(data.end_time.toString())
                tvTime.text = "$startTime - $endTime"
                tvDate.text = data.booking_date
                tvPrice.text = "₹ ${data.final_amount}"

                Glide.with(itemView.context)
                    .load(data.vendor_image)
                    .placeholder(R.drawable.profilefloter)
                    .into(ivProfile)

                if(screenFrom=="home"){
//                    llButtons.visibility = View.GONE
                }else if(screenFrom=="pending"){
//                    llButtons.visibility = View.VISIBLE
//                    tvBookingStatus.visibility = View.GONE
//                    btnDecline.visibility = View.VISIBLE
//                    btnApprove.visibility = View.VISIBLE
                }else if(screenFrom=="upcoming"){
//                    llButtons.visibility = View.GONE
//                    tvBookingStatus.visibility = View.VISIBLE
//                    btnDecline.visibility = View.GONE
//                    btnApprove.visibility = View.GONE
                }else if(screenFrom=="completed"){
//                    llButtons.visibility = View.VISIBLE
//                    tvBookingStatus.visibility = View.VISIBLE
//                    tvBookingStatus.text = "Completed"
//                    tvBookingStatus.setTextColor(context!!.resources.getColor(R.color.green))
//                    btnDecline.visibility = View.GONE
//                    btnApprove.visibility = View.GONE
                }else{
//                    llButtons.visibility = View.VISIBLE
//                    tvBookingStatus.visibility = View.VISIBLE
//                    tvBookingStatus.text = "Cancelled"
//                    tvBookingStatus.setTextColor(context!!.resources.getColor(R.color.red))
                }

//                binding.btnDecline.setOnClickListener {
//                    listner?.onItemClicked(data,position,"decline")
//                }

                binding.root.setOnClickListener {
                    listner?.onItemClicked(data,position,"")
                }


                val nestedList = data.paid_services_json  // Replace with your actual data list
                if (!nestedList.isNullOrEmpty()) {
                    val nestedAdapter = BookingItemPaidServAdapter(nestedList)
                    binding.rcvUpcomingPaidService.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
                    binding.rcvUpcomingPaidService.adapter = nestedAdapter
                }
            }
        }
    }


    override fun getItemCount(): Int {
        if (resultList == null) {
            return 0
        } else {
            return resultList?.size!!
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcvItemBookingBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resultList!![position], position)


    }

    fun setData(it: List<Bookings>?) {

        resultList = it
        notifyDataSetChanged()
    }
    interface OnItemClickListner{
        fun  onItemClicked(data: Bookings, position: Int, type: String)
    }
}
