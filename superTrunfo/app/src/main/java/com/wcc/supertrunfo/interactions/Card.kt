package com.wcc.supertrunfo.interactions

import com.wcc.supertrunfo.entities.Driver
import com.wcc.supertrunfo.entities.Player
import com.wcc.supertrunfo.entities.Vehicle

data class Card (
        val vehicle: Vehicle,
        val driver: Driver,
        private val player: Player = Player("player")
) {
    val label: String = "Card ${player.name}"
    val maxVelocity = initMaxVelocity()
    val accelerationTime = vehicle.accelerationTime
    val passengers = initPassengers()
    val xP = initXP()

    private fun initPassengers(): Int { return vehicle.passengers * (1 + driver.defensiveDriving) }

    private fun initAccelerationTime(): Int {
        return vehicle.accelerationTime
    }

    private fun initMaxVelocity(): Int {
       return when( vehicle.type){
                "car" -> maxCarVelocity()
                "motorcycle" -> maxMotorcycleVelocity()
                else  -> maxVelocityBike()
        }
    }

    private fun initXP(): Int {
         return when (vehicle.type) {
            "car" -> driver.carXP
            "motorcycle" -> driver.motorcycleXP
            else -> driver.bikeXP
        }
    }

    private fun maxVelocityBike(): Int { return vehicle.maxAcceleration * driver.boldness }

    private fun maxMotorcycleVelocity(): Int { return 1 / vehicle.weight * vehicle.maxAcceleration }

    private fun maxCarVelocity(): Int{
        return if(vehicle.style == "sedã"){
            vehicle.maxAcceleration
        }else {
            vehicle.maxAcceleration + 10
        }
    }
}