package com.braun.gustavo.estacionamento.entity

abstract class Veiculo {

    var modelo: String = ""
    var placa: String = ""

    fun Veiculo(modelo: String, placa: String) {
        this.modelo = modelo
        this.placa = placa
    }

    abstract fun estacionarVeiculo(veiculo: Veiculo)

    abstract fun removerVeiculo(veiculo: Veiculo)
}