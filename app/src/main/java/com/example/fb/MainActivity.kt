package com.example.fb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var flage=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnlogin.setOnClickListener {

            var db = FirebaseDatabase.getInstance().getReference("User")

            db.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {

                        for (usersnap in snapshot.children) {
                            val user=usersnap.getValue(Sup::class.java)
                            var username=txtuname.text.toString()
                            var pss=txtpass.text.toString()
                            if(TextUtils.isEmpty(username) || TextUtils.isEmpty(pss))
                            {
                                Toast.makeText(applicationContext,
                                    "Please Enter UserName/Password",
                                    Toast.LENGTH_LONG).show()
                            }
                            if (username.equals("${user!!.sup_username}") && pss.equals("${user!!.sup_pass}")) {

                                var preference = getSharedPreferences("MyPref", MODE_PRIVATE)
                                var editor = preference.edit()
                                editor.putString("UserName", username)
                                editor.commit()
                                var intent = Intent(applicationContext, ViewAll::class.java)
                                startActivity(intent)
                                finish()
                                flage=true
                                break

                            }
                        }
                        if (flage==false)
                        {
                            Toast.makeText(applicationContext,"invalid username password",Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }
        signup.setOnClickListener {
            var intent = Intent(applicationContext,Singup::class.java)
            startActivity(intent)
        }

    }
}
