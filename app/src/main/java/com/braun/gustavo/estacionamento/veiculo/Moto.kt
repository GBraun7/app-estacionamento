package com.braun.gustavo.estacionamento.veiculo

import android.content.Context
import com.braun.gustavo.estacionamento.utils.VeiculoConstants.VAGA_CARRO
import com.braun.gustavo.estacionamento.utils.VeiculoConstants.VAGA_GRANDE
import com.braun.gustavo.estacionamento.utils.VeiculoConstants.VAGA_MOTO
import com.braun.gustavo.estacionamento.utils.VeiculoConstants.VEICULO_TIPO_MOTO
import com.braun.gustavo.estacionamento.storage.VeiculosEstacionadosRepository

class Moto : Veiculo() {

    var vagaUtilizada: String = VAGA_MOTO

    override fun estacionarVeiculo(context: Context) {
        val repository = VeiculosEstacionadosRepository(context)
        val veiculosEstacionados = repository.getVeiculosEstacionadosDTO()
        if (veiculosEstacionados.vagas_moto != 0) {
            veiculosEstacionados.motos_estacionadas.add(this)
            veiculosEstacionados.vagas_moto--
            vagaUtilizada = VAGA_MOTO
        } else if (veiculosEstacionados.vagas_carro != 0) {
            veiculosEstacionados.motos_estacionadas.add(this)
            veiculosEstacionados.vagas_carro--
            vagaUtilizada = VAGA_CARRO
        } else if (veiculosEstacionados.vagas_grande != 0) {
            veiculosEstacionados.motos_estacionadas.add(this)
            veiculosEstacionados.vagas_grande--
            vagaUtilizada = VAGA_GRANDE
        } else {
            throw Exception("Não ha vaga disponivel para esse veiculo")
        }
        repository.storeVeiculosEstacionados(veiculosEstacionados)
    }

    override fun removerVeiculo(context: Context) {
        val repository = VeiculosEstacionadosRepository(context)
        val veiculosEstacionados = repository.getVeiculosEstacionadosDTO()
        when (vagaUtilizada) {
            VAGA_MOTO -> veiculosEstacionados.vagas_moto++
            VAGA_CARRO -> veiculosEstacionados.vagas_carro++
            VAGA_GRANDE -> veiculosEstacionados.vagas_grande++
        }
        for (moto: Veiculo in veiculosEstacionados.motos_estacionadas) {
            if (moto.placa == this.placa){
                veiculosEstacionados.motos_estacionadas.remove(moto)
                break
            }
        }
        repository.storeVeiculosEstacionados(veiculosEstacionados)
    }

    override fun tipoVeiculo(): String {
        return VEICULO_TIPO_MOTO
    }

}