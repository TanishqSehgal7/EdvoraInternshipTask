package com.example.edvorainternshiptask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.example.edvorainternshiptask.adapters.ViewPagerAdapter
import com.example.edvorainternshiptask.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    val tabsHeadingArray = arrayOf("Nearest", "Upcoming", "Past")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // viewbinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setting custom action bar
        supportActionBar?.apply {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            setDisplayShowCustomEnabled(true)
            setCustomView(R.layout.actionbar_layout)
            // here we cast supportActionBar as a Toolbar to add custom properties
            val toolbar: androidx.appcompat.widget.Toolbar = customView.parent as androidx.appcompat.widget.Toolbar
            toolbar.setContentInsetsAbsolute(0,0)
            toolbar.setPadding(0,0,0,0)

            // get user profile name from JSON in assets folder (rides.json)
            try {
                val jsonObj = JSONObject(loadJSONFromAssetFolder())
                val user:JSONObject=jsonObj.getJSONObject("user")
                val userProfileName:String=user.getString("name")

                customView.findViewById<TextView>(R.id.profile_name).text=userProfileName
            } catch (ex:IOException){
                ex.printStackTrace()
                return Unit
            }
            // get Custom Profile Name here after getting data from JSON or make another apply block where data is fetched in this file
        }

        // binding viewpager implementation
        val adapterOfViewPager = ViewPagerAdapter(supportFragmentManager,lifecycle)
        binding.viewPager.adapter = adapterOfViewPager

        // adding number of upcoming rides and past rides based on the data
//        tabsHeadingArray[1] += " (4)"
//        tabsHeadingArray[2] += " (2)"

        // attach tablayout with viewpager
        TabLayoutMediator(binding.tablayout,binding.viewPager) { tab, posOfTab ->
            tab.text = tabsHeadingArray[posOfTab]
        }.attach()
    }

    fun loadJSONFromAssetFolder(): String {
        val json: String?
        try {
            val inputStream = assets.open("rides.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        }
        catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }
}