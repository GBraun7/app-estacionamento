package com.braun.gustavo.estacionamento.entity

import android.content.Context
import com.braun.gustavo.estacionamento.VeiculoConstants.VAGA_CARRO
import com.braun.gustavo.estacionamento.VeiculoConstants.VAGA_GRANDE
import com.braun.gustavo.estacionamento.VeiculoConstants.VEICULO_TIPO_VAN
import com.braun.gustavo.estacionamento.storage.VeiculosEstacionadosRepository

class Van : Veiculo() {

    private var vagaUtilizada: String = VAGA_GRANDE

    override fun estacionarVeiculo(context: Context) {
        val repository = VeiculosEstacionadosRepository(context)
        val veiculosEstacionados = repository.getVeiculosEstacionadosDTO()
        if (veiculosEstacionados.vagas_grande != 0) {
            veiculosEstacionados.vans_estacionadas.add(this)
            veiculosEstacionados.vagas_grande--
            vagaUtilizada = VAGA_GRANDE
        } else if (veiculosEstacionados.vagas_carro >= 3) {
            veiculosEstacionados.vans_estacionadas.add(this)
            veiculosEstacionados.vagas_carro -= 3
            vagaUtilizada = VAGA_CARRO
        } else {
            throw Exception("Não ha mais vaga disponivel no estacionamento")
        }
        repository.storeVeiculosEstacionados(veiculosEstacionados)
    }

    override fun removerVeiculo(context: Context) {
        val repository = VeiculosEstacionadosRepository(context)
        val veiculosEstacionados = repository.getVeiculosEstacionadosDTO()
        for (van: Veiculo in veiculosEstacionados.vans_estacionadas) {
            if (van.placa == this.placa) {
                veiculosEstacionados.vans_estacionadas.remove(van)
                break
            }
        }
        when (vagaUtilizada) {
            VAGA_GRANDE -> veiculosEstacionados.vagas_grande++
            VAGA_CARRO -> veiculosEstacionados.vagas_carro += 3
        }
        repository.storeVeiculosEstacionados(veiculosEstacionados)
    }

    override fun tipoVeiculo(): String {
        return VEICULO_TIPO_VAN
    }
}