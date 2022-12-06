package com.braun.gustavo.estacionamento.storage

import android.content.Context
import com.braun.gustavo.estacionamento.entity.Carro
import com.braun.gustavo.estacionamento.entity.Moto
import com.braun.gustavo.estacionamento.entity.Van
import com.braun.gustavo.estacionamento.entity.Veiculo

class VeiculosEstacionadosRepository(val context: Context) {

    fun getVeiculosEstacionadosDTO(): VeiculosEstacionadosDTO {
        val veiculosEstacionadosDTO = VeiculosEstacionadosStorage(context).get()
        return veiculosEstacionadosDTO
    }

    fun getListVeiculosEstacionados(): ArrayList<Veiculo> {
        val veiculosEstacionadosDTO = VeiculosEstacionadosStorage(context).get()
        val veiculos: ArrayList<Veiculo> = ArrayList()
        for (moto: Moto in veiculosEstacionadosDTO.motos_estacionadas) {
            veiculos.add(moto)
        }
        for (carro: Carro in veiculosEstacionadosDTO.carros_estacionados) {
            veiculos.add(carro)
        }
        for (van: Van in veiculosEstacionadosDTO.vans_estacionadas) {
            veiculos.add(van)
        }
        return veiculos
    }

    fun storeVeiculosEstacionados(veiculos: VeiculosEstacionadosDTO) {
        VeiculosEstacionadosStorage(context).store(veiculos)
    }
}