package com.zetagh.clanbattleapp.viewcontrollers.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zetagh.clanbattleapp.R
import kotlinx.android.synthetic.main.content_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener{
            val context:Context = it.context
            context.startActivity(
                    Intent(context,OnboardingActivity::class.java)
            )
        }


    }
}
