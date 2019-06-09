package ru.prodsouz.pda.scanner.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import ru.prodsouz.pda.scanner.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toast.makeText(this, getString(R.string.register_title), Toast.LENGTH_SHORT).show()

        finish()
    }
}