package br.com.glrmeslp.github.contacts.ui.layouts

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import br.com.glrmeslp.github.contacts.R
import br.com.glrmeslp.github.contacts.models.Contact
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class FormContactsActivity : AppCompatActivity(), IContactActivityConstants {

    private lateinit var contact: Contact
    private val titleAppBar = "New Contact"

    private lateinit var firstNameField: TextInputEditText
    private lateinit var lastNameField: TextInputEditText
    private lateinit var phoneNumberField: TextInputEditText
    private lateinit var emailAddressField: TextInputEditText
    private lateinit var addressField: TextInputEditText
    private lateinit var birthdayField: TextInputEditText
    private lateinit var buttonDeleteContact: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_contacts)
        title = titleAppBar
        initializationComponents()
        configureButtonDeleteContact()
        setSupportActionBar(findViewById(R.id.form_top_bar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_form_contact_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.form_contact_menu_done -> configureMenuDone()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        setValuesEditView()
    }

    private fun setValuesEditView() {
        if (intent.hasExtra(keyContact)) {
            contact = intent.getParcelableExtra(keyContact)!!
//            firstNameField.text = "teste"-- > ERROR!!!
            firstNameField.setText(contact.firstName)
            lastNameField.setText(contact.lastName)
            phoneNumberField.setText(contact.phoneNumber)
            emailAddressField.setText(contact.emailAddress)
            addressField.setText(contact.address)
            birthdayField.setText(contact.birthday)
            title = ""
            buttonDeleteContact.isVisible = true
        }
    }

    private fun initializationComponents() {
        firstNameField = findViewById(R.id.et_first_name)
        lastNameField = findViewById(R.id.et_last_name)
        phoneNumberField = findViewById(R.id.et_add_phone)
        emailAddressField = findViewById(R.id.et_add_email)
        addressField = findViewById(R.id.et_add_address)
        birthdayField = findViewById(R.id.et_add_birthday)
        buttonDeleteContact = findViewById(R.id.bt_delete)
        buttonDeleteContact.isVisible = false
    }

    private fun configureMenuDone() {
        if (!buttonDeleteContact.isVisible) {
            contact = Contact(
                firstName = firstNameField.text.toString(),
                lastName = lastNameField.text.toString(),
                phoneNumber = phoneNumberField.text.toString(),
                emailAddress = emailAddressField.text.toString(),
                address = addressField.text.toString(),
                birthday = birthdayField.text.toString()
            )
            returnContact(resultCodeSave)
        }
        else {
            contact.run {
            firstName = firstNameField.text.toString()
                lastName = lastNameField.text.toString()
                phoneNumber = phoneNumberField.text.toString()
                emailAddress = emailAddressField.text.toString()
                address = addressField.text.toString()
                birthday = birthdayField.text.toString()
            }
            returnContact(resultCodeEdit)
        }
    }

    private fun configureButtonDeleteContact() {
        buttonDeleteContact.setOnClickListener {
            configureAlertDialogDeleteContact(it)
        }
    }

    private fun configureAlertDialogDeleteContact(view: View) {
        MaterialAlertDialogBuilder(view.context)
            .setTitle(resources.getString(R.string.alert_dialog_remove_contact_tittle))
            .setPositiveButton(resources.getString(R.string.menu_remove_delete)) { _, _ ->
                returnContact(resultCodeRemove)
                setResult(resultCodeRemove, Intent().putExtra(keyContact, contact))
                finish()
            }
            .setNegativeButton(resources.getString(R.string.menu_remove_cancel)) { _, _ ->
            }
            .show()
    }

    private fun returnContact(code: Int) {
        setResult(
            Activity.RESULT_OK,
            Intent().putExtra(keyContact, contact).putExtra(keyResultCodeContact, code)
        )
        finish()
    }


}
