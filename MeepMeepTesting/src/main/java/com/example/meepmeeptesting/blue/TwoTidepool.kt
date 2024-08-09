package com.example.meepmeeptesting.blue

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.geometry.Vector2d
import com.noahbres.meepmeep.MeepMeep
import com.noahbres.meepmeep.core.toRadians
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder
import java.awt.Image
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

object TwoTidepool {
    @JvmStatic

    fun main(args: Array<String>) {

        val meepMeep = MeepMeep(800)
        val myBot = DefaultBotBuilder(meepMeep)
                .setConstraints(52.48291908330528, 52.48291908330528, Math.toRadians(254.6342747656169), Math.toRadians(176.88528), 13.9)
                .followTrajectorySequence { drive ->

//                    val hardwareMap: HardwareMap = null
//                    val binServo = hardwareMap.get(Servo::class.java, Control.SERVO.deviceName)

                    drive.trajectorySequenceBuilder(Pose2d(-35.0, 62.0, (-90.0).toRadians()))

                            .lineToConstantHeading(Vector2d(-13.0, 37.0))
                            .waitSeconds(1.0)
//                            .addDisplacementMarker(20.0) {
//                                binServo.setPosition(0.5)
//                                binServo.setPosition(0.0)
//                          {
                            .back(95.0)
                            .strafeLeft(3.0)
                            .lineToLinearHeading(Pose2d(-17.0,40.0, (-90.0).toRadians()))
                            .strafeRight(40.0)
                            .lineToLinearHeading(Pose2d(-34.0, 40.0, (90.0).toRadians()))
                            .back(95.0)
                            .build()




                }
        var img: Image? = null
        try {
            img = ImageIO.read(File("/Users/ishaanghaskadbi/Desktop/field.png"))
        } catch (_: IOException) {

        }
        if (img != null) {
            meepMeep.setBackground(img)
                    .setDarkMode(true)
                    .setBackgroundAlpha(0.95f)
                    .addEntity(myBot)
                    .start()
        }
    }
}