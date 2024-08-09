package org.firstinspires.ftc.teamcode.subsystems.intake

import com.arcrobotics.ftclib.command.SubsystemBase
import com.arcrobotics.ftclib.hardware.motors.Motor

class IntakeSubsystem(
        private val intakeMotor: Motor,
        private val conveyorMotor: Motor
) : SubsystemBase() {

    fun intake() {
        intakeMotor.set(-0.4)
        conveyorMotor.set(1.0)
    }

    fun outtake() {

        intakeMotor.set(0.4)
        conveyorMotor.set(-1.0)
    }

    fun stop() {
        intakeMotor.stopMotor()
        conveyorMotor.stopMotor()

    }
}