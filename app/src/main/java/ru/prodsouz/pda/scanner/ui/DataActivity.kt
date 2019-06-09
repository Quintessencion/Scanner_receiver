package ru.prodsouz.pda.scanner.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.prodsouz.pda.scanner.BuildConfig
import ru.prodsouz.pda.scanner.repository.SharedPrefs

class DataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SharedPrefs.sharedPreferences = getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)

        setResult(RESULT_OK, Intent().apply { putExtra("name", SharedPrefs.barCode) })

        finish()
    }
}
