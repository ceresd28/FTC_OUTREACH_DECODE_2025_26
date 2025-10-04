package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="DECODE_outreach",group="")
public class DecodeTeleop extends LinearOpMode {
    public boolean lightsOrange = false;
    public boolean flywheelRunning = false;
    public double flywheelPower = 0.1;
    @Override
    public void runOpMode() {
        // INIT PRESSED: declare & set up everything
        Robot.set(hardwareMap,this);

        Servo light1 = Servos.create("leftlight");
        Servo light2 = Servos.create("rightlight");

        DcMotorEx flywheel = Motors.create("flywheel",true);
        Motors.changeRangeOfMotion(flywheel,false);
        flywheelRunning = changeFlywheel(flywheel,!flywheelRunning,flywheelPower);

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
        }
        while (opModeIsActive()) {
            // configure controls
            Wheels.TeleOpDrive(1);

            if (gamepad1.circleWasPressed()) { flywheelRunning = changeFlywheel(flywheel,flywheelRunning,flywheelPower); }
            if (gamepad1.crossWasPressed()) { lightsOrange = changeLights(light1,light2,lightsOrange); }

            if (gamepad1.leftBumperWasPressed()) { flywheelPower -= .1; }
            if (gamepad1.rightBumperWasPressed()) { flywheelPower += .1; }

            telemetry.addData("Flywheel power",flywheelPower);
            telemetry.addData("Flywheels running",flywheelRunning);
            telemetry.update();
        }
    }
    public static boolean changeFlywheel(DcMotorEx flywheel, boolean flywheelRunning, double flywheelPower) {
        flywheelRunning = !flywheelRunning;
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