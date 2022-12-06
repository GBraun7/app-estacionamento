package com.braun.gustavo.estacionamento.storage

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.lang.reflect.Type
import java.util.concurrent.ConcurrentLinkedQueue

class VeiculosEstacionadosStorage(val context: Context) {

    private var sharedPrefs: SharedPreferences? = null
    private val PREFERENCES_KEY = "app-estacionamento-cache"
    private val CONFIG_INDEX = "veiculos-estacionados"

    init {
        if (sharedPrefs == null)
            sharedPrefs = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)
    }

    @Synchronized
    fun getEditor(): SharedPreferences.Editor {
        return sharedPrefs!!.edit()
    }

    @Synchronized
    fun store(veiculos: VeiculosEstacionadosDTO) {
        getEditor().putString(CONFIG_INDEX, Gson().toJson(veiculos)).commit()
    }

    @Synchronized
    fun get(): VeiculosEstacionadosDTO {
        return Gson().fromJson(
            sharedPrefs!!.getString(CONFIG_INDEX, null),
            VeiculosEstacionadosDTO::class.java
        ) ?: return VeiculosEstacionadosDTO()
    }
}