package ru.prodsouz.pda.scanner.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import ru.prodsouz.pda.scanner.service.PDAScanIntentService

class PDAScanReceiver : BroadcastReceiver() {

    companion object {
        const val SCAN_ACTION = "scan.rcv.enter"
        const val SCAN_EXTRA_KEY = "codestr"
    }

    override fun onReceive(context: Context, intent: Intent) {
        val data =
            when (intent.action) {
                SCAN_ACTION -> intent.extras?.getString(SCAN_EXTRA_KEY) ?: return
                else -> return
            }

        PDAScanIntentService.startService(context, data)

        abortBroadcast()
    }
}
