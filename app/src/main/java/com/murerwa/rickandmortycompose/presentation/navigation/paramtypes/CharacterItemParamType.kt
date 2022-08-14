package com.murerwa.rickandmortycompose.presentation.navigation.paramtypes

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.murerwa.rickandmortycompose.domain.models.characters.CharacterItem

class CharacterItemParamType : NavType<CharacterItem>(
    isNullableAllowed = false
) {
    override fun get(bundle: Bundle, key: String): CharacterItem? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): CharacterItem {
        return Gson().fromJson(value, CharacterItem::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: CharacterItem) {
        bundle.putParcelable(key, value)
    }
}