package com.mikepenz.fastadapter.app.items

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.app.R
import com.mikepenz.fastadapter.items.AbstractItem
import com.mikepenz.fastadapter.ui.utils.FastAdapterUIUtils

/**
 * Created by mikepenz on 28.12.15.
 */
open class ProviderDetailHeaderInput : AbstractItem<ProviderDetailHeaderInput.ViewHolder>() {

    /**
     * defines the type defining this item. must be unique. preferably an id
     *
     * @return the type
     */
    override val type: Int
        get() = R.id.fastadapter_provider_detail_header_item_id

    /**
     * defines the layout which will be used for this item in the list
     *
     * @return the layout for this item
     */
    override val layoutRes: Int
        get() = R.layout.provider_header_input_item

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    /**
     * our ViewHolder
     */
    class ViewHolder(private var view: View) : FastAdapter.ViewHolder<ProviderDetailHeaderInput>(view) {

        override fun bindView(item: ProviderDetailHeaderInput, payloads: List<Any>) {
            //get the context
            val ctx = itemView.context

            //set the background for the item
            view.background = FastAdapterUIUtils.getSelectableBackground(ctx, Color.RED, true)
            //set the text for the name
//            StringHolder.applyTo(item.name, name)
//            //set the text for the description or hide
//            StringHolder.applyToOrHide(item.description, description)
        }

        override fun unbindView(item: ProviderDetailHeaderInput) {
//            name.text = null
//            description.text = null
        }
    }
}
