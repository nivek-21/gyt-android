package principal.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private val images = intArrayOf(
        R.drawable.test,
        R.drawable.test1,
        R.drawable.test2,
    )

    private val titles = arrayOf(
        "Berlín: entrada al museo de Pérgamo",
        "Ámsterdam: crucero por el canal al atardecer",
        "Barcelona: tour en velero con vermú desde Port Vell",
    )

    private val comments = arrayOf(
        "0 comentarios",
        "10 comentarios",
        "2 comentarios"
    )

    private val prices = arrayOf(
        22.56,
        20.42,
        14.25
    )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemPrice: TextView
        var itemTitle: TextView
        var itemComment: TextView

        init {
            itemImage = itemView.findViewById(R.id.imageView)
            itemTitle = itemView.findViewById(R.id.avatarName)
            itemPrice = itemView.findViewById(R.id.price_text)
            itemComment = itemView.findViewById(R.id.tour_comments)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemImage.setImageResource(images[position])
        holder.itemTitle.text = titles[position]

        val price = prices[position].toString()
        holder.itemPrice.text = "Desde $price $"

        holder.itemComment.text = comments[position]
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}