package com.example.admin_joinsport_application.body

data class BodyPost(
    val user_id:String,
    val username :String,
    val u_img:String,
    val p_message:String,
    val p_time:String,
    val user_status:String
)
data class BodyGetComment (
    val p_id :String
)
data class BodyComment(
    val user_id : String,
    val p_id :String,
    val com_message :String,
    val username:String,
    val img:String,
    val com_time:String
)