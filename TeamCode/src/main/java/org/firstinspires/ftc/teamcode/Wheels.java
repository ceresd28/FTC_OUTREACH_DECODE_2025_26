package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import java.util.ArrayList;

public class Wheels {
    public static DcMotorEx topLeft;
    public static DcMotorEx topRight;
    public static DcMotorEx bottomLeft;
    public static DcMotorEx bottomRight;
    public static ArrayList<DcMotorEx> wheels = new ArrayList<>();
    private static double angle, dist;
    public static void create(String tL, boolean tLFlip, String tR, boolean tRFlip, String bL, boolean bLFlip, String bR, boolean bRFlip, boolean rollable) {
        topLeft = Motors.create(tL,rollable);
        topRight = Motors.create(tR,rollable);
        bottomLeft = Motors.create(bL,rollable);
        bottomRight = Motors.create(bR,rollable);
        Robot.wheels.add(topLeft);
        Robot.wheels.add(topRight);
        Robot.wheels.add(bottomLeft);
        Robot.wheels.add(bottomRight);
        Motors.changeRangeOfMotion(topLeft,tLFlip);
        Motors.changeRangeOfMotion(topRight,tRFlip);
        Motors.changeRangeOfMotion(bottomLeft,bLFlip);
        Motors.changeRangeOfMotion(bottomRight,bRFlip);
    }
    public static void move(double x, double y, double r) {
        Motors.runControlled(topLeft,y+x+r);
        Motors.runControlled(topRight,y-x-r);
        Motors.runControlled(bottomLeft,y-x+r);
        Motors.runControlled(bottomRight,y+x-r);
    }
    public static void TeleOpDrive(double percentPwr) {
        double x = Robot.opMode.gamepad1.left_stick_x*1.1;
        double y = -Robot.opMode.gamepad1.left_stick_y;
        double r = Robot.opMode.gamepad1.right_stick_x;

        x*=percentPwr;
        y*=percentPwr;
        r*=percentPwr;

        if (!Robot.opMode.gamepad1.left_stick_button) {
            x*=.9;
            y*=.9;
        }
        if (!Robot.opMode.gamepad1.right_stick_button) {
            r*=.9;
        }
        move(x,y,r);
    }
}
