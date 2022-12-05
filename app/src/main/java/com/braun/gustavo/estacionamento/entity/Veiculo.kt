package com.braun.gustavo.estacionamento.entity

import android.content.Context

abstract class Veiculo {

    var modelo: String = ""
    var placa: String = ""

    abstract fun estacionarVeiculo(context: Context)

    abstract fun removerVeiculo(context: Context)

    abstract fun tipoVeiculo(): String
}