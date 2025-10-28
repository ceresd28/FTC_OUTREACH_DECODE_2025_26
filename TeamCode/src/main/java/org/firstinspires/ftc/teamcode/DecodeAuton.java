package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="DECODE_outreach_RED",group="")
public class DecodeAuton extends LinearOpMode {
    public boolean lightsOrange = false;
    public boolean flywheelRunning = false;
    public boolean direction = !true;
    public double flywheelPower = .8;
    @Override
    public void runOpMode() {
        // INIT PRESSED: declare & set up everything
        Robot.set(hardwareMap,this);

        Servo light1 = Servos.create("leftlight");
        Servo light2 = Servos.create("rightlight");

        Servo blocker = Servos.create("blocker");
        Servos.changeRangeOfMotion(blocker,0,1,true);
        blocker.setPosition(0);

        DcMotorEx flywheel = Motors.create("flywheel",true);
        Motors.changeRangeOfMotion(flywheel,direction);
        flywheelRunning = changeFlywheel(flywheel,flywheelRunning,flywheelPower);

        Wheels.create(
                "fl",false,
                "fr",true,
                "bl",false,
                "br",true,
                true);

        telemetry.addData("Status","Initialized");
        telemetry.update();

        waitForStart();
        if (opModeIsActive()) {
            lightsOrange = changeLights(light1,light2,!lightsOrange);
            new Thread(() -> {
                ElapsedTime lightTimer = new ElapsedTime();
                lightTimer.reset();
                while (opModeIsActive()) {
                    if (lightTimer.seconds() % 4 == 0) { lightsOrange = changeLights(light1,light2,lightsOrange); }
                }
            }).start();
            for (DcMotorEx motor:Wheels.wheels) {
                Motors.runTo(motor,1000,.2,3);
            }
        }
    }
    public static boolean changeFlywheel(DcMotorEx flywheel, boolean flywheelRunning, double flywheelPower) {
        Motors.runControlled(flywheel,(flywheelRunning) ? flywheelPower : 0);
        return flywheelRunning;
    }
    public static boolean changeLights(Servo light1,Servo light2, boolean lightsOrange) {
        lightsOrange = !lightsOrange;
        if (lightsOrange) {
            light1.setPosition(0.3);
            light2.setPosition(0.3);
        } else {
            light1.setPosition(0.7);
            light2.setPosition(0.7);
        }
        return lightsOrange;
    }
}