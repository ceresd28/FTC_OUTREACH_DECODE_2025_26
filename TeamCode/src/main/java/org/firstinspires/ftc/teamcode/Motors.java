package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Motors {
    public static DcMotorEx create(String name, boolean rollable) {
        DcMotorEx part = Robot.hwMap.get(DcMotorEx.class,name);
        Robot.motors.add(part);
        setRoll(part,rollable);
        resetEncoder(part);
        return part;
    }
    public static void changeRangeOfMotion(DcMotorEx motor, boolean forward) {
        DcMotorSimple.Direction dir;
        if (forward) { dir = DcMotorSimple.Direction.FORWARD; }
        else { dir = DcMotorSimple.Direction.REVERSE; }
        motor.setDirection(dir);
    }
    public static void setRoll(DcMotorEx motor, boolean rollable) {
        DcMotorEx.ZeroPowerBehavior zeroPowerBehavior;
        if (rollable) { zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT; } // roll
        else { zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE; } // don't roll
        motor.setZeroPowerBehavior(zeroPowerBehavior);
    }
    public static void resetEncoder(DcMotorEx motor) {
        motor.setPower(0);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public static void runTo(DcMotorEx motor, int targetPos, double power, double maxTimeoutSecs) {
        motor.setTargetPosition(targetPos);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(power);
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        while (
            Robot.opMode.opModeIsActive() &&
            motor.isBusy() &&
            timer.seconds() < maxTimeoutSecs
        ) {
            Robot.opMode.idle();
        }
    }
    public static void runToIncrement(DcMotorEx motor, int targetPosToAdd, double power, double maxTimeoutSecs) {
        runTo(motor, motor.getCurrentPosition()+targetPosToAdd, power, maxTimeoutSecs);
    }
    public static void stopRunning(DcMotorEx motor, double maxTimeoutSecs) {
        runToIncrement(motor,0,0,maxTimeoutSecs);
    }
    public static void runControlled(DcMotorEx motor, double power) {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setPower(power);
    }
    public static void runUncontrolled(DcMotorEx motor, double power) {
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setPower(power);
    }

}
