package com.kamadgiri.dealer.adapter

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tc.utils.DateTimeUtil

import com.kamadgiri.dealer.R
import com.kamadgiri.dealer.model.pointSummary.PointItemModel
import com.kamadgiri.dealer.model.transactionVolume.TransactionItemModel
import java.util.*

class PointSummaryAdapter(data: List<PointItemModel>, context1: Context) :
    RecyclerView.Adapter<PointSummaryAdapter.MyViewHolder>() {
    private val dataSet: ArrayList<PointItemModel>
    private val context: Context
    lateinit var dateTimeUtil: DateTimeUtil

    inner class MyViewHolder(v: View) : ViewHolder(v) {

        val sourceTV: TextView
        val transactionTypeTV: TextView
        val statusTV: TextView
        val dateTV: TextView
        val pointTV: TextView

        init {

            sourceTV = v.findViewById<View>(R.id.sourceTV) as TextView
            transactionTypeTV = v.findViewById<View>(R.id.transactionTypeTV) as TextView
            statusTV = v.findViewById<View>(R.id.statusTV) as TextView
            dateTV = v.findViewById<View>(R.id.dateTV) as TextView
            pointTV = v.findViewById<View>(R.id.pointTV) as TextView




        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.point_summary_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        listPosition: Int
    ) {
        val item: PointItemModel = dataSet[listPosition]
        holder.sourceTV.setText(item.source)
        holder.transactionTypeTV.setText(item.transactionType)
        holder.statusTV.setText(item.transactionStatus)
        if (item.transactionDate != null) {
            var date = dateTimeUtil.formateDate(item.transactionDate)

            holder.dateTV.setText(date)
        }

        holder.pointTV.setText(item.pointValue + " PT")




    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun clearDataSource() {
        dataSet.clear()
        notifyDataSetChanged()
    }

    init {
        dataSet = data as ArrayList<PointItemModel>
        context = context1
        dateTimeUtil = DateTimeUtil()
    }
}