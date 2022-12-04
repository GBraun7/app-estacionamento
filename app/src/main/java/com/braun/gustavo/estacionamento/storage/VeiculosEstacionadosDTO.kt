package com.braun.gustavo.estacionamento.storage

import com.braun.gustavo.estacionamento.entity.Carro
import com.braun.gustavo.estacionamento.entity.Moto
import com.braun.gustavo.estacionamento.entity.Van
import com.braun.gustavo.estacionamento.entity.Veiculo

data class VeiculosEstacionadosDTO(
    var vagas_moto: Int = 3,
    var vagas_carro: Int = 10,
    var vagas_grande: Int = 3,
    val carros_estacionados: ArrayList<Carro> = ArrayList(),
    val motos_estacionadas: ArrayList<Moto> = ArrayList(),
    val vans_estacionadas: ArrayList<Van> = ArrayList(),
//    val veiculos: ArrayList<Veiculo> = ArrayList()
)