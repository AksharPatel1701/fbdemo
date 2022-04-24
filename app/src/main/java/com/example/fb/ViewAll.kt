package com.example.fb

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_view_all.*
import kotlinx.android.synthetic.main.update_layout.*

class ViewAll : AppCompatActivity() {
    lateinit var arr:ArrayList<Sup>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all)
        retriveAll()
    }
    fun retriveAll()
    {
        var db= FirebaseDatabase.getInstance().getReference("User")
        arr=ArrayList<Sup>()
        db.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    arr.clear()
                    for(usersnap in snapshot.children)
                    {
                        var key:String=usersnap.key!!
                        val user=usersnap.getValue(Sup::class.java)
                        var fname:String=user!!.id
                        var lname:String=user!!.sup_name
                        var uname:String=user!!.sup_username
                        var pass:String=user!!.sup_pass
                        var email:String=user!!.sup_emil
                        var num:String=user!!.sup_numb

                        var user1 = Sup(fname,lname,uname,pass,email,num)
                        //Toast.makeText(applicationContext,user?.fname,Toast.LENGTH_LONG).show()
                        arr.add(user1)
                        refreshRecycle(arr)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
    fun refreshRecycle(arr:ArrayList<Sup>)
    {
        var adpter=SupAdapter(this,arr)
        myrecycle.adapter=adpter

    }




    fun delete(position: Int) {

        var user=arr.get(position)
        var key=user.id
        var db=FirebaseDatabase.getInstance().getReference("User")
        db.child(key).setValue(null).addOnSuccessListener {
            Toast.makeText(this,"Record deleted successfully",Toast.LENGTH_LONG).show()
        }
        retriveAll()
        refreshRecycle(arr)

    }

    fun update(position: Int) {var dialog= Dialog(this)
        dialog.setContentView(R.layout.update_layout)
        dialog.show()
        var user=arr.get(position)
        var key=user.id
        dialog.tvDialogUname.setText(user.id)
        dialog.edtDialogFname.setText(user.sup_emil)
        dialog.edtDialogLname.setText(user.sup_numb)
        dialog.btnUpdate.setOnClickListener {
            var fname=dialog.edtDialogFname.text.toString()
            var lname=dialog.edtDialogLname.text.toString()
            var id:String=user!!.id
            var name:String=user!!.sup_name
            var uname:String=user!!.sup_username
            var pass:String=user!!.sup_pass
            var user=Sup(id,name,uname,pass,fname,lname)
            var db=FirebaseDatabase.getInstance().getReference("User")
            db.child(key).setValue(user).addOnSuccessListener {
                Toast.makeText(this,"Record Updated successfully",Toast.LENGTH_LONG).show()
            }
            dialog.dismiss()
        }
        retriveAll()
        refreshRecycle(arr)

    }
}