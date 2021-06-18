package principal.app.ui.helpers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import principal.app.R
import principal.app.repositories.responses.dto.Tour

class RecyclerAdapter(
    val tours: List<Tour>
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemPrice: TextView
        var itemComment: TextView

        init {
            itemImage = itemView.findViewById(R.id.imageView)
            itemTitle = itemView.findViewById(R.id.avatarName)
            itemPrice = itemView.findViewById(R.id.price_text)
            itemComment = itemView.findViewById(R.id.tour_comments)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tour = tours[position]
        val price = tour.price

        Picasso.get().load(tour.images[0].url).into(holder.itemImage)
        holder.itemTitle.text = tour.title
        holder.itemPrice.text = "Desde $price $"
        holder.itemComment.text = tour.comments_count.toString()
    }

    override fun getItemCount(): Int {
        return tours.size
    }
}