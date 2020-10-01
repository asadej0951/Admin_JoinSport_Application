package com.example.admin_joinsport_application.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.admin_joinsport_application.R
import com.example.admin_joinsport_application.model.ResponseComment
import com.example.admin_joinsport_application.model.ResponseOPT
import com.example.admin_joinsport_application.model.ResponseUser
import com.example.admin_joinsport_application.presenter.PresenterPost
import com.example.admin_joinsport_application.ui.post.Post_Activity
import com.example.admin_joinsport_application.ui.user.User_Activity
import com.example.join_sport_app.rest.Utils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_message_yes_no.view.*

class AdapterOPT (var ct : Context, private var Data:ArrayList<ResponseOPT>): RecyclerView.Adapter<ViewHolderUser>(){
    private var mPresenterPost = PresenterPost()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUser {
        return ViewHolderUser(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user,parent,false
            )
        )
    }

    override fun getItemCount() = Data.size

    override fun onBindViewHolder(holder: ViewHolderUser, position: Int) {
        holder.name.text = "คุณ "+Data[position].o_name+" "+Data[position].o_lname
        Picasso.get().load(Utils.host+"/imageregister/"+Data[position].img).into(holder.image)
        holder.itemView.setOnLongClickListener {
            val builderSingle = AlertDialog.Builder(ct)
            val animals = arrayOf("ลบรายใช้ผู้ใช้")
            builderSingle.setItems(animals){
                    _,which ->
                when(which){
                    0->{
                        DialogMessage(Data[position].o_id)
                    }
                }
            }
            builderSingle.show()
            true
        }
    }
    private fun DialogMessage(ID:Int){
        val View = LayoutInflater.from(ct).inflate(R.layout.dialog_message_yes_no,null)
        View.message_Dialog.setText("การลบรายชื่อผู้ใช้ไม่สามารถกู้ตืนได้\nคุณต้องการลบรายชื่อผู้ใช้ใช่หรือไม่?")
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
                    setAPIDelete(ID)
                }
            }
            time.start()
        }
        View.btn_dialog_cancel.setOnClickListener {
            DialogButton.dismiss()
        }
    }

    private fun setAPIDelete(id: Int) {
        mPresenterPost.deleteOPTPresenterRX(id,this::deleteNext,this::deleteError)

    }

    private fun deleteNext(responseUser: ResponseOPT) {
        Toast.makeText(ct, "ลบเสร็จสิน", Toast.LENGTH_SHORT).show()
        ct.startActivity(
            Intent(ct, User_Activity::class.java)
        )
        (ct as Activity).finish()

    }

    private fun deleteError(message: String) {
    }

}