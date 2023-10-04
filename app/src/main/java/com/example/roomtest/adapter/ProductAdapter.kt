package com.example.roomtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.roomtest.R
import com.example.roomtest.data.database.entities.ProductEntity
import com.example.roomtest.databinding.ItemProductBinding
import java.io.Serializable

class ProductAdapter : AbstractAdapter() {

    private lateinit var binding: ItemProductBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AbstractAdapter.ViewHolder<*>, position: Int) {
        dataList[position].let {
            holder.bindView(dataList[position], position)
            holder.itemView.setOnClickListener {
                onClickListener.invoke(dataList[position])
            }
            binding.sivDelete.setOnClickListener {
                deleteClickListener.invoke(dataList[position])
            }
        }
    }

    inner class ViewHolder(private val itemBinding: ItemProductBinding) :
        AbstractAdapter.ViewHolder<View>(itemBinding.root) {

        override fun bindView(item: Serializable, position: Int) {
            if (item is ProductEntity) {
                val context = itemView.context
                with(itemBinding) {
                    tvDescription.text =
                        context.getString(R.string.str_description_format, item.description)
                    tvPrice.text = context.getString(R.string.str_price_format, item.price)
                    tvSku.text = context.getString(R.string.str_sku_format, item.sku)
                    item.image?.let {
                        Glide.with(context)
                            .load(it)
                            .into(sivPhoto) // ivProductImage es el ImageView en tu diseño de elemento de producto
                    }
                }
            }
        }
    }
}