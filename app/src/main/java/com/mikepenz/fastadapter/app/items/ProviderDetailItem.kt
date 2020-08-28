package com.mikepenz.fastadapter.app.items

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.app.R
import com.mikepenz.fastadapter.drag.IDraggable
import com.mikepenz.fastadapter.items.AbstractItem
import com.mikepenz.fastadapter.ui.utils.FastAdapterUIUtils
import com.mikepenz.materialdrawer.holder.StringHolder

/**
 * Created by mikepenz on 28.12.15.
 */
open class ProviderDetailItem : AbstractItem<ProviderDetailItem.ViewHolder>(), IDraggable {

    var header: String? = null
    var name: StringHolder? = null
    var description: StringHolder? = null

    override var isDraggable = true

    /**
     * defines the type defining this item. must be unique. preferably an id
     *
     * @return the type
     */
    override val type: Int
        get() = R.id.fastadapter_provider_detail_item_id

    /**
     * defines the layout which will be used for this item in the list
     *
     * @return the layout for this item
     */
    override val layoutRes: Int
        get() = R.layout.provider_item

    fun withHeader(header: String): ProviderDetailItem {
        this.header = header
        return this
    }

    fun withName(Name: String): ProviderDetailItem {
        this.name = StringHolder(Name)
        return this
    }

    fun withName(@StringRes NameRes: Int): ProviderDetailItem {
        this.name = StringHolder(NameRes)
        return this
    }

    fun withDescription(description: String): ProviderDetailItem {
        this.description = StringHolder(description)
        return this
    }

    fun withDescription(@StringRes descriptionRes: Int): ProviderDetailItem {
        this.description = StringHolder(descriptionRes)
        return this
    }

    fun withIdentifier(identifier: Long): ProviderDetailItem {
        this.identifier = identifier
        return this
    }

    fun withIsDraggable(draggable: Boolean): ProviderDetailItem {
        this.isDraggable = draggable
        return this
    }

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    /**
     * our ViewHolder
     */
    class ViewHolder(private var view: View) : FastAdapter.ViewHolder<ProviderDetailItem>(view) {
        var name: TextView = view.findViewById(R.id.material_drawer_name)
        var description: TextView = view.findViewById(R.id.material_drawer_description)

        override fun bindView(item: ProviderDetailItem, payloads: List<Any>) {
            //get the context
            val ctx = itemView.context

            //set the background for the item
            view.background = FastAdapterUIUtils.getSelectableBackground(ctx, Color.RED, true)
            //set the text for the name
            StringHolder.applyTo(item.name, name)
            //set the text for the description or hide
            StringHolder.applyToOrHide(item.description, description)
        }

        override fun unbindView(item: ProviderDetailItem) {
            name.text = null
            description.text = null
        }
    }
}
