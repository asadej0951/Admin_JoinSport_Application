package com.example.admin_joinsport_application.presenter

import android.util.Log
import com.example.admin_joinsport_application.body.BodyComment
import com.example.admin_joinsport_application.body.BodyGetComment
import com.example.admin_joinsport_application.body.BodyPost
import com.example.admin_joinsport_application.model.*
import com.example.myapplicationproject.rest.DataModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class PresenterPost {
    var mDisposable: Disposable? = null


    fun PostPresenterRX(
        user_id :String,
        username:String,
        u_img:String,
        p_message :String,
        p_time:String, user_status:String,
        dataResponse:(ResponsePost)->Unit,
        MessageError:(String)->Unit
    ){
        mDisposable = DataModule.myAppService.doPost(
            BodyPost(
                user_id,
                username,
                u_img,
                p_message,p_time,user_status
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<ResponsePost>(){
                override fun onComplete() {}
                override fun onNext(response: ResponsePost) {
                    Log.d("Post",response.toString())
                    dataResponse.invoke(response)
                }
                override fun onError(e: Throwable) {
                    MessageError.invoke(e.message!!)
                }

            })
    }
    fun ShowPostPresenterRX(
        dataResponse:(List<ResponsePost>)->Unit,
        MessageError:(String)->Unit
    ){
        mDisposable = DataModule.myAppService.doShowPost()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<ResponsePost>>(){
                override fun onComplete() {}
                override fun onNext(response: List<ResponsePost>) {
                    Log.d("Post",response.toString())
                    dataResponse.invoke(response)
                }
                override fun onError(e: Throwable) {
                    MessageError.invoke(e.message!!)
                }

            })
    }
    fun CommentPresenterRX(
        user_id: String,
        p_id:String,
        com_message:String,
        username: String,
        img:String,
        com_time:String,
        dataResponse:(ResponseComment)->Unit,
        MessageError:(String)->Unit
    ){
        mDisposable = DataModule.myAppService.doComment(BodyComment(user_id, p_id, com_message,username,img, com_time))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<ResponseComment>(){
                override fun onComplete() {}
                override fun onNext(response: ResponseComment) {
                    Log.d("Comment",response.toString())
                    dataResponse.invoke(response)
                }
                override fun onError(e: Throwable) {
                    Log.d("Comment",e.message.toString())
                    MessageError.invoke(e.message!!)
                }

            })
    }
    fun GetCommentPresenterRX(
        p_id:String,
        dataResponse:(List<ResponseComment>)->Unit,
        MessageError:(String)->Unit
    ){
        mDisposable = DataModule.myAppService.dogetComment(BodyGetComment(p_id))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<ResponseComment>>(){
                override fun onComplete() {}
                override fun onNext(response: List<ResponseComment>) {
                    Log.d("Comment",response.toString())
                    dataResponse.invoke(response)
                }
                override fun onError(e: Throwable) {
                    MessageError.invoke(e.message!!)
                }

            })
    }
    fun deletePostPresenterRX(
        p_id: Int,
        dataResponse:(ResponsePost)->Unit,
        MessageError:(String)->Unit
    ){
        mDisposable = DataModule.myAppService.deletePost(p_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<ResponsePost>(){
                override fun onComplete() {}
                override fun onNext(response: ResponsePost) {
                    Log.d("deletePost",response.toString())
                    dataResponse.invoke(response)
                }
                override fun onError(e: Throwable) {
                    Log.d("deletePost",e.message.toString())
                    MessageError.invoke(e.message!!)
                }

            })
    }
    fun deleteCommentPresenterRX(
        p_id: Int,
        dataResponse:(ResponseComment)->Unit,
        MessageError:(String)->Unit
    ){
        mDisposable = DataModule.myAppService.deleteComment(p_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<ResponseComment>(){
                override fun onComplete() {}
                override fun onNext(response: ResponseComment) {
                    Log.d("deletePost",response.toString())
                    dataResponse.invoke(response)
                }
                override fun onError(e: Throwable) {
                    Log.d("deletePost",e.message.toString())
                    MessageError.invoke(e.message!!)
                }

            })
    }
    fun deleteUserPresenterRX(
        u_id: Int,
        dataResponse:(ResponseUser)->Unit,
        MessageError:(String)->Unit
    ){
        mDisposable = DataModule.myAppService.deleteUser(u_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<ResponseUser>(){
                override fun onComplete() {}
                override fun onNext(response: ResponseUser) {
                    Log.d("deletePost",response.toString())
                    dataResponse.invoke(response)
                }
                override fun onError(e: Throwable) {
                    Log.d("deletePost",e.message.toString())
                    MessageError.invoke(e.message!!)
                }

            })
    }
    fun deleteOPTPresenterRX(
        o_id: Int,
        dataResponse:(ResponseOPT)->Unit,
        MessageError:(String)->Unit
    ){
        mDisposable = DataModule.myAppService.deleteOPT(o_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<ResponseOPT>(){
                override fun onComplete() {}
                override fun onNext(response: ResponseOPT) {
                    Log.d("deletePost",response.toString())
                    dataResponse.invoke(response)
                }
                override fun onError(e: Throwable) {
                    Log.d("deletePost",e.message.toString())
                    MessageError.invoke(e.message!!)
                }

            })
    }
    fun updateDataPostPresenterRX(
        p_id: Int,
        user_id :String,
        username:String,
        u_img:String,
        p_message :String,
        p_time:String,user_status:String,
        dataResponse:(ResponsePost)->Unit,
        MessageError:(String)->Unit
    ){
        mDisposable = DataModule.myAppService.updateDataPost(p_id,
            BodyPost(user_id, username, u_img, p_message, p_time, user_status)
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<ResponsePost>(){
                override fun onComplete() {}
                override fun onNext(response: ResponsePost) {
                    Log.d("updateDataPost",response.toString())
                    dataResponse.invoke(response)
                }
                override fun onError(e: Throwable) {
                    Log.d("updateDataPost",e.message.toString())
                    MessageError.invoke(e.message!!)
                }

            })
    }
    fun ShowOPTPresenterRX(
        dataResponse:(List<ResponseOPT>)->Unit,
        MessageError:(String)->Unit
    ){
        mDisposable = DataModule.myAppService.dogetOPT()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<ResponseOPT>>(){
                override fun onComplete() {}
                override fun onNext(response: List<ResponseOPT>) {
                    Log.d("Post",response.toString())
                    dataResponse.invoke(response)
                }
                override fun onError(e: Throwable) {
                    MessageError.invoke(e.message!!)
                }

            })
    }
    fun ShowUserPresenterRX(
        dataResponse:(List<ResponseUser>)->Unit,
        MessageError:(String)->Unit
    ){
        mDisposable = DataModule.myAppService.doShowUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<ResponseUser>>(){
                override fun onComplete() {}
                override fun onNext(response: List<ResponseUser>) {
                    Log.d("Post",response.toString())
                    dataResponse.invoke(response)
                }
                override fun onError(e: Throwable) {
                    MessageError.invoke(e.message!!)
                }

            })
    }
    fun ShowActivityPresenterRX(
        dataResponse:(List<ResponseActivity>)->Unit,
        MessageError:(String)->Unit
    ){
        mDisposable = DataModule.myAppService.doshowactivity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<ResponseActivity>>(){
                override fun onComplete() {}
                override fun onNext(response: List<ResponseActivity>) {
                    Log.d("Post",response.toString())
                    dataResponse.invoke(response)
                }
                override fun onError(e: Throwable) {
                    MessageError.invoke(e.message!!)
                }

            })
    }
    fun ShowStadiumPresenterRX(
        dataResponse:(List<ResponseShowStadium>)->Unit,
        MessageError:(String)->Unit
    ){
        mDisposable = DataModule.myAppService.doShowStadium()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<ResponseShowStadium>>(){
                override fun onComplete() {}
                override fun onNext(response: List<ResponseShowStadium>) {
                    Log.d("Post",response.toString())
                    dataResponse.invoke(response)
                }
                override fun onError(e: Throwable) {
                    MessageError.invoke(e.message!!)
                }

            })
    }
}