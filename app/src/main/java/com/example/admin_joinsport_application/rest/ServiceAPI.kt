package com.example.myapplicationproject.rest

import com.example.admin_joinsport_application.body.BodyComment
import com.example.admin_joinsport_application.body.BodyGetComment
import com.example.admin_joinsport_application.body.BodyPost
import com.example.admin_joinsport_application.model.*
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface ServiceAPI {
    //Post
    @POST("/Post")
    fun doPost(@Body body : BodyPost?):Observable<ResponsePost>

    @GET("/Post")
    fun doShowPost():Observable<List<ResponsePost>>


    @POST("/Comment")
    fun doComment(@Body body : BodyComment?):Observable<ResponseComment>

    @DELETE("/deletePost/{id}")
    fun  deletePost(@Path("id")id: Int) :Observable<ResponsePost>

    @DELETE("/deleteComment/{id}")
    fun deleteComment(@Path("id")id: Int):Observable<ResponseComment>

    @PUT("/updateDataPost/{id}")
    fun updateDataPost(@Path("id")id: Int, @Body body :BodyPost?) :Observable<ResponsePost>

    @POST("/getComment")
    fun dogetComment(@Body body : BodyGetComment?):Observable<List<ResponseComment>>

    //User
    @GET("/getUser")
    fun doShowUser():Observable<List<ResponseUser>>

    @GET("/getOPT")
    fun dogetOPT():Observable<List<ResponseOPT>>

    @DELETE("/deleteUser/{id}")
    fun deleteUser(@Path("id")id:Int):Observable<ResponseUser>

    @DELETE("/deleteOPT/{id}")
    fun deleteOPT(@Path("id")id:Int):Observable<ResponseOPT>



    //Activity
    @GET("/activity")
    fun doshowactivity():Observable<List<ResponseActivity>>

    //
    @GET("/ShowStadium")
    fun doShowStadium():Observable<List<ResponseShowStadium>>
}