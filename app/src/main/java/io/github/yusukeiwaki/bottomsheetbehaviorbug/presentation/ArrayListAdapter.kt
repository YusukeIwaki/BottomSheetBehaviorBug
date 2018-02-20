package io.github.yusukeiwaki.bottomsheetbehaviorbug.presentation

import android.support.v7.widget.RecyclerView

abstract class ArrayListAdapter<Model, ViewHolder : ArrayListItemViewHolder<Model>> : RecyclerView.Adapter<ViewHolder>() {

    private val data: MutableList<Model> = ArrayList<Model>()
    private var itemClickListener: ((Model) -> Unit)? = null

    override fun getItemCount() = data.size

    fun getItemAt(position: Int) = data[position]

    fun updateData(newData: List<Model>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    fun setItemClickListener(itemClickListener: ((Model) -> Unit)?) {
        this.itemClickListener = itemClickListener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItemAt(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(item)
        }
    }
}
