package com.ag04.githubapp.components.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by akovar on 10/06/2020.
 */
abstract class BaseRecyclerViewAdapter<T, VH : BaseRecyclerViewAdapter<T, VH>.BaseVH> :
    RecyclerView.Adapter<VH>() {

    var items: List<T>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

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

    fun getItemAt(position: Int): T? {
        items?.let {
            if (it.size > position) {
                return it[position]
            }
        }

        return null
    }

    abstract inner class BaseVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun doBind(position: Int) {
            val item = getItemAt(position)
            item?.let {
                performBind(it)
            }
        }

        fun doBind(position: Int, payloads: MutableList<Any>) {

        }

        abstract fun performBind(item: T)
    }

    interface OnItemClickListener<T> {

        fun onClick(item: T)
    }
}

