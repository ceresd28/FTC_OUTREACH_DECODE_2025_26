package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Servos {
    public static Servo create(String name) {
        Servo part = Robot.hwMap.get(Servo.class,name);
        Robot.servos.add(part);
        return part;
    }
    public static void runTo(Servo servo, double targetPos) {
        servo.setPosition(targetPos);
    }
    public static void runToIncrement(Servo servo, double targetPosToAdd) {
        runTo(servo,servo.getPosition()+targetPosToAdd);
    }
    public static void changeRangeOfMotion(Servo servo, boolean forward) {
        Servo.Direction dir;
        if (forward) { dir = Servo.Direction.FORWARD; }
        else { dir = Servo.Direction.REVERSE; }
        servo.setDirection(dir);
    }
    public static void changeRangeOfMotion(Servo servo, double min, double max, boolean forward) {
        servo.scaleRange(min,max);
        changeRangeOfMotion(servo,forward);
    }
}
