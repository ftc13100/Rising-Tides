package org.firstinspires.ftc.teamcode.roadrunner.drive;

import static org.firstinspires.ftc.teamcode.constants.ControlBoard.ODO_LEFT_ENCODER;
import static org.firstinspires.ftc.teamcode.constants.ControlBoard.ODO_RIGHT_ENCODER;
import static org.firstinspires.ftc.teamcode.constants.ControlBoard.ODO_STRAFE_ENCODER;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.localization.ThreeTrackingWheelLocalizer;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.roadrunner.util.Encoder;

import java.util.Arrays;
import java.util.List;

/*
 * Sample tracking wheel localizer implementation assuming the standard configuration:
 *
 *    /--------------\
 *    |     ____     |
 *    |     ----     |
 *    | ||        || |
 *    | ||        || |
 *    |              |
 *    |              |
 *    \--------------/
 *
 */
@Config
public class StandardTrackingWheelLocalizer extends ThreeTrackingWheelLocalizer {
    public static double TICKS_PER_REV = 2000;
    public static double WHEEL_RADIUS = 1.88976 / 2; // in
    public static double GEAR_RATIO = 1; // output (wheel) speed / input (encoder) speed

    public static double LATERAL_DISTANCE = 17.87374770446561; // in; distance between the left and right wheels
    public static double FORWARD_OFFSET = 0; // in; offset of the lateral wheel
    // 2.6 in

    public static double X_MULTIPLIER = 0.89881492066; // Multiplier in the X direction
    public static double Y_MULTIPLIER = 0.89839420596; // Multiplier in the Y direction

    private final Encoder leftEncoder;
    private final Encoder rightEncoder;
    private final Encoder strafeEncoder;

    public StandardTrackingWheelLocalizer(HardwareMap hardwareMap) {
        super(Arrays.asList(
                new Pose2d(0, LATERAL_DISTANCE / 2, 0), // left
                new Pose2d(0, -LATERAL_DISTANCE / 2, 0), // right
                new Pose2d(FORWARD_OFFSET, 0, Math.toRadians(90)) // front
        ));

        leftEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, ODO_LEFT_ENCODER.getDeviceName()));
        rightEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, ODO_RIGHT_ENCODER.getDeviceName()));
        strafeEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, ODO_STRAFE_ENCODER.getDeviceName()));

        strafeEncoder.setDirection(Encoder.Direction.REVERSE);
    }

    public static double encoderTicksToInches(double ticks) {
        return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV;
    }

    @NonNull
    @Override
    public List<Double> getWheelPositions() {
        return Arrays.asList(
                encoderTicksToInches(leftEncoder.getCurrentPosition()) * X_MULTIPLIER,
                encoderTicksToInches(rightEncoder.getCurrentPosition()) * X_MULTIPLIER,
                encoderTicksToInches(strafeEncoder.getCurrentPosition()) * Y_MULTIPLIER
        );
    }

    @NonNull
    @Override
    public List<Double> getWheelVelocities() {
        return Arrays.asList(
                encoderTicksToInches(leftEncoder.getCorrectedVelocity()) * X_MULTIPLIER,
                encoderTicksToInches(rightEncoder.getCorrectedVelocity()) * X_MULTIPLIER,
                encoderTicksToInches(strafeEncoder.getCorrectedVelocity()) * Y_MULTIPLIER
        );
    }
}
