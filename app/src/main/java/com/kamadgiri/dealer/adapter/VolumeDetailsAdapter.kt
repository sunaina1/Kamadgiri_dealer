package com.kamadgiri.dealer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

import com.kamadgiri.dealer.R
import com.kamadgiri.dealer.model.transactionVolume.TransactionItemModel
import java.util.*

class VolumeDetailsAdapter(data: List<TransactionItemModel>, context1: Context) :
    RecyclerView.Adapter<VolumeDetailsAdapter.MyViewHolder>() {
    private val dataSet: ArrayList<TransactionItemModel>
    private val context: Context


    inner class MyViewHolder(v: View) : ViewHolder(v) {

        val productNameTV: TextView
        val batchNumberTV: TextView
        val cropTV: TextView
        val packetsInsideTV: TextView
        val packetsWeightTV: TextView
        val bagWeightTV: TextView



        init {

            productNameTV = v.findViewById<View>(R.id.productNameTV) as TextView
            batchNumberTV = v.findViewById<View>(R.id.batchNumberTV) as TextView
            cropTV = v.findViewById<View>(R.id.cropTV) as TextView
            packetsInsideTV = v.findViewById<View>(R.id.packetsInsideTV) as TextView
            packetsWeightTV = v.findViewById<View>(R.id.packetsWeightTV) as TextView
            bagWeightTV = v.findViewById<View>(R.id.bagWeightTV) as TextView



        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.volume_detail_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        listPosition: Int
    ) {
        val item: TransactionItemModel = dataSet[listPosition]
        holder.productNameTV.setText(item.productId)
        holder.batchNumberTV.setText(item.batchNo)
        holder.cropTV.setText(item.crop)
        holder.packetsInsideTV.setText(item.packetsInside)
        holder.packetsWeightTV.setText(item.packetWeight)
        holder.bagWeightTV.setText(item.bagWeight)



    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun clearDataSource() {
        dataSet.clear()
        notifyDataSetChanged()
    }

    init {
        dataSet = data as ArrayList<TransactionItemModel>
        context = context1

    }
}