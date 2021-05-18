package br.com.glrmeslp.github.contacts.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Contact(
    @ColumnInfo(name = "first_name") var firstName: String = "",
    @ColumnInfo(name = "last_name") var lastName: String = "",
    @ColumnInfo(name = "phone_number") var phoneNumber: String = "",
    @ColumnInfo(name = "email_address") var emailAddress: String = "",
    @ColumnInfo(name = "address") var address: String = "",
    @ColumnInfo(name = "birthday") var birthday: String = ""
) : Parcelable {


    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0


    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
        uid = parcel.readInt()
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(firstName)
        dest?.writeString(lastName)
        dest?.writeString(phoneNumber)
        dest?.writeString(emailAddress)
        dest?.writeString(address)
        dest?.writeString(birthday)
        dest?.writeInt(uid)
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
