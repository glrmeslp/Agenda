package br.com.glrmeslp.github.contacts.ui.adapter

import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.glrmeslp.github.contacts.models.Contact

class ContactViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private lateinit var contact: Contact
    private val textView: TextView = itemView.findViewById(android.R.id.text1)

    fun setContact(contact: Contact) {
        this.contact = contact
        setTextView(contact.toString())
    }

    private fun setTextView(string: String) {
        textView.text = string
    }

    fun configureItemClick(onItemClickListener: IOnItemClickListener) {
        itemView.setOnClickListener(View.OnClickListener {
            onItemClickListener.onItemClick(contact)
        })
    }



}