package com.adevinta.leku.geocoder.adapters.base

import androidx.recyclerview.widget.RecyclerView
import com.adevinta.leku.lekuViewHolder

abstract class lekuSearchAdapter<T : lekuViewHolder, I> : RecyclerView.Adapter<T>() {
    var items: List<I> = emptyList()
    var onClick: (position: Int) -> Unit = {}

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.itemView.setOnClickListener { onClick(position) }
    }
}
