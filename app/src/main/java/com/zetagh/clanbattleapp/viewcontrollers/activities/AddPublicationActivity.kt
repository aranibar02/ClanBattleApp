package com.zetagh.clanbattleapp.viewcontrollers.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zetagh.clanbattleapp.R
import kotlinx.android.synthetic.main.activity_add_publication.*

class AddPublicationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_publication)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
