/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.ui.base


import androidx.recyclerview.widget.RecyclerView
import shafoot.h.myevents.utils.OnItemClickListener

abstract class BaseAdapter<M, VH : RecyclerView.ViewHolder>constructor( var items: MutableList<M> = mutableListOf()):
    RecyclerView.Adapter<VH>() {



    var itemClickListener: OnItemClickListener<M>? = null

    protected fun initOnItemClickListener(holder: VH, position: Int) {
        if (itemClickListener != null) {
            holder.itemView.setOnClickListener {
                itemClickListener?.onItemClicked(it, get(position), position)
            }
        }
    }

    open fun add(item: M) {
        items.add(item)
        notifyItemInserted(items.size)
    }

    open fun add(items: MutableList<M>) {
        if ((this.items.size) == 0) {
            submitItems(items)
            return
        }

        val oldSize = this.items.size
        this.items.addAll(items)
        notifyItemRangeInserted(oldSize, items.size)
    }

    fun submitItems(items: MutableList<M>) {
        this.items = items
        notifyDataSetChanged()
    }


    fun remove(item: M) {
        val position = items.indexOf(item)
        if (position != -1) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<M>) {
        this.itemClickListener = onItemClickListener
    }

    override fun getItemCount() = items.size

    fun get(position: Int): M? {
        return items[position]
    }

}
