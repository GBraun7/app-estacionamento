package com.braun.gustavo.estacionamento.utils

import android.content.Context
import android.content.Intent

class BroadcastVeiculosEstacionados(val context: Context) {

    companion object {
        const val updateVeiculosEstacionados = "update-veiculos_estacionados"
    }

    fun emitiBroadcastVeiculoEstacionado() {
        val intent = Intent()
        intent.action = updateVeiculosEstacionados
        context.sendBroadcast(intent)
    }
}