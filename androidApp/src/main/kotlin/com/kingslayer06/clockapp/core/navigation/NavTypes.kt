package com.kingslayer06.clockapp.core.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.kingslayer06.clockapp.domain.models.ChessRuleset
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val ChessRulesetType = object : NavType<ChessRuleset>(isNullableAllowed = false) {
    override fun put(bundle: Bundle, key: String, value: ChessRuleset) {
        bundle.putString(key, Json.encodeToString(ChessRuleset.serializer(), value))
    }

    override fun get(bundle: Bundle, key: String): ChessRuleset? {
        return bundle.getString(key)?.let { Json.decodeFromString(ChessRuleset.serializer(), it) }
    }

    override fun parseValue(value: String): ChessRuleset {
        return Json.decodeFromString(ChessRuleset.serializer(), Uri.decode(value))
    }

    override fun serializeAsValue(value: ChessRuleset): String {
        return Uri.encode(Json.encodeToString(ChessRuleset.serializer(), value))
    }
}