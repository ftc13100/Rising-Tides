package org.firstinspires.ftc.teamcode.opModes.teleOp

import android.widget.Button
import com.arcrobotics.ftclib.command.CommandOpMode
import com.arcrobotics.ftclib.gamepad.GamepadEx
import com.arcrobotics.ftclib.gamepad.GamepadKeys
import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.commands.IntakeCommand
import org.firstinspires.ftc.teamcode.commands.SlidesCommand
import org.firstinspires.ftc.teamcode.constants.ControlBoard
import org.firstinspires.ftc.teamcode.subsystems.elevator.SlidesSubsystem
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSubsystem

@TeleOp
class MainTeleOp: CommandOpMode() {
    private lateinit var intakeMotor: Motor
    private lateinit var conveyorMotor: Motor
    private lateinit var leftMotor: Motor
    private lateinit var rightMotor: Motor

    private lateinit var intakeSubsystem: IntakeSubsystem
    private lateinit var slidesSubsystem: SlidesSubsystem

    private lateinit var intakeCommand: IntakeCommand
    private lateinit var outtakeCommand: IntakeCommand
    private lateinit var upCommand: SlidesCommand
    private lateinit var downCommand: SlidesCommand

    private lateinit var driver: GamepadEx
    private lateinit var operator: GamepadEx
    override fun initialize() {
        intakeMotor = Motor(hardwareMap, ControlBoard.INTAKE.deviceName)
        conveyorMotor = Motor(hardwareMap, ControlBoard.CONVEYOR.deviceName)

        rightMotor = Motor(hardwareMap, ControlBoard.SLIDES_RIGHT.deviceName)
        leftMotor = Motor(hardwareMap, ControlBoard.SLIDES_LEFT.deviceName)

        intakeSubsystem = IntakeSubsystem(intakeMotor, conveyorMotor)
        slidesSubsystem = SlidesSubsystem(rightMotor, leftMotor)


        intakeCommand = IntakeCommand(intake = true, intakeSubsystem)
        outtakeCommand = IntakeCommand(intake = false, intakeSubsystem)
        upCommand = SlidesCommand(slides = true, slidesSubsystem)
        downCommand = SlidesCommand(slides = false, slidesSubsystem)

        driver = GamepadEx(gamepad1)
        operator = GamepadEx(gamepad2)

        operator.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whileHeld(intakeCommand)
        operator.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whileHeld(outtakeCommand)
        operator.getGamepadButton(GamepadKeys.Button.DPAD_UP).whileHeld(upCommand)
        operator.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whileHeld(downCommand)
    }
}