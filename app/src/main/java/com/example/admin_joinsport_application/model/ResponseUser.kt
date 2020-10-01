package com.example.admin_joinsport_application.model

data class ResponseOPT(
    val img: String,
    val o_Sname: String,
    val o_address: String,
    val o_email: String,
    val o_id: Int,
    val o_lname: String,
    val o_name: String,
    val o_pass: String,
    val o_status: String,
    val o_tel: String,
    val o_user: String
)
data class ResponseUser(
    val u_id:String,
    val u_user: String,
    val u_pass: String,
    val u_name: String,
    val u_lname: String,
    val u_email: String,
    val u_tel: String,
    val u_status: String,
    val img :String
)