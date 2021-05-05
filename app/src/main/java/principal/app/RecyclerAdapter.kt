package principal.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private val titles = arrayOf("text1", "text2", "text3")
    private val itemDetails = arrayOf("esto es una prueba de parrafo", "esto es una prueba de parrafo", "esto es una prueba de parrafo")
    private val images = intArrayOf(
        R.drawable.cancun,
        R.drawable.cancun,
        R.drawable.cancun,
    )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView
        var Details : TextView

        init {
            itemImage = itemView.findViewById(R.id.imageView)
            itemTitle = itemView.findViewById(R.id.avatarName)
            Details =  itemView.findViewById(R.id.item_details)
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
        holder.Details.text = itemDetails [position]
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}