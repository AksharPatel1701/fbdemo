package com.example.fb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_item.view.*

class SupAdapter (val context: Context, var arr:ArrayList<Sup>)
    : RecyclerView.Adapter<SupAdapter.PersonViewHolde>()

{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolde {
        var inflater= LayoutInflater.from(parent.context)
        var view= inflater.inflate(R.layout.card_item,parent,false)
        return PersonViewHolde(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolde, position: Int) {
        holder.bind(arr[position])
        holder.itemView.imgDelete.setOnClickListener {
            if(context is ViewAll)
            {
                context.delete(position)
            }
        }
        holder.itemView.imgUpdate.setOnClickListener {
            if(context is ViewAll)
            {
                context.update(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return  arr.size
    }

    class PersonViewHolde(var view:View):RecyclerView.ViewHolder(view)
    {
        fun bind(p:Sup)
        {
            view.tvUname.setText(p.id)
            view.tvfname.setText(p.sup_name)
            view.tvlname.setText(p.sup_username)
            view.tvxyz.setText(p.sup_pass)
            view.tvabc.setText(p.sup_emil)
            view.tvpqr.setText(p.sup_numb)


        }
    }
}