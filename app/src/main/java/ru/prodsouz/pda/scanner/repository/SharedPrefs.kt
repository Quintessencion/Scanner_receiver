package ru.prodsouz.pda.scanner.repository

import android.content.SharedPreferences
import ru.prodsouz.pda.scanner.extensions.get
import ru.prodsouz.pda.scanner.extensions.set
import java.util.concurrent.ArrayBlockingQueue

object SharedPrefs {

    lateinit var sharedPreferences: SharedPreferences

    var barCode: String
        get() {
            val codeList = sharedPreferences[::barCode.name]
            if (codeList.isNullOrEmpty()) return ""

            val code = codeList.poll()
            sharedPreferences[::barCode.name] = codeList

            return code
        }
        set(value) {
            val codeList = sharedPreferences[::barCode.name] ?: ArrayBlockingQueue<String>(30)
            codeList.add(value)
            sharedPreferences[::barCode.name] = codeList
        }
}