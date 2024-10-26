package org.firstinspires.ftc.teamcode.subsystems.elevator

import com.arcrobotics.ftclib.command.SubsystemBase
import com.arcrobotics.ftclib.hardware.motors.Motor
import com.arcrobotics.ftclib.hardware.motors.MotorGroup
import com.qualcomm.robotcore.hardware.TouchSensor
import com.qualcomm.robotcore.hardware.TouchSensorMultiplexer

class SlidesSubsystem(
    leftMotor: Motor,
    rightMotor: Motor,
    private val slidesTouch: TouchSensor,
) : SubsystemBase() {
    private val elevatorMotors = MotorGroup(leftMotor, rightMotor)

    init {
        elevatorMotors.inverted = true
    }

    val isPressed: Boolean
        get() = slidesTouch.isPressed

    fun up() {
        elevatorMotors.set(1.0)
    }

    fun down() {
        if (!slidesTouch.isPressed) {
            elevatorMotors.set(-1.0)
        }
    }

    fun stop() {
        elevatorMotors.set(0.0)
    }
}