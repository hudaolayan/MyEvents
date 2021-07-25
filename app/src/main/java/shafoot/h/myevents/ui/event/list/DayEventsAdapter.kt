/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.ui.event.list


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import shafoot.h.myevents.R
import shafoot.h.myevents.databinding.RowDayEventsBinding
import shafoot.h.myevents.models.DayEvents
import shafoot.h.myevents.models.Event
import shafoot.h.myevents.ui.base.BaseAdapter
import shafoot.h.myevents.utils.toStringDate
import javax.inject.Inject

class DayEventsAdapter @Inject constructor() :
    BaseAdapter<DayEvents, DayEventsAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    private var onEventClickListener: OnEventClickListener? = null

    class ViewHolder(val view: RowDayEventsBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = DataBindingUtil.inflate<RowDayEventsBinding>(
            inflater,
            R.layout.row_day_events, parent, false
        )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.dateTxt.text = items[position].dateMilliseconds?.toStringDate()
        val eventsAdapter = EventsAdapter(items[position].events as MutableList<Event?>)
        eventsAdapter.setOnItemClickListener { view, item, _ ->
            onEventClickListener?.onItemClicked(
                view,
                item,
                position
            )
        }
        holder.view.dayEventsRv.apply {
            setRecycledViewPool(viewPool)
            this.adapter = eventsAdapter
        }
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClicked(it, items[position], position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setOnEventClickListener(onDayEventClickListener: OnEventClickListener) {
        this.onEventClickListener = onDayEventClickListener
    }
}

fun interface OnEventClickListener {
    fun onItemClicked(view: View?, item: Event?, position: Int)
}