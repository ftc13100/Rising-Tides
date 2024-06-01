package org.firstinspires.ftc.teamcode.commands

import com.arcrobotics.ftclib.command.CommandBase
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSubsystem

class IntakeCommand(
     private val intake: Boolean,
     private val subsystem: IntakeSubsystem
) : CommandBase() {

    override fun execute() {
        if (intake) {
            subsystem.intake()
        }
        else {
            subsystem.outake()
        }
    }

    override fun end(interrupted: Boolean) {
        subsystem.stop()
    }
}
