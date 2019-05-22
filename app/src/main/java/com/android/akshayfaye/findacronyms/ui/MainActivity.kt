package com.android.akshayfaye.findacronyms.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.akshayfaye.findacronyms.AcronymsRecyclerAdapter
import com.android.akshayfaye.findacronyms.R
import com.android.akshayfaye.findacronyms.data.AcronymsData
import com.android.akshayfaye.findacronyms.utilities.InjectorUtils
import com.android.akshayfaye.findacronyms.utilities.Utils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter : AcronymsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
    }

    /**
     * initializes views, adapters and viewModel
     */
    private fun initialize(){

        adapter = AcronymsRecyclerAdapter(this)
        acronyms_recycler_view.adapter = adapter
        acronyms_recycler_view.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL ,false)

        val factory = InjectorUtils.provideAcronymsViewModelFactory(this)
        val viewModel : AcronymsViewModel = ViewModelProviders.of(this, factory). get(AcronymsViewModel::class.java)

        find_acronyms_button.setOnClickListener{
            setVisibility(false)
            if(Utils.validateAcronymsString(acronyms_search_edit_text.text.toString().trim())){
                progress_bar.visibility = View.VISIBLE

                viewModel.getFullForms(acronyms_search_edit_text.text.toString())
                    ?.observe(this, Observer {

                        updateViews(it);
                    })
            }else{
                Snackbar.make(root_layout, getString(R.string.not_valid_acronyms), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    /**
     * Method to update views
     * @param acronymsDataList
     */
    private fun updateViews(acronymsDataList: List<AcronymsData?>?){
        if(acronymsDataList != null){
            setVisibility(true)
            acronyms_text_view.text = acronymsDataList.get(0)?.sf
            acronymsDataList.get(0)?.lfs?.let { adapter.setLongFormList(it) }
            adapter.notifyDataSetChanged()

        }else{
            Snackbar.make(root_layout, getString(R.string.no_data_for_acronyms), Snackbar.LENGTH_LONG).show()
        }

        progress_bar.visibility = View.GONE
    }

    /**
     * Method to switch views visibility
     * @param visibilityFlag
     */
    private fun setVisibility(visibilityFlag: Boolean){

        if(visibilityFlag){
            acronyms_text_view.visibility = View.VISIBLE
            full_form_text_view.visibility = View.VISIBLE
            acronyms_recycler_view.visibility = View.VISIBLE
        }else{
            acronyms_text_view.visibility = View.GONE
            full_form_text_view.visibility = View.GONE
            acronyms_recycler_view.visibility = View.GONE
        }

    }

}
