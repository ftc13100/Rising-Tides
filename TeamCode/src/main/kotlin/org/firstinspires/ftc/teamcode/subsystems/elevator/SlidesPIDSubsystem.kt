package org.firstinspires.ftc.teamcode.subsystems.elevator

import com.arcrobotics.ftclib.controller.PIDFController
import com.arcrobotics.ftclib.hardware.motors.Motor
import com.arcrobotics.ftclib.hardware.motors.MotorGroup
import com.qualcomm.robotcore.hardware.TouchSensor
import org.firstinspires.ftc.teamcode.utils.PIDSubsystem

class SlidesPIDSubsystem(

        slidesLeft: Motor,
        slidesRight: Motor,
        private val slidesTouch: TouchSensor

) : PIDSubsystem(
        controller = PIDFController(0.0, 0.0, 0.0, 0.0)
) {

    private val slidesMotors = MotorGroup(slidesLeft, slidesRight)

    init {
        slidesMotors.inverted = true
    }
    override fun useOutput(output: Double, setpoint: Double) {
        slidesMotors.set(output)
    }

    override fun getMeasurement(): Double = slidesMotors.positions[0]

}