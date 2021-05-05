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
        "Ámsterdam: crucero por el canal al atardecer crucero por el canal al atardecer crucero por el canal al atardecer",
        "Barcelona: tour en velero con vermú desde Port Vell",
    )

    private val descriptions = arrayOf(
        "Visite el Museo de Pérgamo con numerosas colecciones impresionantes. Vea piezas de la antigüedad, descubra el Museo de Oriente Medio y el Museo de Arte Islámico.",
        "Sube a bordo de un exclusivo crucero de 90 min con audioguía disponible en múltiples idiomas. Goza de vistas nocturnas de los edificios y puentes de la ciudad iluminados.",
        "Disfruta de las vistas de Barcelona desde el mar en un tour en barco de 1,5 h. Sube a bordo de un yate de 12 metros y toma un vermú helado mientras admiras el monumento a Colón, la Barceloneta, el Maremagnum y más. Relájate con tus amigos y tu familia.",
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
        var itemDescription: TextView

        init {
            itemImage = itemView.findViewById(R.id.imageView)
            itemTitle = itemView.findViewById(R.id.avatarName)
            itemPrice = itemView.findViewById(R.id.price_text)
            itemDescription = itemView.findViewById(R.id.price_text)
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
        holder.itemPrice.text = prices[position].toString()
        holder.itemDescription.text = descriptions[position]
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}