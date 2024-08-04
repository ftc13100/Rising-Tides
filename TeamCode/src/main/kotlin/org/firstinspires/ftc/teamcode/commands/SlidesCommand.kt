package org.firstinspires.ftc.teamcode.commands

import com.arcrobotics.ftclib.command.CommandBase
import org.firstinspires.ftc.teamcode.subsystems.elevator.SlidesSubsystem

class SlidesCommand (
    private val slides: Boolean,
    private val subsystem: SlidesSubsystem
) : CommandBase() {

    override fun execute() {
        if (slides) {
            subsystem.up()
        } else {
            subsystem.down()
        }
    }

    override fun end(interrupted: Boolean) {
        subsystem.stop()
    }

}


