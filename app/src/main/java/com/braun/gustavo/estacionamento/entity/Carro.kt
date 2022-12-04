package com.braun.gustavo.estacionamento.entity

import android.content.Context
import com.braun.gustavo.estacionamento.VeiculoConstants
import com.braun.gustavo.estacionamento.storage.VeiculosEstacionadosRepository

class Carro(context: Context): Veiculo() {

    @Transient
    private var vagaUtilizada: String = VeiculoConstants.VAGA_CARRO
    @Transient
    private var repository = VeiculosEstacionadosRepository(context)

    override fun estacionarVeiculo(veiculo: Veiculo) {
        val veiculosEstacionados = repository.getVeiculosEstacionadosDTO()
        if (veiculosEstacionados.vagas_carro != 0) {
            veiculosEstacionados.carros_estacionados.add(this)
            veiculosEstacionados.vagas_carro--
            vagaUtilizada = VeiculoConstants.VAGA_CARRO
        } else if (veiculosEstacionados.vagas_grande != 0) {
            veiculosEstacionados.carros_estacionados.add(this)
//            veiculosEstacionados.vans_estacionadas.add(veiculo)
            veiculosEstacionados.vagas_grande--
            vagaUtilizada = VeiculoConstants.VAGA_GRANDE
        } else {
            throw Exception("não ha mais vagas disponiveis")
        }
        repository.storeVeiculosEstacionados(veiculosEstacionados)
    }


    override fun removerVeiculo(veiculo: Veiculo) {
        val veiculosEstacionados = repository.getVeiculosEstacionadosDTO()
        when (vagaUtilizada) {
            VeiculoConstants.VAGA_CARRO -> {
                veiculosEstacionados.vagas_carro++
                veiculosEstacionados.carros_estacionados.remove(this)
            }
            VeiculoConstants.VAGA_GRANDE -> {
                veiculosEstacionados.vagas_grande++
                veiculosEstacionados.carros_estacionados.remove(this)
//                veiculosEstacionados.vans_estacionadas.remove(this)
            }
        }
        repository.storeVeiculosEstacionados(veiculosEstacionados)
    }
}