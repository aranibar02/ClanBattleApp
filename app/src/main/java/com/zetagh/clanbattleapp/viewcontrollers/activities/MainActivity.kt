package com.zetagh.clanbattleapp.viewcontrollers.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.zetagh.clanbattleapp.R
import com.zetagh.clanbattleapp.viewcontrollers.fragments.ChatFragment
import com.zetagh.clanbattleapp.viewcontrollers.fragments.HomeFragment
import com.zetagh.clanbattleapp.viewcontrollers.fragments.MapFragment
import com.zetagh.clanbattleapp.viewcontrollers.fragments.RankFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item -> return@OnNavigationItemSelectedListener navigateTo(item) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        toolbar.title = "Home in case"
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_home

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation_toolbar,menu)
        return true
    }

    private fun fragmentFor(item: MenuItem): Fragment? {
        when(item.itemId){
            R.id.navigation_home -> {
                toolbar.title = "Home"
                return HomeFragment()
            }
            R.id.navigation_sources -> {
                toolbar.title = "Map"
                return MapFragment()
            }
            R.id.navigation_ranking -> {
                toolbar.title = "Groups"
                return RankFragment()
            }
            R.id.navigation_favorites -> {
                toolbar.title = "Message"
                return ChatFragment()
            }
        }
        return HomeFragment()
    }

    private fun navigateTo(item: MenuItem):Boolean {
        item.setChecked(true)
        return supportFragmentManager
                .beginTransaction()
                .replace(R.id.content,fragmentFor(item))
                .commit()>0
    }

//    Funcion solo por motivos de test
    private fun ToastTest(text:String , context:Context){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
    }


}
