package com.braun.gustavo.estacionamento

import android.content.Context
import android.content.Intent

class BroadcastVeiculosEstacionados(val context: Context) {

    companion object {
        const val veiculosEstacionados = "estacionar-veiculo"
    }

    fun emitiBroadcastVeiculoEstacionado() {
        val intent = Intent()
        intent.action = veiculosEstacionados
        context.sendBroadcast(intent)
    }
}