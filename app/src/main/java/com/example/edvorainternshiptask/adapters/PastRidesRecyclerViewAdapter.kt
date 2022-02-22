package com.example.edvorainternshiptask.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.edvorainternshiptask.R
import java.util.*
import kotlin.collections.ArrayList

class PastRidesRecyclerViewAdapter : RecyclerView.Adapter<PastRidesRecyclerViewAdapter.RidesViewHolder>(){

    private var idList = ArrayList<String>()
    private var originStationCodeList=ArrayList<String>()
    private var stationPathArray=ArrayList<Array<Int>>()
    private var dateList=ArrayList<Date>()
    private var mapUrlList=ArrayList<Uri>()
    private var stateList=ArrayList<String>()
    private var city=ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RidesViewHolder {
        val viewHolder = PastRidesRecyclerViewAdapter.RidesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rides_info_card_layout, parent, false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: RidesViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return idList.size
    }

    class RidesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTv=itemView.findViewById<TextView>(R.id.RideIdTv)
        val originStationCodeTv=itemView.findViewById<TextView>(R.id.OriginStationTv)
        val stationPathArrayTv=itemView.findViewById<TextView>(R.id.station_Path_tv)
        val dateTv=itemView.findViewById<TextView>(R.id.DateTv)
        val stateTv=itemView.findViewById<TextView>(R.id.StateNameTv)
        val cityTv=itemView.findViewById<TextView>(R.id.cityNameTv)
    }
}