package br.com.glrmeslp.github.contacts.models

import android.os.Parcel
import android.os.Parcelable

class Contact(
        var firstName: String = "",
        var lastName: String = "",
        var phoneNumber: String = "",
        var emailAddress: String = "",
        var address: String = "",
        var birthday: String = ""

) : Parcelable {

    var id: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
        id = parcel.readInt()
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(firstName)
        dest?.writeString(lastName)
        dest?.writeString(phoneNumber)
        dest?.writeString(emailAddress)
        dest?.writeString(address)
        dest?.writeString(birthday)
        dest?.writeInt(id)
    }

    override fun toString(): String {
        return when {
            isNotEmpty(firstName) || isNotEmpty(lastName) -> "$firstName $lastName"
            isNotEmpty(phoneNumber) -> phoneNumber
            isNotEmpty(emailAddress) -> emailAddress
            else -> "No Name"
        }
    }

    override fun describeContents(): Int = 0

    private fun isNotEmpty(string: String): Boolean {
        return string != ""
    }

    companion object CREATOR : Parcelable.Creator<Contact> {
        override fun createFromParcel(parcel: Parcel): Contact {
            return Contact(parcel)
        }

        override fun newArray(size: Int): Array<Contact?> {
            return arrayOfNulls(size)
        }
    }
}
