package ru.prodsouz.pda.scanner.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.widget.Toast
import ru.prodsouz.pda.scanner.R

class PDAScanReceiver : BroadcastReceiver() {

    companion object {
        const val SCAN_ACTION = "scan.rcv.enter"
        const val SCAN_EXTRA_KEY = "codestr"

        const val ACTION_SEND_INTENT = "ru.prodsouz.pda.scanner.barcode.intent"
        const val BARCODE_DATA = "BARCODE_DATA"
    }

    override fun onReceive(context: Context, intent: Intent) {
        val data =
            when (intent.action) {
                SCAN_ACTION -> intent.extras?.getString(SCAN_EXTRA_KEY) ?: return
                else -> return
            }

        var infoText = context.getString(R.string.send_broadcast)

        val intentFor1C = Intent(ACTION_SEND_INTENT).apply { putExtra(BARCODE_DATA, data) }
        context.sendBroadcast(intentFor1C)

        val activities: List<ResolveInfo> = context.packageManager.queryIntentActivities(
            intentFor1C,
            PackageManager.MATCH_DEFAULT_ONLY
        )
        if (activities.isNotEmpty()) {
            context.startActivity(intentFor1C)
            infoText += "\n\n" + context.getString(R.string.data_transfer_to_another_app)
        } else {
            infoText += "\n\n" + context.getString(R.string.no_intent_activity)
        }

        Toast.makeText(context, infoText, Toast.LENGTH_SHORT).show()

        abortBroadcast()
    }
}
