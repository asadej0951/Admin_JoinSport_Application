package com.example.admin_joinsport_application.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.admin_joinsport_application.Login_Activity
import com.example.admin_joinsport_application.R
import com.example.admin_joinsport_application.model.ResponterMenu
import com.example.admin_joinsport_application.ui.activity.Show_Activity
import com.example.admin_joinsport_application.ui.post.Post_Activity
import com.example.admin_joinsport_application.ui.stadium.Stadium_Activity
import com.example.admin_joinsport_application.ui.user.User_Activity
import com.example.myapplicationproject.rest.local.Preferrences

class AdapterMenu (var ct:Context,private var DataMenu:ArrayList<ResponterMenu>):RecyclerView.Adapter<ViewHolderMenu>(){
    lateinit var mPreferrences: Preferrences
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMenu {
        return ViewHolderMenu(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_menu,parent,false
            )
        )
    }

    override fun getItemCount() = DataMenu.size

    override fun onBindViewHolder(holder: ViewHolderMenu, position: Int) {
        holder.menu.text = DataMenu.get(position).data
        holder.itemView.setOnClickListener {
            mPreferrences = Preferrences(ct)
            if (DataMenu[position].data=="โพสต์ข้อความ")
            {
                ct.startActivity(
                    Intent(ct,
                        Post_Activity::class.java)
                )
            }
            else if (DataMenu[position].data=="จัดการข้อมูลผู้ใช้")
                {
                    ct.startActivity(
                        Intent(ct,
                            User_Activity::class.java)
                    )
                }
            else if (DataMenu[position].data=="ตรวจสอบกิจกรรม")
            {
                ct.startActivity(
                    Intent(ct,
                        Show_Activity::class.java)
                )
            }
            else if (DataMenu[position].data=="ตรวจสอบสนามกีฬา")
            {
                ct.startActivity(
                    Intent(ct,
                        Stadium_Activity::class.java)
                )
            }
            else if (DataMenu[position].data=="ออกจากระบบ")
            {
                mPreferrences.clear()
                ct.startActivity(
                    Intent(ct,Login_Activity::class.java)
                )
                (ct as Activity).finish()
            }
        }
    }

}

class ViewHolderMenu(item:View):RecyclerView.ViewHolder(item){
    var menu = item.findViewById<TextView>(R.id.menu)
}