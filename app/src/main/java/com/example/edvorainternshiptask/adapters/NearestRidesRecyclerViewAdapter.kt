package com.example.edvorainternshiptask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.edvorainternshiptask.R

class NearestRidesRecyclerViewAdapter (
    var idList: ArrayList<String> = ArrayList(),
    var originStationCodeList: ArrayList<String> =ArrayList(),
    var stationPathArray: ArrayList<Int> =ArrayList(),
    var stationPathString:ArrayList<String> =ArrayList(),
    var dateList: ArrayList<String> =ArrayList(),
    var mapUrlList: ArrayList<String> =ArrayList(),
    var stateList: ArrayList<String> =ArrayList(),
    var cityList: ArrayList<String> =ArrayList(),
    var distanceList: ArrayList<Int> =ArrayList()
) : RecyclerView.Adapter<NearestRidesRecyclerViewAdapter.RidesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RidesViewHolder {
        val viewHolder = RidesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rides_info_card_layout,parent,false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: RidesViewHolder, position: Int) {
        holder.idTv.text = "Ride Id: "+ idList[position]
        holder.originStationCodeTv.text = "Origin Station: "+originStationCodeList[position]
        holder.stationPathArrayTv.text = "station_path:" + stationPathString[position]
//        var listOfDates:ArrayList<String> =ArrayList()
//        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
//        val dateOfRide = simpleDateFormat.format(dateList[position])
//        listOfDates.add(String.format("Date: %s",dateOfRide))
        holder.dateTv.text= "Date: "+dateList[position]
        holder.cityTv.text = cityList[position]
        holder.stateTv.text = stateList[position]
        holder.distanceTv.text = "Distance: "+distanceList[position].toString()
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
        val distanceTv=itemView.findViewById<TextView>(R.id.distanceTv)
    }

}