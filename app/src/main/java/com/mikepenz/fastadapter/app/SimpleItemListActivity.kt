package com.mikepenz.fastadapter.app

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericFastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.IAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter.Companion.items
import com.mikepenz.fastadapter.app.items.ProviderDetailHeaderInput
import com.mikepenz.fastadapter.app.items.ProviderDetailItem
import com.mikepenz.fastadapter.select.getSelectExtension
import kotlinx.android.synthetic.main.activity_sample.*
import java.util.*


class SimpleItemListActivity : AppCompatActivity(){

    private lateinit var fastAdapter: GenericFastAdapter
    private lateinit var itemAdapter: ItemAdapter<ProviderDetailItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        // Package adapter
        itemAdapter = items()

        // Header adapter
        val headerAdapter = ItemAdapter<ProviderDetailHeaderInput>()
        val headerItem = ProviderDetailHeaderInput()
        headerItem.isSelectable = false
        headerAdapter.add(headerItem)

        // Create our FastAdapter which will manage everything
        fastAdapter = FastAdapter.with(listOf(headerAdapter, itemAdapter))

        // Custom selection
        val selectExtension = fastAdapter.getSelectExtension()
        selectExtension.isSelectable = true
        selectExtension.allowDeselection = false

        // Configure our fastAdapter
        fastAdapter.onClickListener = { v: View?, _: IAdapter<GenericItem>, item: GenericItem, _: Int ->
            v?.let {
                if (item is ProviderDetailItem) {
                    // TODO vtt onClick
                    Toast.makeText(v.context, item.name?.getText(v.context), Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(v.context, "Header item click ", Toast.LENGTH_LONG).show()
                }
            }
            false
        }

        // Custom item span
        val span = 3 // TODO calculate span to support wide screen
        val gridLayoutManager = GridLayoutManager(this, span)
        gridLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (fastAdapter.getItem(position)?.type) {
                    R.id.fastadapter_provider_detail_item_id -> 1
                    R.id.fastadapter_provider_detail_header_item_id -> 3
                    else -> -1
                }
            }
        }
        // Setup recycler view basic
        rv.layoutManager = gridLayoutManager
        rv.itemAnimator = DefaultItemAnimator()
        rv.adapter = fastAdapter

        // Fill with some sample data
        var x = 0
        val items = ArrayList<ProviderDetailItem>()
        for (s in ALPHABET) {
            for (i in 1..5) {
                val item = ProviderDetailItem().withName("$s Test $x")
                item.identifier = (100 + x).toLong()
                items.add(item)
                x++
            }
        }
        itemAdapter.add(items)


        //restore selections (this has to be done after the items were added
        fastAdapter.withSavedInstanceState(savedInstanceState)

        //set the back arrow in the toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(false)

        btnRefresh.setOnClickListener {
            Log.i("vtt", "SelectedCount: " + selectExtension.selections.size + " ItemsCount: " + selectExtension.selectedItems.size)
            selectExtension.deselect()
        }
    }

    override fun onSaveInstanceState(_outState: Bundle) {
        var outState = _outState
        //add the values which need to be saved from the adapter to the bundle
        outState = fastAdapter.saveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //handle the click on the back arrow click
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private val ALPHABET = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
    }
}
