package com.android.akshayfaye.findacronyms.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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

    private fun initialize(){

        adapter = AcronymsRecyclerAdapter(this)
        acronyms_recycler_view.adapter = adapter

        val factory = InjectorUtils.provideAcronymsViewModelFactory(this)
        val viewModel : AcronymsViewModel = ViewModelProviders.of(this, factory). get(AcronymsViewModel::class.java)

        find_acronyms_button.setOnClickListener{

            if(Utils.validateAcronymsString(acronyms_search_edit_text.text.toString().trim())){

                progress_bar.visibility = View.VISIBLE

                viewModel.getFullForms(acronyms_search_edit_text.text.toString(), "")
                    .observe(this, Observer {

                        updateViews(it);
                    })

            }else{
                Snackbar.make(root_layout, getString(R.string.not_valid_acronyms), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun updateViews(acronymsData: AcronymsData){

        if(acronymsData !== null){
            acronyms_text_view.text = acronymsData.sf
            adapter.setLongFormList(acronymsData.lfs)
            adapter.notifyDataSetChanged()

        }else{
            Snackbar.make(root_layout, getString(R.string.no_data_for_acronyms), Snackbar.LENGTH_LONG).show()
        }

        progress_bar.visibility = View.GONE

    }

}
