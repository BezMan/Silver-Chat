package com.dev.kotlinmessenger

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MessagesListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages_list)

        verifyUserLoggedIn()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_new_message -> {
                launchComposeMessage()
                }
            R.id.menu_sign_out -> {
                FirebaseAuth.getInstance().signOut()
                launchRegister()
                }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun launchComposeMessage() {
        val composeIntent = Intent(this, ComposeMessageActivity::class.java)
        startActivity(composeIntent)
    }


    private fun verifyUserLoggedIn() {
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null) {
            launchRegister()
        }
    }

    private fun launchRegister() {
        val registerIntent = Intent(this, RegisterActivity::class.java)
        registerIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(registerIntent)
    }


}