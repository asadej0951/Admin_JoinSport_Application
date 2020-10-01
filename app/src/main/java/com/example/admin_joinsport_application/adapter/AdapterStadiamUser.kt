package com.example.admin_joinsport_application.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.admin_joinsport_application.R
import com.example.admin_joinsport_application.model.ResponseShowStadium
import com.example.join_sport_app.rest.Utils
import com.squareup.picasso.Picasso
import java.io.File

class AdapterStadiamUser(val ct:Context,private var mDataStadiam :ArrayList<ResponseShowStadium>):RecyclerView.Adapter<ViewHolderStadiamUser>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderStadiamUser {
        return ViewHolderStadiamUser(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_stadiam,parent,false
            )
        )
    }

    override fun getItemCount(): Int = mDataStadiam.size

    override fun onBindViewHolder(holder: ViewHolderStadiamUser, position: Int) {

        Picasso.get().load(Utils.host+"/imageregister/"+mDataStadiam.get(position).img).into(holder.ImgOPT)
        Picasso.get().load(Utils.host+"/imageStadium/"+mDataStadiam.get(position).Simg).into(holder.ImgStadiam)

        Log.d("ImageStadium",Utils.host+"/imageregister/"+mDataStadiam.get(position).img)
        Log.d("ImageStadium",Utils.host+"/imageStadium/"+mDataStadiam.get(position).Simg)

        holder.nameStadiam.setText(mDataStadiam[position].s_name)
        holder.time.text = "เปิด :"+mDataStadiam[position].s_timeopen.substring(0,5)+" ถึง "+mDataStadiam[position].s_timeclose.substring(0,5)
        holder.price.text = "ราคา : ${mDataStadiam[position].s_price} บาท ต่อชั่วโมง"
        holder.address.text= "ที่อยู่ : \n ${mDataStadiam[position].s_address}"
    }

}

class ViewHolderStadiamUser(item: View):RecyclerView.ViewHolder(item){
    val ImgOPT = item.findViewById<ImageView>(R.id.img_item_userstadiam)
    val nameStadiam = item.findViewById<TextView>(R.id.tv_namestadiam)
    val ImgStadiam = item.findViewById<ImageView>(R.id.img_stadiam)
    val time = item.findViewById<TextView>(R.id.time)
    val price = item.findViewById<TextView>(R.id.price)
    val address = item.findViewById<TextView>(R.id.address)
}