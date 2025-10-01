package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;

public class Robot {
    public static HardwareMap hwMap;
    public static LinearOpMode opMode;
    public static ArrayList<Servo> servos = new ArrayList<>();
    public static ArrayList<DcMotorEx> motors = new ArrayList<>();
    public static ArrayList<DcMotorEx> wheels = new ArrayList<>();
    public static void set(HardwareMap hardwareMap, LinearOpMode op) {
        hwMap = hardwareMap;
        opMode = op;
    }
    public static void waitt(double secs) {
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        while (
            opMode.opModeIsActive() &&
            timer.seconds() < secs
        ) {
            //opMode.idle();
        }
    }
    public static void waitt(double secs,DcMotorEx motor) {
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        while (
                opMode.opModeIsActive() &&
                timer.seconds() < secs &&
                motor.isBusy()
        ) {
            //opMode.idle();
        }
    }
}
