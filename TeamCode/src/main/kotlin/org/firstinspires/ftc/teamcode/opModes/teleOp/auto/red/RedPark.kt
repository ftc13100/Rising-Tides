package org.firstinspires.ftc.teamcode.opModes.teleOp.auto.red

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.constants.ControlBoard
import org.firstinspires.ftc.teamcode.roadrunner.drive.DriveConstants
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSubsystem
import org.firstinspires.ftc.teamcode.subsystems.pixelBin.BinSubsystem

@Autonomous
class RedPark : OpMode() {
    private lateinit var drive : SampleMecanumDrive

    private lateinit var bin: BinSubsystem

    private val startPose = Pose2d(-35.0, 62.0, Math.toRadians(90.0))
    private lateinit var path: TrajectorySequence

    override fun init() {
        drive = SampleMecanumDrive(hardwareMap)
        bin = BinSubsystem(
                hardwareMap.get(Servo::class.java, ControlBoard.SERVO.deviceName)
        )

        path = drive.trajectorySequenceBuilder(startPose)
                .back(5.0)
                .strafeRight(18.0)
                .back(20.0,
                        SampleMecanumDrive.getVelocityConstraint(
                                20.0,
                                DriveConstants.MAX_ANG_VEL,
                                DriveConstants.TRACK_WIDTH
                        ),
                        SampleMecanumDrive.getAccelerationConstraint(
                                DriveConstants.MAX_ACCEL
                        )
                )
                .waitSeconds(2.0)
                .back(87.0,
                    SampleMecanumDrive.getVelocityConstraint(
                        20.0,
                        DriveConstants.MAX_ANG_VEL,
                        DriveConstants.TRACK_WIDTH
                    ),
                    SampleMecanumDrive.getAccelerationConstraint(
                        DriveConstants.MAX_ACCEL
                    )
                )
                .addTemporalMarker(2.1) {
                    bin.goingBack()
                }
                .addTemporalMarker(4.6) {
                    bin.goingIn()
                }
                .forward(90.0)
                .turn(Math.toRadians(-90.0))
                .back(20.0)
                .forward(5.0)
                .strafeRight(90.0,
                        SampleMecanumDrive.getVelocityConstraint(
                                20.0,
                                DriveConstants.MAX_ANG_VEL,
                                DriveConstants.TRACK_WIDTH
                        ),
                        SampleMecanumDrive.getAccelerationConstraint(
                                DriveConstants.MAX_ACCEL
                        )
                )
                .build()
    }

    override fun start() {
        drive.poseEstimate = startPose
        drive.followTrajectorySequenceAsync(path)
    }
    override fun loop() {
        drive.update()
    }

}