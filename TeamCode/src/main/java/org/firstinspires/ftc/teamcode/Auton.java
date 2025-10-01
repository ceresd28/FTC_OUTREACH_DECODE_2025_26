package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@Autonomous(name="[AUTON]OUTREACH_INTO_THE_DEEP",group="")
public class Auton extends LinearOpMode {
    @Override
    public void runOpMode() {
        // INIT PRESSED: declare & set up everything
        Robot.set(hardwareMap,this);

        /* orange=.3, purple=.7 */
        Servo light1 = Servos.create("leftlight");
        Servo light2 = Servos.create("rightlight");

        light1.setPosition(0.3);
        light2.setPosition(0.7);

        Wheels.create(
                "fl",false,
                "fr",true,
                "bl",false,
                "br",true,
                true);

        telemetry.addData("Status","Initialized");
        telemetry.update();

        waitForStart();
        // START PRESSED & OP MODE BEGINS:
        double num = 0;
        while (opModeIsActive()) {
            // configure controls
            if (num < 6500) {
                Wheels.move(-1,0,0);
                num+=1;
            }

            // send telemetry data
            telemetry.addData("% finished", num/6500*100);
            telemetry.update();
        }
    }
}