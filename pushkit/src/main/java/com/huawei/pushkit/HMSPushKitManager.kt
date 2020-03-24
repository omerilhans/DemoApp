package com.huawei.pushkit

import android.app.Activity
import com.huawei.hms.aaid.HmsInstanceId
import com.huawei.hms.common.ApiException
import com.huawei.pushkitdemoX.constant.Constant

class HMSPushKitManager(val activity: Activity) {

    fun getAAID(getAAIDResult: (aaid: String) -> Unit) {
        val idResult = HmsInstanceId.getInstance(activity).aaid
        idResult.addOnSuccessListener { aaidResult ->
            getAAIDResult(aaidResult.id)
        }.addOnFailureListener {
            getAAIDResult("")
        }
    }

    fun deleteAAID(getDeleteAAIDResult: (result: Boolean) -> Unit) {
        object : Thread() {
            override fun run() {
                try {
                    HmsInstanceId.getInstance(activity).deleteAAID()
                    getDeleteAAIDResult(true)
                } catch (e: Exception) {
                    getDeleteAAIDResult(false)
                }
            }
        }.start()
    }

    fun getToken(getTokenResult: (aaid: String) -> Unit) {
        object : Thread() {
            override fun run() {
                try {
                    val token = HmsInstanceId.getInstance(activity).getToken(Constant.appid, "HCM")
                    getTokenResult(token)
                } catch (e: java.lang.Exception) {
                    getTokenResult("")
                }
            }
        }.start()
    }

    fun deleteToken(getDeleteTokenResult: (result: Boolean) -> Unit) {
        object : Thread() {
            override fun run() {
                try {
                    HmsInstanceId.getInstance(activity)
                        .deleteToken(Constant.appid, "HCM")
                    getDeleteTokenResult(true)
                } catch (e: ApiException) {
                    getDeleteTokenResult(false)
                    e.printStackTrace()
                }
            }
        }.start()
    }
}