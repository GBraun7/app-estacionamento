package com.braun.gustavo.estacionamento.entity

import android.content.Context
import com.braun.gustavo.estacionamento.VeiculoConstants
import com.braun.gustavo.estacionamento.storage.VeiculosEstacionadosRepository

class Van(context: Context) : Veiculo() {

    private var vagaUtilizada: String = VeiculoConstants.VAGA_MOTO
    private var repository = VeiculosEstacionadosRepository(context)

    override fun estacionarVeiculo(veiculo: Veiculo) {
        val veiculosEstacionados = repository.getVeiculosEstacionadosDTO()
        if (veiculosEstacionados.vagas_grande != 0) {
            veiculosEstacionados.vans_estacionadas.add(this)
            veiculosEstacionados.vagas_grande--
            vagaUtilizada = VeiculoConstants.VAGA_GRANDE
        } else if (veiculosEstacionados.vagas_carro >= 3) {
            veiculosEstacionados.vans_estacionadas.add(this)
//            veiculosEstacionados.carros_estacionados.add(veiculo)
            veiculosEstacionados.vagas_carro = -3
            vagaUtilizada = VeiculoConstants.VAGA_CARRO
        } else {
            throw Exception("não ha mais vagas disponiveis")
        }
        repository.storeVeiculosEstacionados(veiculosEstacionados)
    }

    override fun removerVeiculo(veiculo: Veiculo) {
        val veiculosEstacionados = repository.getVeiculosEstacionadosDTO()
        when (vagaUtilizada) {
            VeiculoConstants.VAGA_GRANDE -> {
                veiculosEstacionados.vagas_grande++
                veiculosEstacionados.vans_estacionadas.remove(veiculo)
            }
            VeiculoConstants.VAGA_CARRO -> {
                veiculosEstacionados.vagas_carro = +3
                veiculosEstacionados.carros_estacionados.remove(veiculo)
            }
        }
        repository.storeVeiculosEstacionados(veiculosEstacionados)
    }
}