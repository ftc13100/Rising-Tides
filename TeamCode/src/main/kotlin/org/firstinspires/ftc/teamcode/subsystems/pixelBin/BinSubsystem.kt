package org.firstinspires.ftc.teamcode.subsystems.pixelBin

import com.arcrobotics.ftclib.command.SubsystemBase
import com.qualcomm.robotcore.hardware.Servo

class BinSubsystem (
    private val binServo: Servo

) : SubsystemBase() {
    fun goingIn() {
        binServo.position = 0.5
    }

    fun goingBack() {
        binServo.position = 0.0
    }

    fun isBack() = binServo.position == 0.5
}

