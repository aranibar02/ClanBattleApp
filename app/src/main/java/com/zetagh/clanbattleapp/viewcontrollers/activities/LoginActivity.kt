package com.zetagh.clanbattleapp.viewcontrollers.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApi
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.common.api.Status
import com.zetagh.clanbattleapp.R
import kotlinx.android.synthetic.main.content_login.*

class LoginActivity : AppCompatActivity() {

    var mGoogleApiClient:GoogleApiClient?=null
    val RC_SIGN_IN:Int = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this,object:GoogleApiClient.OnConnectionFailedListener {
                    override fun onConnectionFailed(p0: ConnectionResult) {
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build()

        sign_in_button.setOnClickListener{
            signIn()
        }
        signOutButton.setOnClickListener{
            signOut()
        }

        loginButton.setOnClickListener{
            val context:Context = it.context
            context.startActivity(
                    Intent(context,OnboardingActivity::class.java)
            )
        }

    }

    private fun signIn() {
        var signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)

        startActivityForResult(signInIntent,RC_SIGN_IN)
    }
    private fun signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback { object:ResultCallback<Status>{
            override fun onResult(p0: Status) {
                Toast.makeText(this@LoginActivity,"Signed out",Toast.LENGTH_SHORT).show()
            }
        } }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==RC_SIGN_IN){
            var result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }

    }

    private fun handleSignInResult(result: GoogleSignInResult?) {
        if(result!!.isSuccess){

            var acct = result.signInAccount
            Toast.makeText(this,"${acct!!.displayName}",Toast.LENGTH_SHORT).show()
        }
        else{

        }
    }

}
