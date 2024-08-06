package org.firstinspires.ftc.teamcode.opModes.teleOp

import com.arcrobotics.ftclib.command.CommandOpMode
import com.arcrobotics.ftclib.gamepad.GamepadEx
import com.arcrobotics.ftclib.gamepad.GamepadKeys
import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.commands.IntakeCommand
import org.firstinspires.ftc.teamcode.commands.SlidesCommand
import org.firstinspires.ftc.teamcode.commands.drive.DriveCommand
import org.firstinspires.ftc.teamcode.constants.ControlBoard
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive
import org.firstinspires.ftc.teamcode.subsystems.drive.DriveSubsystem
import org.firstinspires.ftc.teamcode.subsystems.elevator.SlidesSubsystem
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSubsystem

@TeleOp
class MainTeleOp: CommandOpMode() {
    private lateinit var intakeMotor: Motor
    private lateinit var conveyorMotor: Motor
    private lateinit var slidesLeft: Motor
    private lateinit var slidesRight: Motor

    private lateinit var intakeSubsystem: IntakeSubsystem
    private lateinit var slidesSubsystem: SlidesSubsystem
    private lateinit var driveSubsystem: DriveSubsystem

    private lateinit var intakeCommand: IntakeCommand
    private lateinit var outtakeCommand: IntakeCommand
    private lateinit var spinUpCommand: SlidesCommand
    private lateinit var spinDownCommand: SlidesCommand
    private lateinit var driveCommand: DriveCommand

    private lateinit var driver: GamepadEx
    private lateinit var operator: GamepadEx
    override fun initialize() {
        driver = GamepadEx(gamepad1)
        operator = GamepadEx(gamepad2)

        intakeMotor = Motor(hardwareMap, ControlBoard.INTAKE.deviceName)
        conveyorMotor = Motor(hardwareMap, ControlBoard.CONVEYOR.deviceName)
        slidesRight = Motor(hardwareMap, ControlBoard.SLIDES_RIGHT.deviceName)
        slidesLeft = Motor(hardwareMap, ControlBoard.SLIDES_LEFT.deviceName)

        intakeSubsystem = IntakeSubsystem(intakeMotor, conveyorMotor)
        slidesSubsystem = SlidesSubsystem(slidesRight, slidesLeft)
        driveSubsystem = DriveSubsystem(SampleMecanumDrive(hardwareMap), false)

        intakeCommand = IntakeCommand(intake = true, intakeSubsystem)
        outtakeCommand = IntakeCommand(intake = false, intakeSubsystem)
        spinUpCommand = SlidesCommand(slides = true, slidesSubsystem)
        spinDownCommand = SlidesCommand(slides = false, slidesSubsystem)
        driveCommand = DriveCommand(driveSubsystem, driver::getRightX, driver::getLeftX, driver::getLeftY)

        operator.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whileHeld(intakeCommand)
        operator.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whileHeld(outtakeCommand)
        operator.getGamepadButton(GamepadKeys.Button.DPAD_UP).whileHeld(spinUpCommand)
        operator.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whileHeld(spinDownCommand)

        driveSubsystem.defaultCommand = driveCommand

        register(driveSubsystem)
    }
}