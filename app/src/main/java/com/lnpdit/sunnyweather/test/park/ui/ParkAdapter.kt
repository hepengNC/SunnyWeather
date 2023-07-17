package com.lnpdit.sunnyweather.test.park.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lnpdit.sunnyweather.databinding.ParkItemBinding
import com.lnpdit.sunnyweather.test.park.model.ParkResponse

/**
 * Created by HePeng on 2023/7/13
 */
class ParkAdapter(val parkList: List<ParkResponse.Park>) :
    RecyclerView.Adapter<ParkAdapter.ViewHolder>() {
    inner class ViewHolder(binding: ParkItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val parkName: TextView = binding.tvParkName
        val parkAddress: TextView = binding.tvParkAddress
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ParkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = parkList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = parkList[position]
        holder.parkName.text = item.name
        holder.parkAddress.text = "${item.province}${item.city}${item.area}${item.street}"
    }
}