package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="DECODE_outreach",group="")
public class DecodeTeleop extends LinearOpMode {
    public boolean lightsOrange = false;
    public boolean lightsLocked = false;
    public boolean flywheelRunning = false;
    public boolean direction = !true;
    public double flywheelPower = 1;
    public boolean flywheelLocked = true;
    public double timeeee = 0;
    @Override
    public void runOpMode() {
        // INIT PRESSED: declare & set up everything
        Robot.set(hardwareMap,this);

        Servo light1 = Servos.create("leftlight");
        Servo light2 = Servos.create("rightlight");

        Servo blocker = Servos.create("blocker");
        Servos.changeRangeOfMotion(blocker,0,.4,false);
        blocker.setPosition(1);

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

        ElapsedTime timer = new ElapsedTime();

        waitForStart();
        if (opModeIsActive()) {
            lightsOrange = changeLights(light1,light2,!lightsOrange);
        }
        while (opModeIsActive()) {
            // configure controls
            Wheels.TeleOpDrive(gamepad1,.25);
            if (gamepad2.crossWasPressed() && !lightsLocked) { lightsOrange = changeLights(light1,light2,lightsOrange); }

            flywheelLocked=false;
            if (gamepad1.circleWasPressed() && !flywheelLocked) { flywheelRunning = changeFlywheel(flywheel,!flywheelRunning,flywheelPower); }
            /*
            if (gamepad1.circleWasPressed()) {
                flywheelLocked = !flywheelLocked;
                flywheelRunning = changeFlywheel(flywheel,false,flywheelPower);
            }*/
            if (gamepad1.leftBumperWasPressed()) { flywheelPower -= .1; }
            if (gamepad1.rightBumperWasPressed()) { flywheelPower += .1; }
            if (gamepad1.squareWasPressed()) {
                boolean check = (blocker.getPosition() > 0.5);
                blocker.setPosition((check) ? 0 : 1);
                if (check) {
                    new Thread(() -> {
                        while (blocker.getPosition()>0.5) {}
                        timer.reset();
                        while (blocker.getPosition()<0.5) {}
                        timeeee= timer.seconds();
                    }).start();
                }
            }
            if (gamepad1.crossWasPressed()) {
                blocker.setPosition(0);
                timer.reset();
                while (timer.seconds() < .275) {}
                blocker.setPosition(1);
            }
            /*
            .2679
            .3174
            .3595
            */
            telemetry.addData("Flywheel power",(flywheelRunning) ? flywheelPower : "OFF");
            telemetry.addData("Flywheels locked",flywheelLocked);
            telemetry.addData("Lights locked",lightsLocked);
            telemetry.addData("Blocker pos",blocker.getPosition());
            telemetry.addData("timeeeeee",timeeee);
            telemetry.update();
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