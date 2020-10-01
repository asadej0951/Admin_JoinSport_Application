package com.example.admin_joinsport_application.ui.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin_joinsport_application.R
import com.example.admin_joinsport_application.adapter.AdapterComment
import com.example.admin_joinsport_application.model.ResponseComment
import com.example.admin_joinsport_application.presenter.PresenterPost
import com.example.join_sport_app.rest.Utils
import com.example.myapplicationproject.rest.local.Preferrences
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_comment.*
import java.util.*
import kotlin.collections.ArrayList

class CommentActivity : AppCompatActivity() {
    var mPresenterPost = PresenterPost()
    var mResponseComment = ArrayList<ResponseComment>()
    lateinit var mAdapterComment : AdapterComment
    var mPreferrences = Preferrences(this)
    private var p_id :Int? =null
    private var u_id = ""
    private var u_name = ""
    private var u_img = ""
    private var p_message = ""
    private var p_time = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        p_id = intent.getIntExtra("ID",0)
        u_id = intent.getStringExtra("u_id")
        u_name = intent.getStringExtra("u_name")
        u_img = intent.getStringExtra("u_img")
        p_message = intent.getStringExtra("p_message")
        p_time = intent.getStringExtra("p_time")

        initviewBtn()
        initViewShow()
        setAPIShowComment()
    }

    private fun initViewShow() {
        Picasso.get().load(Utils.host+"/imageregister/"+u_img).into(Img_Comment)
        nameUser_Post.setText(u_name)
        time_Post.setText(p_time)
        message_Post.setText(p_message)
    }

    private fun initviewBtn() {
        btn_comment.setOnClickListener {
            val message = comment_Post.text.toString()
            mResponseComment.clear()
            setAPIComment(message)
            comment_Post.text.clear()
        }
    }

    private fun setAPIComment(message: String) {
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
        val c_time = "${DD}/${Mon}/${year} - ${HH}:${MM}"
        mPresenterPost.CommentPresenterRX("Admin",p_id.toString(),
            message,"Admin","admin.png",c_time,this::CommentNext,this::CommentError)
    }

    private fun CommentError(message: String) {
    }

    private fun CommentNext(response:ResponseComment) {
        setAPIShowComment()
    }

    private fun setAPIShowComment() {
        mPresenterPost.GetCommentPresenterRX(p_id.toString(),this::GetCommnetNext,this::GetCommentError)
    }

    private fun GetCommentError(string: String) {
    }

    private fun GetCommnetNext(responseComment: List<ResponseComment>) {
        for (i in responseComment.indices){
            mResponseComment.add(responseComment[i])
        }
        mAdapterComment = AdapterComment(this,mResponseComment)
        recyclerComment.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,true)
            adapter = mAdapterComment
            mAdapterComment.notifyDataSetChanged()
        }
    }
}