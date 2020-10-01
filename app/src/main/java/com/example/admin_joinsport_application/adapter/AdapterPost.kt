package com.example.admin_joinsport_application.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.admin_joinsport_application.R
import com.example.admin_joinsport_application.model.ResponseComment
import com.example.admin_joinsport_application.model.ResponsePost
import com.example.admin_joinsport_application.presenter.PresenterPost
import com.example.admin_joinsport_application.ui.post.CommentActivity
import com.example.admin_joinsport_application.ui.post.Post_Activity
import com.example.join_sport_app.rest.Utils
import com.example.myapplicationproject.rest.local.Preferrences
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_message_yes_no.view.*

class AdapterPost (val ct :Context,private var mDataPost: ArrayList<ResponsePost>):RecyclerView.Adapter<ViewHolderPost>(){

   private var mPresenterPost = PresenterPost()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPost {
       return ViewHolderPost(
           LayoutInflater.from(parent.context).inflate(
               R.layout.item_post,parent,false
           )
       )
    }
    override fun getItemCount() = mDataPost.size

    override fun onBindViewHolder(holder: ViewHolderPost, position: Int) {
        if (mDataPost[position].p_message.length <50){
            holder.messageitem.text=mDataPost.get(position).p_message
        }
        else{
            holder.messageitem.text=mDataPost.get(position).p_message.substring(0,50)+"..."
        }
        Picasso.get().load(Utils.host+"/imageregister/"+mDataPost[position].u_img).into(holder.u_img)
        holder.usernamePost.text = mDataPost.get(position).username
        holder.timePost.text = mDataPost.get(position).p_time
        holder.itemView.setOnClickListener {
            val i = Intent(ct, CommentActivity::class.java)
            i.putExtra("ID", mDataPost[position].p_id)
            i.putExtra("u_id", mDataPost[position].user_id)
            i.putExtra("u_name", mDataPost[position].username)
            i.putExtra("u_img", mDataPost[position].u_img)
            i.putExtra("p_message", mDataPost[position].p_message)
            i.putExtra("p_time", mDataPost[position].p_time)
            ct.startActivity(i)

        }
        holder.itemView.setOnLongClickListener {
            val builderSingle = AlertDialog.Builder(ct)
            val animals = arrayOf("ลบข้อความโพสต์")
            builderSingle.setItems(animals){
                    _,which ->
                when(which){
                    0->{
                        DialogMessage(mDataPost.get(position).p_id)
                    }
                }
            }
            builderSingle.show()
            true
        }
    }
    private fun DialogMessage(ID:Int){
        val View = LayoutInflater.from(ct).inflate(R.layout.dialog_message_yes_no,null)
        View.message_Dialog.setText("การลบข้อความโพสต์ไม่สามารถกู้ตืนได้\nคุณต้องการลบข้อความโพสต์ใช่หรือไม่?")
        View.btn_dialog_OK.setText("แน่ใจ")
        View.btn_dialog_cancel.setText("ยกเลิก")
        val Dialog = AlertDialog.Builder(ct).apply {
            setTitle("คำเตือน!!")
            setIcon(R.drawable.alert)
            setView(View)
        }
        val DialogButton = Dialog.show()
        View.btn_dialog_OK.setOnClickListener {
            DialogButton.dismiss()
            Toast.makeText(ct, "กำลังลบ...", Toast.LENGTH_SHORT).show()
            val time = object : CountDownTimer(2000,1000){
                override fun onTick(millisUntilFinished: Long) {
                }

                override fun onFinish() {
                    setAPIDeletePost(ID)
                    setAPIDeleteComment(ID)
                }
            }
            time.start()

        }
        View.btn_dialog_cancel.setOnClickListener {
            DialogButton.dismiss()
        }
    }

    private fun setAPIDeleteComment(id: Int) {
        mPresenterPost.deleteCommentPresenterRX(id,this::deleteCommentNext,this::deleteCommentError)
    }

    private fun deleteCommentNext(responsePost: ResponseComment) {
        Toast.makeText(ct, "ลบเสร็จสิ้น", Toast.LENGTH_SHORT).show()
    }

    private fun deleteCommentError(message: String) {
    }

    private fun setAPIDeletePost(id: Int) {
        mPresenterPost.deletePostPresenterRX(id,this::deletePostNext,this::deletePostError)
    }

    private fun deletePostNext(responsePost: ResponsePost) {
        ct.startActivity(
            Intent(ct,Post_Activity::class.java)
        )
        (ct as Activity).finish()
    }

    private fun deletePostError(message:String) {
        Toast.makeText(ct, "ลบไม่สำเร็จ", Toast.LENGTH_SHORT).show()
    }
}


class ViewHolderPost(item:View):RecyclerView.ViewHolder(item){
    val u_img :ImageView = item.findViewById(R.id.img_userItem_post)
    val usernamePost :TextView = item.findViewById(R.id.tv_username_PostItem)
    val messageitem :TextView = item.findViewById(R.id.tv_messageItem)
    val timePost :TextView = item.findViewById(R.id.timeItem_Post)
}