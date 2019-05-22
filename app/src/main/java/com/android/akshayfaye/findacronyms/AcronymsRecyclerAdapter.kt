package com.android.akshayfaye.findacronyms

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.akshayfaye.findacronyms.data.LongFormData
import kotlinx.android.synthetic.main.acronyms_details.view.*

/**
 * Recycler Adapter for RecyclerView
 * @param context
 */
class AcronymsRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<AcronymsRecyclerAdapter.ViewHolder>(){

    private var longFormDataDataList : List<LongFormData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(
            R.layout.acronyms_details,
            parent,
            false)

        val viewHolder = ViewHolder(view);

        return viewHolder;
    }

    fun setLongFormList(longFormDataDataList : List<LongFormData>) {
        this.longFormDataDataList = longFormDataDataList
    }

    override fun getItemCount(): Int {
        return longFormDataDataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(longFormDataDataList.get(position));
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val fullnameTextView = view.fullname_text_view
        val freqTextView = view.freq_text_view
        val sinceTextView = view.since_text_view

        fun setData(longFormData: LongFormData){
            fullnameTextView?.text = longFormData.lf
            freqTextView?.text = "Frequency: ${longFormData.freq}"
            sinceTextView?.text = "Since: ${longFormData.since}"
        }
    }
}