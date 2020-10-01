package com.example.admin_joinsport_application.ui.stadium

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin_joinsport_application.R
import com.example.admin_joinsport_application.adapter.AdapterStadiamUser
import com.example.admin_joinsport_application.model.ResponseShowStadium
import com.example.admin_joinsport_application.presenter.PresenterPost
import kotlinx.android.synthetic.main.activity_stadium_.*

class Stadium_Activity : AppCompatActivity() {

    var mPresenterPost = PresenterPost()
    var mResponseStadiam = ArrayList<ResponseShowStadium>()
    lateinit var mAdapterStadiamUser : AdapterStadiamUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stadium_)
        setAPIShowStadiamUser()
    }
    private fun setAPIShowStadiamUser() {
        mPresenterPost.ShowStadiumPresenterRX(this::ShowStadiamNext,this::ShowStadiamError)
    }

    private fun ShowStadiamError(message: String) {

    }

    private fun ShowStadiamNext(responseStadiam: List<ResponseShowStadium>) {
        for (i in responseStadiam.indices){
            mResponseStadiam.add(responseStadiam[i])
        }
        mAdapterStadiamUser= AdapterStadiamUser(this,mResponseStadiam)
        recyclerView_StadiamUser.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapterStadiamUser
            mAdapterStadiamUser.notifyDataSetChanged()
        }
    }
}