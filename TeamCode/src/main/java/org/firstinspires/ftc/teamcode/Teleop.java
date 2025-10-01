package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Disabled
@TeleOp(name="OUTREACH_INTO_THE_DEEP",group="")
public class Teleop extends LinearOpMode {
    public boolean lightsOrange = false;
    public boolean canPickUpFromGround = false;
    public boolean wallPosition = true;
    public boolean kidMode = false;
    /*
    circle = hang and go to wall OR hover
    square = hang and ground position [DISABLED]
    cross/X = open or close claw
    left bumper = turn lights on or off
     */
    public ElapsedTime timer;
    @Override
    public void runOpMode() {
        // INIT PRESSED: declare & set up everything
        Robot.set(hardwareMap,this);

        Servo claw = Servos.create("claw"); /* open=1, closed=0 */
        Servos.changeRangeOfMotion(claw,0.4,0.8,false);
        double clawPos = 1;

        Servo wrist = Servos.create("wrist"); /* faceup=0, halfway=.5, facedown=1 */
        Servos.changeRangeOfMotion(wrist,0,.8,true);

        /* ground=1, bar=.65, wall=0 */
        Servo elbow1 = Servos.create("leftelbow");
        Servo elbow2 = Servos.create("rightelbow");
        Servos.changeRangeOfMotion(elbow1,0.07,1,true);
        Servos.changeRangeOfMotion(elbow2,0.05,1,true);

        /* orange=.3, purple=.7 */
        Servo light1 = Servos.create("leftlight");
        Servo light2 = Servos.create("rightlight");

        light1.setPosition(0.2);
        light2.setPosition(0.2);

        retrieveFromWall(claw,elbow1,elbow2,wrist);

        timer = new ElapsedTime();

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
        if (opModeIsActive()) {
            lightsOrange = changeLights(light1,light2,!lightsOrange);
        }
        while (opModeIsActive()) {
            // configure controls
            Wheels.TeleOpDrive((kidMode) ? .075 : .1);
            if (gamepad1.circle) {
                if (wallPosition) {
                    wallPosition = false;
                    hoverSpecimen(claw, elbow1, elbow2, wrist);
                } else {
                    wallPosition = true;

                    timer.reset();
                    hangSpecimen(claw, elbow1, elbow2, wrist);
                    while (timer.seconds() < 0.5) {}
                    claw.setPosition(1);
                    retrieveFromWall(claw, elbow1, elbow2, wrist);
                }
            }
            if (gamepad1.squareWasPressed() && canPickUpFromGround) {
                retrieveFromGround(claw, elbow1, elbow2, wrist); // disabled
            }
            if (gamepad1.rightBumperWasPressed()) { kidMode = true; }

            new Thread(() -> {
                // should this be used?
            }).start(); // this runs async so wheels can turn while doing action

            if (gamepad1.crossWasPressed()) {
                if (clawPos == 0) {
                    clawPos = 1;
                } else {
                    clawPos = 0;
                }
                claw.setPosition(clawPos);
            }
            if (gamepad1.leftBumperWasPressed()) {
                lightsOrange = changeLights(light1,light2,lightsOrange);
            }

            // send telemetry data
            telemetry.addData("claw", claw.getPosition());
            telemetry.addData("wrist", wrist.getPosition());
            telemetry.addData("elbow1", elbow1.getPosition());
            telemetry.addData("elbow2", elbow2.getPosition());
            telemetry.addData("wheel",Wheels.topLeft.getCurrentPosition());
            telemetry.addData("lights color", (lightsOrange) ? "orange" : "purple");
            telemetry.addData("timer", timer.seconds());

            telemetry.update();
        }
    }
    public static boolean changeLights(Servo light1,Servo light2, boolean lightsOrange) {
        if (lightsOrange) {
            light1.setPosition(0.7);
            light2.setPosition(0.7);
        } else {
            light1.setPosition(0.3);
            light2.setPosition(0.3);
        }
        return !lightsOrange;
    }
    public static void retrieveFromWall(Servo claw,Servo elbow1,Servo elbow2,Servo wrist) {
        claw.setPosition(1);
       // Robot.waitt(1);
        elbow1.setPosition(0);
        //elbow2.setPosition(0);
        Robot.waitt(.2);
        // remember wait secs for elbow1 and elbow2 cant be both above 0! then will be offset and break
        // maybe one first one 0?
        wrist.setPosition(0);
    }
    public static void retrieveFromGround(Servo claw,Servo elbow1,Servo elbow2,Servo wrist) {
        claw.setPosition(1);
        Robot.waitt(1);
        elbow1.setPosition(1);
        //elbow2.setPosition(1);
        Robot.waitt(0.5);
        // remember wait secs for elbow1 and elbow2 cant be both above 0! then will be offset and break
        // maybe one first one 0?
        wrist.setPosition(1);
    }
    public static void hoverSpecimen(Servo claw,Servo elbow1,Servo elbow2,Servo wrist) {
        claw.setPosition(0);
        Robot.waitt(1);
        double hoverNum = 0.45;
        elbow1.setPosition(hoverNum);
        //elbow2.setPosition(hoverNum+0.025);
        Robot.waitt(1);
        wrist.setPosition(1);
        Robot.waitt(1.5);
    }
    public static void hangSpecimen(Servo claw,Servo elbow1,Servo elbow2,Servo wrist) {
        wrist.setPosition(1);
        //Robot.waitt(1.5);
        double hangNum = 0.6;
        elbow1.setPosition(hangNum);
        //elbow2.setPosition(hangNum);
        //Robot.waitt(0.5);
        //claw.setPosition(0);
       // Robot.waitt(0.5);
    }
}