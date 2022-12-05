package com.braun.gustavo.estacionamento.entity

abstract class Veiculo {

    var modelo: String = ""
    var placa: String = ""

    abstract fun estacionarVeiculo()

    abstract fun removerVeiculo()
}