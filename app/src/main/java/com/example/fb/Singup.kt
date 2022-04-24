package com.example.fb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_singup.*

class Singup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)
        supsave.setOnClickListener {
            var id=supid.text.toString()
            var name=supnamme.text.toString()
            var username=supusername.text.toString()
            var pass=suppassword.text.toString()
            var email=supemail.text.toString()
            var no=supphone.text.toString()
            var s1=Sup(id,name,username,pass,email,no)
            var db= FirebaseDatabase.getInstance().getReference("User")
            db.child(id).setValue(s1).addOnSuccessListener {
                Toast.makeText(this,"Record inserted Successfully",Toast.LENGTH_LONG).show()
                supid.setText("")
                supnamme.setText("")
                supusername.setText("")
                supemail.setText("")
                supphone.setText("")
                suppassword.setText("")
            }
            .addOnFailureListener {
                Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
            }
        }
    }
}