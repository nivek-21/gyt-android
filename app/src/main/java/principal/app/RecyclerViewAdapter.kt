package principal.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val itemTitles = arrayOf("text1" , "text2", "text3", "text4")
    private val itemDetails = arrayOf("text1Des" , "text2Des", "text3Des", "text3Des")

    private val itemImages = intArrayOf(

        R.drawable.imagen1,
        R.drawable.imagen2,
        R.drawable.imagen3,
        R.drawable.imagen4
    )




    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /*   aqui desplegamos un card view con 2 elementos un image y text view  , creamos dos variable y las inicialimos con las dos referencias del cardview.*/


        var image : ImageView
        var textTitle : TextView
        var textDes : TextView


        init{

            image = itemView.findViewById(R.id.item_image)
            textTitle = itemView.findViewById(R.id.item_title)
            textDes = itemView.findViewById(R.id.item_details)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { /* poder consumir y mostrar data */
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_model, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { /* es el que comienza a ligar cada item o el item que va tener el recylerview , el recycler view v tener un grupo de item de tipo
                                                                                    card view*/
        holder.textTitle.text = itemTitles [position]
        holder.textDes.text = itemDetails [position]
        holder.image.setImageResource(itemImages[position])

        holder.itemView.setOnClickListener { v: View ->

            Toast.makeText(v.context, "Clicked on the item", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int { /* necesita saber cuantos items va tener la lista */
        return itemTitles.size
    }
}