package com.lnpdit.sunnyweather.ui.place

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lnpdit.sunnyweather.databinding.PlaceItemBinding
import com.lnpdit.sunnyweather.logic.model.PlaceResponse

/**
 * Created by HePeng on 2023/7/11
 */
class PlaceAdapter(val placeList: List<PlaceResponse.Place>) :
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {
    inner class ViewHolder(binding: PlaceItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val placeName: TextView = binding.tvPlaceName
        val placeAddress: TextView = binding.tvPlaceAddress
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PlaceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = placeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = placeList[position]
        holder.placeName.text = item.name
        holder.placeAddress.text = item.formatted_address
    }

}