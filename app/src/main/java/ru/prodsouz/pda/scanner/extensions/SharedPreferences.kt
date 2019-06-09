@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package ru.prodsouz.pda.scanner.extensions

import android.content.SharedPreferences
import java.util.*

operator fun SharedPreferences.get(key: String) = this[key, ""].fromJson<Queue<String>>()
operator fun SharedPreferences.get(key: String, defValue: String): String = getString(key, defValue)
operator fun SharedPreferences.get(key: String, defValue: Int): Int = getInt(key, defValue)
operator fun SharedPreferences.get(key: String, defValue: Boolean): Boolean = getBoolean(key, defValue)
operator fun SharedPreferences.get(key: String, defValue: Float): Float = getFloat(key, defValue)
operator fun SharedPreferences.get(key: String, defValue: Long): Long = getLong(key, defValue)
operator fun SharedPreferences.get(key: String, defValue: Set<String>): Set<String> = getStringSet(key, defValue)
inline operator fun <reified T> SharedPreferences.get(key: String): T? = this[key, ""].fromJson<T>()

operator fun SharedPreferences.set(key: String, list: Queue<String>) = set(key, list.toJson)
operator fun SharedPreferences.set(key: String, value: String) = edit().putString(key, value).apply()
operator fun SharedPreferences.set(key: String, value: Int) = edit().putInt(key, value).apply()
operator fun SharedPreferences.set(key: String, value: Boolean) = edit().putBoolean(key, value).apply()
operator fun SharedPreferences.set(key: String, value: Float) = edit().putFloat(key, value).apply()
operator fun SharedPreferences.set(key: String, value: Long) = edit().putLong(key, value).apply()
operator fun SharedPreferences.set(key: String, value: Set<String>) = edit().putStringSet(key, value).apply()
operator fun SharedPreferences.set(key: String, value: Any) = set(key, value.toJson)