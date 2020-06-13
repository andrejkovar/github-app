package com.akovar.githubapp.components.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by akovar on 10/06/2020.
 */
abstract class BaseRecyclerViewAdapter<T, VH : BaseRecyclerViewAdapter<T, VH>.BaseVH> :
    RecyclerView.Adapter<VH>() {

    /**
     * Adapter items holder.
     */
    var items: List<T>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    /**
     * Item click listener holder.
     */
    var onItemClickListener: OnItemClickListener<T>? = null

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.doBind(position)
    }

    override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        holder.doBind(position, payloads)
    }

    /**
     * Gets item on provided position.
     *
     * @param position position
     * @return item or null if not found
     */
    fun getItemAt(position: Int): T? {
        items?.let {
            if (it.size > position) {
                return it[position]
            }
        }

        return null
    }

    abstract inner class BaseVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * Binds item for provided position.
         *
         * @param position position
         */
        fun doBind(position: Int) {
            val item = getItemAt(position)
            item?.let {
                performBind(it)
            }
        }

        fun doBind(position: Int, payloads: MutableList<Any>) {

        }

        /**
         * Performs binding for provided item.
         *
         * @param item item
         */
        abstract fun performBind(item: T)
    }

    interface OnItemClickListener<T> {

        /**
         * Called when item has been clicked.
         *
         * @param item clicked item
         */
        fun onItemClick(item: T)
    }
}

