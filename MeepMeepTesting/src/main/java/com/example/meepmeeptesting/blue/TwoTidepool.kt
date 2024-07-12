package com.example.meepmeeptesting.blue

import com.acmerobotics.roadrunner.geometry.Pose2d
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
                    drive.trajectorySequenceBuilder(Pose2d(-35.0, 62.0, (-90.0).toRadians()))

                            .forward(20.0)
                            .waitSeconds(1.0)
                            // Place starfish in tidepool
                            // Push tidepool to Ocean
                            .forward(100.0)
                            .back(100.0)
                            .strafeLeft(23.0)
                            .forward(100.0)

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