package com.candra.starterprojectaplikasi.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.candra.starterprojectaplikasi.R
import com.candra.starterprojectaplikasi.core.data.source.local.entity.TourismEntity
import com.candra.starterprojectaplikasi.core.domain.model.Tourism
import com.candra.starterprojectaplikasi.databinding.ItemListTourismBinding

class TourismAdapter : RecyclerView.Adapter<TourismAdapter.ListViewHolder>() {

    private var listData = ArrayList<Tourism>()
    var onItemClick: ((Tourism) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Tourism>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_tourism, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListTourismBinding.bind(itemView)
        fun bind(data: Tourism) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(ivItemImage)
                tvItemTitle.text = data.name
                tvItemSubtitle.text = data.address
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}