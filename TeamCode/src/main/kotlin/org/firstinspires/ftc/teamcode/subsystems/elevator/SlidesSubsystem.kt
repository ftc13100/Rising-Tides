package org.firstinspires.ftc.teamcode.subsystems.elevator

import com.arcrobotics.ftclib.command.SubsystemBase
import com.arcrobotics.ftclib.hardware.motors.Motor
import com.arcrobotics.ftclib.hardware.motors.MotorGroup

class SlidesSubsystem(
    private val leftMotor: Motor,
    private val rightMotor: Motor
) : SubsystemBase() {
    private val elevatorMotors = MotorGroup(leftMotor, rightMotor)
    fun up() {
        elevatorMotors.set(0.5)
}
    fun down() {
        elevatorMotors.set(-0.5)
    }

    fun stop() {
        elevatorMotors.stopMotor()
    }

}