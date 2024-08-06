package org.firstinspires.ftc.teamcode.commands.drive

import com.arcrobotics.ftclib.command.CommandBase
import org.firstinspires.ftc.teamcode.subsystems.drive.DriveSubsystem

class DriveCommand (
    private val subsystem: DriveSubsystem,
    private val rightX: () -> Double,
    private val leftX: () -> Double,
    private val leftY: () -> Double
) : CommandBase() {
    override fun execute() {
        subsystem.drive(
            leftY = leftY.invoke(),
            leftX = leftX.invoke(),
            rightX = rightX.invoke()
        )
    }
}
