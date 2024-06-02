package org.firstinspires.ftc.teamcode.opModes.teleOp

import com.arcrobotics.ftclib.command.CommandOpMode
import com.arcrobotics.ftclib.gamepad.GamepadEx
import com.arcrobotics.ftclib.gamepad.GamepadKeys
import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.commands.IntakeCommand
import org.firstinspires.ftc.teamcode.constants.ControlBoard
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSubsystem

@TeleOp
class MainTeleOp: CommandOpMode() {
    private lateinit var intakeMotor: Motor

    private lateinit var intakeSubsystem: IntakeSubsystem

    private lateinit var intakeCommand: IntakeCommand
    private lateinit var outtakeCommand: IntakeCommand

    private lateinit var driver: GamepadEx
    private lateinit var operator: GamepadEx
    override fun initialize() {
//        intakeMotor = Motor(hardwareMap, ControlBoard.INTAKE.deviceName)

//        intakeSubsystem = IntakeSubsystem(intakeMotor)

//        intakeCommand = IntakeCommand(intake = true, intakeSubsystem)
//        outtakeCommand = IntakeCommand(intake = false, intakeSubsystem)

        driver = GamepadEx(gamepad1)
        operator = GamepadEx(gamepad2)

//        driver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whileHeld(intakeCommand)
//        driver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whileHeld(outtakeCommand)
    }
}