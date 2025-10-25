package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.List;

@TeleOp(name = "April Tag Test")
public class aprilTag extends LinearOpMode {
    Limelight3A limelight;
    public void runOpMode() throws InterruptedException {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100);
        limelight.start();

        limelight.pipelineSwitch(1); //apriTag pipeline

        while(opModeIsActive()){
            LLResult result = limelight.getLatestResult();

            List<LLResultTypes.FiducialResult> fiducials = result.getFiducialResults();
            for(LLResultTypes.FiducialResult fiducal: fiducials){
                int id = fiducal.getFiducialId();
                if(id==20){
                        telemetry.addData("ID IS:", id);
                        telemetry.update();
                }else if(id == 21){
                    telemetry.addData("ID IS:", id);
                    telemetry.update();
                } else if(id == 22){
                    telemetry.addData("ID IS:", id);
                    telemetry.update();
                } else if(id == 23){
                    telemetry.addData("ID IS:", id);
                    telemetry.update();
                } else if(id == 24){
                    telemetry.addData("ID IS:", id);
                    telemetry.update();
                }else{
                    telemetry.addData("ID IS:", "AAAAAAA");
                    telemetry.update();
                }
            }
        }
    }


}
