package com.huawei.pushkitdemoX

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.huawei.pushkit.HMSPushKitManager
import com.huawei.pushkitdemoX.constant.Constant
import com.huawei.pushkitdemoX.receiver.KitBroadcastReceiver
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var manager: HMSPushKitManager
    lateinit var kitBroadcastReceiver: KitBroadcastReceiver
    private lateinit var intentFilter: IntentFilter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager = HMSPushKitManager(this)
        createReceiver()
        setListener()
    }

    fun setListener() {
        btn_get_aaid.setOnClickListener(this)
        btn_delete_aaid.setOnClickListener(this)
        btn_get_token.setOnClickListener(this)
        btn_delete_token.setOnClickListener(this)
    }

    fun createReceiver() {
        kitBroadcastReceiver = KitBroadcastReceiver()
        intentFilter = IntentFilter()
        intentFilter.addAction(Constant.PUSH_ACTION)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_get_aaid -> manager.getAAID { println("getAAID-itt: $it") }
            R.id.btn_delete_aaid -> manager.deleteAAID { println("deleteAAID-itt: $it") }
            R.id.btn_get_token -> manager.getToken { println("getToken-itt: $it") }
            R.id.btn_delete_token -> manager.deleteToken { println("deleteToken-itt: $it") }
        }
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(kitBroadcastReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(kitBroadcastReceiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(kitBroadcastReceiver)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        registerReceiver(kitBroadcastReceiver, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(kitBroadcastReceiver)
    }

    companion object {
        var token: String? = null
    }

}
