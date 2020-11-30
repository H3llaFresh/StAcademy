package by.vlfl.task4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.vlfl.task4.R
import by.vlfl.task4.model.db.RateEntity


class CustomAdapter(
    private val dataSet: MutableList<RateEntity> = mutableListOf(),
    private val viewHolderClickListener: ViewHolderClickListener
) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.name)
        val rateView: TextView = view.findViewById(R.id.rate)

        fun bind(item: RateEntity) {
            nameView.text = item.curName
            rateView.text = "${item.curScale} ${item.curCode}"
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)

        val holder = ViewHolder(view)

        view.setOnClickListener {
            val itemPosition = holder.adapterPosition

            if (itemPosition != RecyclerView.NO_POSITION)
                viewHolderClickListener.onItemClicked(dataSet[itemPosition])
        }
        return holder
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size


    fun replaceAll(items: List<RateEntity>) {
        dataSet.clear()
        dataSet.addAll(items)
        notifyDataSetChanged()
    }

}