package com.example.admin_joinsport_application.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin_joinsport_application.R
import com.example.admin_joinsport_application.model.ResponseActivity
import com.example.admin_joinsport_application.presenter.PresenterPost
import com.example.myapplicationproject.adapterall.AdapterActivity
import kotlinx.android.synthetic.main.activity_show_.*

class Show_Activity : AppCompatActivity() {
    var mPresenterActivity = PresenterPost()
    var mResponseActivity = ArrayList<ResponseActivity>()
    lateinit var mAdapterActivity : AdapterActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_)
        setAPIActivity()
    }
    private fun setAPIActivity() {
        mPresenterActivity.ShowActivityPresenterRX(this::onShowActivitySuccess,this::onShowActivityError)
    }

    private fun onShowActivityError(message :String) {
    }

    private fun onShowActivitySuccess(responseActivity: List<ResponseActivity>) {
        for (i in responseActivity.indices) {
            mResponseActivity.add(responseActivity[i])
        }
        mAdapterActivity = AdapterActivity(this,mResponseActivity)
        recycler_activity.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapterActivity
            mAdapterActivity.notifyDataSetChanged()
        }
    }
}