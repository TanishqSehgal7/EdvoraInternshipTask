package com.example.edvorainternshiptask.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edvorainternshiptask.R
import com.example.edvorainternshiptask.adapters.NearestRidesRecyclerViewAdapter
import com.example.edvorainternshiptask.databinding.FragmentNearestRidesBinding
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList
import org.json.JSONException





class NearestRidesFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentNearestRidesBinding

    // array lists for different data from JSON
    private var idList = ArrayList<String>()
    private var originStationCodeList=ArrayList<String>()
    private var stationPathArray=ArrayList<Int>()
    private var stationPathString=ArrayList<String>()
    private var dateList=ArrayList<String>()
    private var mapUrlList=ArrayList<String>()
    private var stateList=ArrayList<String>()
    private var cityList=ArrayList<String>()
    private var distanceList=ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNearestRidesBinding.inflate(inflater,container,false)
        val view:View = binding.root
        recyclerView=view.findViewById(R.id.rvNearestRides)
        recyclerView.layoutManager= LinearLayoutManager(context)

        // getting JSON in try and catch
        try {
            val jsonObj = JSONObject(loadJSONFromAssetFolder())
            val jsonArray=jsonObj.getJSONArray("Ride")
            for (i in 0 until jsonArray.length()) {
                val jsonObject: JSONObject =jsonArray.getJSONObject(i)

                // logic for distance |getNearestRides()-station_code|
                val user: JSONObject =jsonObj.getJSONObject("user")
                val userStationCode:Int=user.getInt("station_code")

                // station path array used for calculating distance
                val getJsonArray = jsonObject.optJSONArray("station_path")
                stationPathString.add(jsonObject.getString("station_path"))
                for (i in 0 until getJsonArray.length()) {
                    stationPathArray.add(getJsonArray.optInt(i))
                }

                var arrayOfStringValues=ArrayList<String>()
//                arrayOfStringValues= stationPathArray.get(i).split("[],",""," ") as ArrayList<String> (ignore)

                for (i in 0 until arrayOfStringValues.size) {
                    Log.d("String Array Values","Values"+arrayOfStringValues[i])
                }

                val distance = (getNearestRides(stationPathArray, userStationCode) - userStationCode)
                jsonObject.put("distance",distance)
                Log.d("Nearest Ride", "Distance is:" + distance)

                idList.add(jsonObject.getString("id"))
                originStationCodeList.add(jsonObject.getString("origin_station_code"))
                dateList.add(jsonObject.getString("date"))
//                mapUrlList.add(jsonObj.getString("map_url"))
                stateList.add(jsonObject.getString("state"))
                cityList.add(jsonObject.getString("city"))
                distanceList.add(jsonObject.getInt("distance"))

            }
            // make a list of nearest rides and sort it on the basis of distance
            // using collections.sort()
        } catch (ex: IOException) {
            ex.printStackTrace()
            return view
        }
        recyclerView.adapter= NearestRidesRecyclerViewAdapter(idList,originStationCodeList,stationPathArray,stationPathString,dateList,mapUrlList,stateList,cityList,distanceList)
        return view
    }

//    fun getNearestRides(stationPath: ArrayList<Int>, origin:Int) : Int {
//        val treeSet= TreeSet<Int>()
//
//        for(i in stationPath) {
//            treeSet.add(i)
//        }
//        var closest=0;
//
//        var i=0
//        while(i < stationPath.size) {
//            if (stationPath[i]==origin) {
//                closest=origin
//                return closest;
//            }
//            if(stationPath[i]<origin) {
//                continue
//            } else if (stationPath[i]!=origin && stationPath[i]>origin) {
//                closest = treeSet.higher(origin)
//                return closest
//            }
//            i++
//        }
//        return closest;
//    }

    fun getNearestRides(stationPath: ArrayList<Int>, origin: Int): Int {
        // Insert all array elements into a TreeSet
        val ts = TreeSet<Int>()
        for (i in stationPath.indices) {
            ts.add(stationPath[i])
        }

        var closest = 0
        var i = 0
        while (i < stationPath.size) {
            if (stationPath[i] == origin) {
                closest = origin
                return closest
            }
            if (stationPath[i] != origin && stationPath[i] < origin) {
                i++
            } else if (stationPath[i] != origin && stationPath[i] > origin) {
                closest = ts.higher(origin) // gives the closest element to origin
                return closest
            }
            i++
        }
        return closest
    }

    fun loadJSONFromAssetFolder(): String? {
        val json: String?
        try {
            val inputStream = context?.assets?.open("rides.json")
            val size = inputStream?.available()
            val buffer = size?.let { ByteArray(it) }
            val charset: Charset = Charsets.UTF_8
            inputStream?.read(buffer)
            inputStream?.close()
            json = buffer?.let { String(it, charset) }
        }
        catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }
}

class MyJSONComparator : Comparator<JSONObject> {

    override fun compare(obj1: JSONObject?, obj2: JSONObject?): Int {
        return value1.compareTo(value2)
    }

    companion object {
        val obj1:JSONObject = JSONObject()
        val obj2:JSONObject = JSONObject()
        var value1:String = obj1?.get("distance") as String
        val value2:String = obj2?.get("distance") as String
    }
}
