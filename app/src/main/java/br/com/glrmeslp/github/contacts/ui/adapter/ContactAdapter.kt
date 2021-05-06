package br.com.glrmeslp.github.contacts.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.glrmeslp.github.contacts.models.Contact

class ContactAdapter(
    private val contacts: MutableList<Contact>
) : RecyclerView.Adapter<ContactViewHolder>() {

    lateinit var onItemClickListener: IOnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false).run {
            ContactViewHolder(this).run {
                configureItemClick(onItemClickListener)
                return this
            }
        }
    }

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        contacts[position].run {
            holder.setContact(this)
        }
    }

    fun actualize() {
        notifyDataSetChanged()
    }
}
