package io.amntoppo.userphotos.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import io.amntoppo.userphotos.domain.model.Address
import io.amntoppo.userphotos.domain.model.Company
import io.amntoppo.userphotos.utils.JsonParser

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
        fun fromAddresstoJson(address: Address): String {
            return jsonParser.toJson(
                address,
                Address::class.java
            ) ?: "[]"
        }

    @TypeConverter
    fun fromJsontoAddress(json: String): Address? {
        return jsonParser.fromJson<Address>(
            json,
            Address::class.java
        )
    }

    @TypeConverter
    fun fromCompanytoJson(company: Company): String {
        return jsonParser.toJson(
            company,
            Company::class.java
        )?: "[]"
    }

    @TypeConverter
    fun fromJsontoCompany(json: String): Company? {
        return jsonParser.fromJson<Company>(
            json,
            Company::class.java
        )
    }
}