package org.firstinspires.ftc.teamcode.opModes.teleOp.auto.blue

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence

class BluePark : OpMode () {
    private lateinit var drive : SampleMecanumDrive

    private val startPose = Pose2d(-35.0, 0.0)
    private lateinit var path: TrajectorySequence

    override fun init() {
        drive = SampleMecanumDrive(hardwareMap)

        path = drive.trajectorySequenceBuilder(startPose)
                .forward(120.0)
                .build()
    }

    override fun start() {
        drive.followTrajectorySequenceAsync(path)
    }
    override fun loop() {
        drive.update()
    }
}