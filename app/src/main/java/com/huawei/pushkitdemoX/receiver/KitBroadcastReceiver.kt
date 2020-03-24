package com.huawei.pushkitdemoX.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.huawei.pushkitdemoX.MainActivity
import com.huawei.pushkitdemoX.constant.IntentConstant

class KitBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val bundle = intent!!.extras
        if (bundle?.getString(IntentConstant.KEY_MSG) != null) {
            if (IntentConstant.VALUE_ON_NEW_TOKEN == bundle.getString(IntentConstant.KEY_METHOD)) {
                MainActivity.token = bundle.getString(IntentConstant.KEY_MSG)
            }
        }
    }
}