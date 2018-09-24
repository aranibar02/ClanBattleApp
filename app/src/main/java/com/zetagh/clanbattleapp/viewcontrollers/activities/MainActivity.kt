package com.zetagh.clanbattleapp.viewcontrollers.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.zetagh.clanbattleapp.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        toolbar.title = "Home in case"

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation_toolbar,menu)
        return true
    }
}
