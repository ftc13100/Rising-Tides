package org.firstinspires.ftc.teamcode.subsystems.pixelBin

import com.arcrobotics.ftclib.command.SubsystemBase
import com.qualcomm.robotcore.hardware.Servo

class BinSubsystem (
    private val binServo: Servo
) : SubsystemBase() {
    val outPos = 0.0
    val inPos = 0.8

    fun goingIn() {
        binServo.position = inPos
    }

    fun goingBack() {
        binServo.position = outPos
    }

    fun isBack() = binServo.position == inPos
}

