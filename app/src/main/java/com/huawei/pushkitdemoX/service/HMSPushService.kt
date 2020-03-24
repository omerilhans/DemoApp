package com.huawei.pushkitdemoX.service

import android.content.Intent
import android.util.Log
import com.huawei.hms.push.HmsMessageService
import com.huawei.hms.push.RemoteMessage
import com.huawei.hms.push.SendException
import com.huawei.pushkitdemoX.constant.Constant
import com.huawei.pushkitdemoX.constant.IntentConstant

class HMSPushService : HmsMessageService() {

    override fun onNewToken(s: String?) {
        super.onNewToken(s)
        Log.d(Constant.PUSH_TAG, s)
        val intent = Intent()
        intent.action = Constant.PUSH_ACTION
        intent.putExtra(IntentConstant.KEY_METHOD, IntentConstant.VALUE_ON_NEW_TOKEN)
        intent.putExtra(IntentConstant.KEY_MSG, s)
        sendBroadcast(intent)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(
                Constant.PUSH_TAG,
                "Message data payload: " + remoteMessage.data
            )
        }
        if (remoteMessage.notification != null) {
            Log.d(
                Constant.PUSH_TAG,
                "Message Notification Body: " + remoteMessage.notification.body
            )
        }
    }

    override fun onMessageSent(s: String?) {
        super.onMessageSent(s)
        val intent = Intent()
        intent.action = Constant.PUSH_ACTION
        intent.putExtra(IntentConstant.KEY_METHOD, IntentConstant.VALUE_ON_MESSAGE_SENT)
        intent.putExtra(IntentConstant.KEY_MSG, s)
        sendBroadcast(intent)
    }

    override fun onSendError(s: String, e: Exception) {
        super.onSendError(s, e)
        val intent = Intent()
        intent.action = Constant.PUSH_ACTION
        intent.putExtra(IntentConstant.KEY_METHOD, IntentConstant.VALUE_ON_SEND_ERROR)
        intent.putExtra(
            IntentConstant.KEY_MSG, s + "onSendError called, message id:" + s + " ErrCode:"
                    + (e as SendException).errorCode + " message:" + e.message
        )
        sendBroadcast(intent)
    }
}