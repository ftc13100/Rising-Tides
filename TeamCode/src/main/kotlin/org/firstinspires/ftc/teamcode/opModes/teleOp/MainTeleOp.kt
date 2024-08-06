package org.firstinspires.ftc.teamcode.opModes.teleOp

import com.arcrobotics.ftclib.command.CommandOpMode
import com.arcrobotics.ftclib.command.ConditionalCommand
import com.arcrobotics.ftclib.command.InstantCommand
import com.arcrobotics.ftclib.command.PerpetualCommand
import com.arcrobotics.ftclib.command.RunCommand
import com.arcrobotics.ftclib.gamepad.GamepadEx
import com.arcrobotics.ftclib.gamepad.GamepadKeys
import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.TouchSensor
import org.firstinspires.ftc.teamcode.commands.IntakeCommand
import org.firstinspires.ftc.teamcode.commands.slides.SpinUpCommand
import org.firstinspires.ftc.teamcode.commands.drive.DriveCommand
import org.firstinspires.ftc.teamcode.commands.slides.SpinDownCommand
import org.firstinspires.ftc.teamcode.constants.ControlBoard
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive
import org.firstinspires.ftc.teamcode.subsystems.drive.DriveSubsystem
import org.firstinspires.ftc.teamcode.subsystems.elevator.SlidesSubsystem
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSubsystem
import org.firstinspires.ftc.teamcode.subsystems.pixelBin.BinSubsystem

@TeleOp
class MainTeleOp: CommandOpMode() {
    private lateinit var intakeMotor: Motor
    private lateinit var conveyorMotor: Motor
    private lateinit var slidesLeft: Motor
    private lateinit var slidesRight: Motor
    private lateinit var binServo: Servo
    private lateinit var slidesTouch: TouchSensor

    private lateinit var intakeSubsystem: IntakeSubsystem
    private lateinit var slidesSubsystem: SlidesSubsystem
    private lateinit var driveSubsystem: DriveSubsystem
    private lateinit var binSubsystem: BinSubsystem

    private lateinit var intakeCommand: IntakeCommand
    private lateinit var outtakeCommand: IntakeCommand
    private lateinit var spinUpCommand: SpinUpCommand
    private lateinit var spinDownCommand: SpinDownCommand
    private lateinit var driveCommand: DriveCommand
    private lateinit var binCommand: ConditionalCommand

    private lateinit var driver: GamepadEx
    private lateinit var operator: GamepadEx
    override fun initialize() {
        driver = GamepadEx(gamepad1)
        operator = GamepadEx(gamepad2)

        intakeMotor = Motor(hardwareMap, ControlBoard.INTAKE.deviceName)
        conveyorMotor = Motor(hardwareMap, ControlBoard.CONVEYOR.deviceName)
        slidesRight = Motor(hardwareMap, ControlBoard.SLIDES_RIGHT.deviceName)
        slidesLeft = Motor(hardwareMap, ControlBoard.SLIDES_LEFT.deviceName)
        slidesTouch = hardwareMap.get(TouchSensor::class.java, ControlBoard.SLIDES_TOUCH.deviceName)
        binServo = hardwareMap.get(Servo::class.java, ControlBoard.SERVO.deviceName)

        intakeSubsystem = IntakeSubsystem(intakeMotor, conveyorMotor)
        slidesSubsystem = SlidesSubsystem(slidesRight, slidesLeft, slidesTouch)
        driveSubsystem = DriveSubsystem(SampleMecanumDrive(hardwareMap), false)
        binSubsystem = BinSubsystem(binServo)

        intakeCommand = IntakeCommand(intake = true, intakeSubsystem)
        outtakeCommand = IntakeCommand(intake = false, intakeSubsystem)
        spinUpCommand = SpinUpCommand(slidesSubsystem)
        spinDownCommand = SpinDownCommand(slidesSubsystem)
        driveCommand = DriveCommand(driveSubsystem, driver::getRightX, driver::getLeftX, driver::getLeftY)
        binCommand = ConditionalCommand(
                InstantCommand({ binSubsystem.goingBack() }),
                InstantCommand({ binSubsystem.goingIn() }),
                binSubsystem::isBack
        )

        operator.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whileHeld(intakeCommand)
        operator.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whileHeld(outtakeCommand)
        operator.getGamepadButton(GamepadKeys.Button.DPAD_UP).whileHeld(spinUpCommand)
        operator.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whileHeld(spinDownCommand)
        operator.getGamepadButton(GamepadKeys.Button.X).whileHeld(binCommand)
        operator.getGamepadButton(GamepadKeys.Button.Y).whileHeld(binCommand)

        register(driveSubsystem)

        driveSubsystem.defaultCommand = driveCommand

        PerpetualCommand(
            RunCommand({
                telemetry.addData("LeftX: ", driver.leftX)
                telemetry.addData("LeftY: ", driver.leftY)
                telemetry.addData("RightX: ", driver.rightX)

                telemetry.update()
            })
        ).schedule()

    }
}