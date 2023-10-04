package com.example.roomtest.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable
import kotlin.properties.Delegates

abstract class AbstractAdapter : RecyclerView.Adapter<AbstractAdapter.ViewHolder<*>>() {

    open var dataList: List<Serializable> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    protected var onClickListener: (item: Serializable) -> Unit = {}
    var deleteClickListener: (item: Serializable) -> Unit = {}

    fun setOnItemClickListener(onClickListener: (item: Serializable) -> Unit) {
        this.onClickListener = onClickListener
    }

    fun setOnDeleteClickListener(onClickListener: (item: Serializable) -> Unit) {
        this.deleteClickListener = onClickListener
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ViewHolder<*>, position: Int) {
        dataList[position].let {
            holder.bindView(dataList[position], position)
            holder.itemView.setOnClickListener {
                onClickListener.invoke(dataList[position])
            }
        }
    }

    abstract class ViewHolder<T> internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bindView(item: Serializable, position: Int)
        open fun bindView() {}
    }
}