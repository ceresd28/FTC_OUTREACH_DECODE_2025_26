package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotorEx;

@Disabled
@TeleOp(name="Goose_Teleop",group="")
public class PigeonTeleop extends LinearOpMode {
    public boolean flywheelRunning = false;
    public int direction = 1;
    @Override
    public void runOpMode() {
        // INIT PRESSED: declare & set up everything
        Robot.set(hardwareMap,this);

        /*
        Servo light1 = Servos.create("leftlight");
        Servo light2 = Servos.create("rightlight");
        light1.setPosition(0.7);
        light2.setPosition(0.7);
*/
        DcMotorEx flywheel1 = Motors.create("bl",true);
        DcMotorEx flywheel2 = Motors.create("br",true);
        Motors.changeRangeOfMotion(flywheel1,false);
        changeFlywheel(flywheel1,flywheel2,direction,flywheelRunning);

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

            if (gamepad1.circleWasPressed()) { flywheelRunning = changeFlywheel(flywheel1,flywheel2,direction,flywheelRunning); }
            //if (gamepad1.leftBumperWasPressed()) { direction *= -1; }

            telemetry.addData("Direction?",direction);
            telemetry.addData("Flywheels running?",flywheelRunning);
            telemetry.update();
        }
    }
    public static boolean changeFlywheel(DcMotorEx flywheel1, DcMotorEx flywheel2, int direction, boolean flywheelRunning) {
        Motors.runControlled(flywheel1,(flywheelRunning) ? direction : 0);
        Motors.runControlled(flywheel2,(flywheelRunning) ? direction : 0);
        return !flywheelRunning;
    }
}