package org.firstinspires.ftc.teamcode.subsystems.intake

import com.arcrobotics.ftclib.command.SubsystemBase
import com.arcrobotics.ftclib.hardware.motors.Motor

class IntakeSubsystem(
        private val intakeMotor: Motor,
        private val conveyorMotor: Motor
) : SubsystemBase() {

    fun intake() {
        intakeMotor.set(1.0)
        conveyorMotor.set(0.3)
    }

    fun outtake() {

        intakeMotor.set(-1.0)
        conveyorMotor.set(-0.3)
    }

    fun stop() {
        intakeMotor.stopMotor()
        conveyorMotor.stopMotor()

    }
}