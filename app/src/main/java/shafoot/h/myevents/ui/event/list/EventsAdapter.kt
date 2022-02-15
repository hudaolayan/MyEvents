/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.ui.event.list


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.SwipeRevealLayout
import com.chauthai.swipereveallayout.ViewBinderHelper
import shafoot.h.myevents.R
import shafoot.h.myevents.databinding.RowEventBinding
import shafoot.h.myevents.models.Event
import shafoot.h.myevents.ui.base.BaseAdapter
import javax.inject.Inject

class EventsAdapter @Inject constructor(items: MutableList<Event?>) :
    BaseAdapter<Event?, EventsAdapter.ViewHolder>(items) {

    private val viewBinderHelper = ViewBinderHelper()

    class ViewHolder(val view: RowEventBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = DataBindingUtil.inflate<RowEventBinding>(
            inflater,
            R.layout.row_event, parent, false
        )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.event = items[position]

        viewBinderHelper.setOpenOnlyOne(true)
        viewBinderHelper.bind(

            holder.itemView.findViewById(R.id.swipe_layout) as SwipeRevealLayout,
            items[position].toString()
        )

        viewBinderHelper.closeLayout(items[position].toString())
        holder.view.editLayout.setOnClickListener {
            itemClickListener?.onItemClicked(it, items[position], position)
        }
        holder.view.deleteLayout.setOnClickListener {
            itemClickListener?.onItemClicked(it, items[position], position)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}
