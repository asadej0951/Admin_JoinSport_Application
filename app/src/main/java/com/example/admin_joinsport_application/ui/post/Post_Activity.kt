package com.example.admin_joinsport_application.ui.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin_joinsport_application.R
import com.example.admin_joinsport_application.adapter.AdapterPost
import com.example.admin_joinsport_application.model.ResponsePost
import com.example.admin_joinsport_application.presenter.PresenterPost
import com.example.join_sport_app.rest.Utils
import com.example.myapplicationproject.rest.local.Preferrences
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_post_.*
import java.util.*
import kotlin.collections.ArrayList

class Post_Activity : AppCompatActivity() {
    var mPresenterPost = PresenterPost()
    var mResponsePost = ArrayList<ResponsePost>()
    lateinit var mAdapterPost : AdapterPost
    var mPreferrences = Preferrences(this)
    var Image =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_)
        setAPIShowPost()
        Image = "admin.png"
        Picasso.get().load(Utils.host+"/imageregister/"+Image).into(imageUser)
        btn_Post.setOnClickListener {
            var message = ed_Post.text.toString()
            setAPIPost(message)
        }

    }
    private fun setAPIShowPost() {
        mPresenterPost.ShowPostPresenterRX(this::ShowPostNext,this::ShowPostError)
    }

    private fun ShowPostError(message: String) {
    }

    private fun ShowPostNext(responsePost: List<ResponsePost>) {
        for (i in responsePost.indices){
            mResponsePost.add(responsePost[i])
        }
        mAdapterPost = AdapterPost(this,mResponsePost)
        recycler_Post.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapterPost
            mAdapterPost.notifyDataSetChanged()
        }
    }
    private fun setAPIPost(message: String) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val min = calendar.get(Calendar.MINUTE)
        var DD =""
        var Mon = ""
        var HH = ""
        var MM = ""
        if (day.toString().length ==1){DD = "0${day}"}else{DD = "${day}"}
        if (month.toString().length ==1){Mon = "0${month}"}else{Mon = "${month}"}
        if (hour.toString().length==1){HH ="0${hour}"}else{HH ="${hour}"}
        if (min.toString().length==1){MM ="0${min}"}else{MM ="${min}"}
        val p_time = "${DD}/${Mon}/${year} - ${HH}:${MM}"
        mPresenterPost.PostPresenterRX("admin","Admin",Image,message,p_time,"admin",this::PostNext,this::PostError)
    }
    private fun PostError(message: String) {
    }

    private fun PostNext(responseOperator : ResponsePost) {
        ed_Post.text.clear()
        mResponsePost.clear()
        mAdapterPost.notifyDataSetChanged()
        setAPIShowPost()
    }
}