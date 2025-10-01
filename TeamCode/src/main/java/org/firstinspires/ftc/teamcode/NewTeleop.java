package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="shooter_test",group="")
public class NewTeleop extends LinearOpMode {
    public boolean flywheelRunning = false;
    @Override
    public void runOpMode() {
        // INIT PRESSED: declare & set up everything
        Robot.set(hardwareMap,this);

        Servo light1 = Servos.create("leftlight");
        Servo light2 = Servos.create("rightlight");
        light1.setPosition(0.7);
        light2.setPosition(0.7);

        DcMotorEx flywheel1 = Motors.create("bl",true);
        DcMotorEx flywheel2 = Motors.create("br",true);
        Motors.changeRangeOfMotion(flywheel2,false);
        changeFlywheel(flywheel1,flywheel2,flywheelRunning);

        /*
        Wheels.create(
                "fl",false,
                "fr",true,
                "bl",false,
                "br",true,
                true);
        */
        telemetry.addData("Status","Initialized");
        telemetry.update();

        waitForStart();
        while (opModeIsActive()) {
            // configure controls
            //Wheels.TeleOpDrive(1);

            if (gamepad1.circleWasPressed()) { flywheelRunning = changeFlywheel(flywheel1,flywheel2,flywheelRunning); }

            telemetry.addData("Flywheels running?",flywheelRunning);
            telemetry.update();
        }
    }
    public static boolean changeFlywheel(DcMotorEx flywheel1, DcMotorEx flywheel2, boolean flywheelRunning) {
        Motors.runControlled(flywheel1,(flywheelRunning) ? 1 : 0);
        Motors.runControlled(flywheel2,(flywheelRunning) ? 1 : 0);
        return !flywheelRunning;
    }
}