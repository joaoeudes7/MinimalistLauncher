package com.jedev.minimalistlauncher.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

open class AdapterGeneric<T>(
    private val resourceLayout: Int,
    private val onBindView: (view: View, item: T) -> Unit,
    private val items: MutableList<T> = mutableListOf(),
    private val limitItems: Int = items.size
): RecyclerView.Adapter<AdapterGeneric.HolderView<T>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderView<T> {
        val context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val itemView = layoutInflater.inflate(resourceLayout, parent, false)

        return HolderView(itemView, onBindView)
    }

    override fun getItemCount(): Int {
        return limitItems
    }

    override fun onBindViewHolder(holder: HolderView<T>, position: Int) {
        val item = items[position]

        holder.bind(item)
    }

    // This will force all models to be unbound and their views recycled once the RecyclerView is no longer in use. We need this so resources
    // are properly released, listeners are detached, and views can be returned to view pools (if applicable)
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        if (recyclerView.layoutManager is LinearLayoutManager) {
            (recyclerView.layoutManager as LinearLayoutManager).recycleChildrenOnDetach = true
        }
    }

    fun setItems(newItems: List<T>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class HolderView<T>(itemView: View, private val onBindView: (view: View, item: T) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: T) {
            onBindView.invoke(itemView, item)
        }
    }
}
