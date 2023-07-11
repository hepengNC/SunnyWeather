package com.lnpdit.sunnyweather.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lnpdit.sunnyweather.databinding.FragmentPlaceBinding
import com.lnpdit.sunnyweather.logic.model.PlaceResponse

/**
 * Created by HePeng on 2023/7/11
 */
class PlaceFragment : Fragment() {
    val viewModel by lazy { ViewModelProvider(this).get(PlaceViewModel::class.java) }
    private var _binding: FragmentPlaceBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PlaceAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = PlaceAdapter(viewModel.placeList)
        binding.recyclerView.adapter = adapter
        binding.etPlace.addTextChangedListener {
            val content = it.toString()
            if (content.isNotEmpty()) {
                viewModel.searchPlaces(content)
            } else {
                binding.recyclerView.visibility = View.GONE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }
        viewModel.placeLiveData.observe(viewLifecycleOwner) { result ->
            val response = result.getOrNull()
            if (response != null) {
                binding.recyclerView.visibility = View.VISIBLE
                viewModel.placeList.addAll(((response as PlaceResponse).places))
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(requireContext(), "未查询到相关地点", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}