package com.braun.gustavo.estacionamento.entity

import android.content.Context
import com.braun.gustavo.estacionamento.VeiculoConstants.VAGA_CARRO
import com.braun.gustavo.estacionamento.VeiculoConstants.VAGA_GRANDE
import com.braun.gustavo.estacionamento.VeiculoConstants.VAGA_MOTO
import com.braun.gustavo.estacionamento.storage.VeiculosEstacionadosRepository

class Moto(context: Context) : Veiculo() {

    @Transient
    private var vagaUtilizada: String = VAGA_MOTO
    @Transient
    private var repository  = VeiculosEstacionadosRepository(context)

    override fun estacionarVeiculo(veiculo: Veiculo) {
        val veiculosEstacionados = repository.getVeiculosEstacionadosDTO()
        if (veiculosEstacionados.vagas_moto != 0) {
            veiculosEstacionados.motos_estacionadas.add(this)
            veiculosEstacionados.vagas_moto--
            vagaUtilizada = VAGA_MOTO
        } else if (veiculosEstacionados.vagas_carro != 0) {
            veiculosEstacionados.motos_estacionadas.add(this)
//            veiculosEstacionados.carros_estacionados.add(this)
            veiculosEstacionados.vagas_carro--
            vagaUtilizada = VAGA_CARRO
        } else if (veiculosEstacionados.vagas_grande != 0) {
            veiculosEstacionados.motos_estacionadas.add(this)
//            veiculosEstacionados.vans_estacionadas.add(this)
            veiculosEstacionados.vagas_grande--
            vagaUtilizada = VAGA_GRANDE
        } else {
            throw Exception("não ha mais vagas disponiveis")
        }
        repository.storeVeiculosEstacionados(veiculosEstacionados)
    }

    override fun removerVeiculo(veiculo: Veiculo) {
        val veiculosEstacionados = repository.getVeiculosEstacionadosDTO()
        when (vagaUtilizada) {
            VAGA_MOTO -> {
                veiculosEstacionados.vagas_moto++
                veiculosEstacionados.motos_estacionadas.remove(this)
//                for (moto: Veiculo in veiculosEstacionados.motos_estacionadas) {
//                    if (moto.placa == veiculo.placa)
//                        veiculosEstacionados.motos_estacionadas.remove(veiculo)
//                }
            }
            VAGA_CARRO -> {
                veiculosEstacionados.vagas_carro++
                veiculosEstacionados.motos_estacionadas.remove(this)
//                veiculosEstacionados.carros_estacionados.remove(this)
            }
            VAGA_GRANDE -> {
                veiculosEstacionados.vagas_grande++
//                veiculosEstacionados.vans_estacionadas.remove(this)
                veiculosEstacionados.motos_estacionadas.remove(this)
            }
        }
        repository.storeVeiculosEstacionados(veiculosEstacionados)
    }
}