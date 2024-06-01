package org.firstinspires.ftc.teamcode.subsystems.intake

import com.arcrobotics.ftclib.command.SubsystemBase
import com.arcrobotics.ftclib.hardware.motors.Motor

class IntakeSubsystem(
        private val intakeMotor: Motor
) : SubsystemBase() {

    fun intake() {
        intakeMotor.set(1.0)
    }

    fun outake() {

        intakeMotor.set(-1.0)
    }

    fun stop() {
        intakeMotor.stopMotor()

    }
}