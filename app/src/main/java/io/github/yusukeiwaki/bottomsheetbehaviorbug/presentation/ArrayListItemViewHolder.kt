package io.github.yusukeiwaki.bottomsheetbehaviorbug.presentation

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class ArrayListItemViewHolder<Model>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(model: Model)
}
