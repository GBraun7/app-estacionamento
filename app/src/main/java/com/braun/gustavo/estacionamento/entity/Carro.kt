package com.braun.gustavo.estacionamento.entity

import android.content.Context
import com.braun.gustavo.estacionamento.VeiculoConstants.VAGA_CARRO
import com.braun.gustavo.estacionamento.VeiculoConstants.VAGA_GRANDE
import com.braun.gustavo.estacionamento.VeiculoConstants.VEICULO_TIPO_CARRO
import com.braun.gustavo.estacionamento.storage.VeiculosEstacionadosRepository

class Carro : Veiculo() {

    var vagaUtilizada: String = VAGA_CARRO

    override fun estacionarVeiculo(context: Context) {
        val repository = VeiculosEstacionadosRepository(context)
        val veiculosEstacionados = repository.getVeiculosEstacionadosDTO()
        if (veiculosEstacionados.vagas_carro != 0) {
            veiculosEstacionados.carros_estacionados.add(this)
            veiculosEstacionados.vagas_carro--
            vagaUtilizada = VAGA_CARRO
        } else if (veiculosEstacionados.vagas_grande != 0) {
            veiculosEstacionados.carros_estacionados.add(this)
            veiculosEstacionados.vagas_grande--
            vagaUtilizada = VAGA_GRANDE
        } else {
            throw Exception("Não ha mais vaga disponivel no estacionamento")
        }
        repository.storeVeiculosEstacionados(veiculosEstacionados)
    }


    override fun removerVeiculo(context: Context) {
        val repository = VeiculosEstacionadosRepository(context)
        val veiculosEstacionados = repository.getVeiculosEstacionadosDTO()
        for (carro: Veiculo in veiculosEstacionados.carros_estacionados) {
            if (carro.placa == this.placa) {
                veiculosEstacionados.carros_estacionados.remove(carro)
                break
            }
        }
        when (vagaUtilizada) {
            VAGA_CARRO -> veiculosEstacionados.vagas_carro++
            VAGA_GRANDE -> veiculosEstacionados.vagas_grande++
        }
        repository.storeVeiculosEstacionados(veiculosEstacionados)
    }

    override fun tipoVeiculo(): String {
        return VEICULO_TIPO_CARRO
    }
}