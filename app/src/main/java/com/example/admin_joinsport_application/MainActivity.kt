package com.example.admin_joinsport_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin_joinsport_application.adapter.AdapterMenu
import com.example.admin_joinsport_application.model.DataMenu
import com.example.admin_joinsport_application.model.ResponterMenu
import com.example.myapplicationproject.rest.local.Preferrences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val data = ArrayList<ResponterMenu>()
    lateinit var mMenuAdapter: AdapterMenu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        for (i in DataMenu.datamenu.indices){
            data.add(ResponterMenu(DataMenu.datamenu[i]))
        }
        mMenuAdapter = AdapterMenu(this,data)
        RecyclerView_menu.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mMenuAdapter
            mMenuAdapter.notifyDataSetChanged()
        }
    }
}