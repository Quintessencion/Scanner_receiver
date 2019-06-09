package ru.prodsouz.pda.scanner.extensions

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import ru.prodsouz.pda.scanner.extensions.GsonHelper.gson

object GsonHelper {
    val gson by lazy { GsonBuilder().create() }
}

inline val Any.toJson get() = gson.toJson(this)

//do not work with nested generics List<Pair<Int,Int>> for example
inline fun <reified T> String.fromJson() = if (isEmpty()) {
    null
} else {
    try {
        if (T::class.java.genericSuperclass == null) {
            gson.fromJson<T>(this, object : TypeToken<T>() {}.type)
        } else {
            gson.fromJson(this, T::class.java)
        }
    } catch (t: Throwable) {
        null
    }
}