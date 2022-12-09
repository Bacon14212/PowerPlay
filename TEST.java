package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name ="Right Auto")
public class TEST extends LinearOpMode {

    private DcMotor fL;
    private DcMotor fR;
    private DcMotor bL;
    private DcMotor bR;
    private DcMotor Lift;
    private Servo claw;

    private int fLPos;
    private int fRPos;
    private int bLPos;
    private int bRPos;

    final double WHEEL_DIAMETER = 4; //Inches
    final double PULES_PER_ROTATION = 537.7; //Gobilda 435 rpm motor

    @Override
    public void runOpMode() {
        fL = hardwareMap.get(DcMotor.class, "leftFront");
        fR = hardwareMap.get(DcMotor.class, "rightFront");
        bL = hardwareMap.get(DcMotor.class, "leftRear");
        bR = hardwareMap.get(DcMotor.class, "rightRear");

        fL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        fL.setDirection(DcMotorSimple.Direction.REVERSE);
        bL.setDirection(DcMotorSimple.Direction.REVERSE);
        fLPos = 0;
        fRPos = 0;
        bLPos = 0;
        bRPos = 0;


        waitForStart();
        drive(1053,1053,1053,1053,1);
        sleep(1000);
        drive(-1300, 1300, 1300, -1300, 1);

        telemetry.addData("fL: ", fL.getCurrentPosition());
        telemetry.addData("fR: ", fR.getCurrentPosition());
        telemetry.addData("bL: ", bL.getCurrentPosition());
        telemetry.addData("bR: ", bR.getCurrentPosition());
            telemetry.update();

            sleep(1000);

    }
    private void drive(int fLTarget, int fRTarget, int bLTarget, int bRTarget, double speed) {

       fLPos+=fLTarget;
       fRPos+=fRTarget;
       bLPos+=bLTarget;
       bRPos+=bRTarget;
        //150.3 ticks per inch

        fL.setTargetPosition(fLPos);
        fR.setTargetPosition(fRPos);
        bL.setTargetPosition(bLPos);
        bR.setTargetPosition(bRPos);

        fL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        fL.setPower(1);
        fR.setPower(1);
        bL.setPower(1);
        bR.setPower(1);

        while(opModeIsActive() && fL.isBusy() && fR.isBusy() && bL.isBusy() && bR.isBusy()) {
            telemetry.update();
            idle();
        }
    }
}
