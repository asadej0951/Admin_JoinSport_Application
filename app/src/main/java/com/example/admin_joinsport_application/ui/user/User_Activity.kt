package com.example.admin_joinsport_application.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin_joinsport_application.R
import com.example.admin_joinsport_application.adapter.AdapterOPT
import com.example.admin_joinsport_application.adapter.AdapterUser
import com.example.admin_joinsport_application.model.ResponseOPT
import com.example.admin_joinsport_application.model.ResponseUser
import com.example.admin_joinsport_application.presenter.PresenterPost
import kotlinx.android.synthetic.main.activity_user_.*

class User_Activity : AppCompatActivity() {

    var mPresenterPost = PresenterPost()
    var mResponseUser = ArrayList<ResponseUser>()
    var mResponseOPT = ArrayList<ResponseOPT>()
    lateinit var mAdapterUser : AdapterUser
    lateinit var mAdapterOPT : AdapterOPT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_)
        setAPIUser()
    }

    private fun setAPIUser() {
        mPresenterPost.ShowOPTPresenterRX(this::OPTNext,this::OPTError)
        mPresenterPost.ShowUserPresenterRX(this::UserNext,this::UserError)
    }

    private fun UserNext(responseUser: List<ResponseUser>) {
        for (i in responseUser.indices){
            mResponseUser.add(responseUser[i])
        }
        mAdapterUser= AdapterUser(this,mResponseUser)
        recycler_User.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapterUser
            mAdapterUser.notifyDataSetChanged()
        }
    }

    private fun UserError(message: String) {
    }

    private fun OPTNext(responseOPT: List<ResponseOPT>) {
        for (i in responseOPT.indices){
            mResponseOPT.add(responseOPT[i])
        }
        mAdapterOPT = AdapterOPT(this,mResponseOPT)
        recycler_OPT.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapterOPT
            mAdapterOPT.notifyDataSetChanged()
        }
    }

    private fun OPTError(message:String) {
    }
}

