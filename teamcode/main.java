package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="leftblue", group="Linear Opmode")
public class main extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    //public ColorSensor colorsensor;
    private DcMotor fleft = null;
    private DcMotor bleft = null;
    private DcMotor fright = null;
    private DcMotor bright = null;
    //private DcMotor rightDrive = null;

    static final double INCREMENT   = 0.01;
    static final int    CYCLE_MS    =   50;
    static final double MAX_POS     =  1.0;
    static final double MIN_POS     =  0.0;

    //Servo   servo;
    //double  position = (MAX_POS - MIN_POS) / 2; // Start at halfway position
    //boolean rampUp = true;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //servo = hardwareMap.get(Servo.class, "servo");

        //float hsvValues[] = {0F,0F,0F};
        //final float values[] = hsvValues;

        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        //color sensor. ignore unless you are in agony
        boolean bPrevState = false;
        boolean bCurrState = false;
        boolean bLedOn = true;

        float feet = 4;
        double scoot = 0.25;
        float rspeed = (float) 1.75; //feet per second

        double power = 0.5;
        double fpower = 0.75;
        int fix = -1;

        //colorsensor = hardwareMap.get(ColorSensor.class, "sensor_color");

        //  setting motors and such
        fleft  = hardwareMap.get(DcMotor.class, "fleft");
        bleft  = hardwareMap.get(DcMotor.class, "bleft");
        fright  = hardwareMap.get(DcMotor.class, "fright");
        bright  = hardwareMap.get(DcMotor.class, "bright");

        //presses start
        waitForStart();
        sleep(1000);
        runtime.reset();

        while(opModeIsActive() && (runtime.seconds() < ((2 - scoot )/ rspeed))) {
            //FORWARD 2
            fleft.setDirection(DcMotor.Direction.REVERSE);
            bleft.setDirection(DcMotor.Direction.REVERSE);
            fright.setDirection(DcMotor.Direction.FORWARD);
            bright.setDirection(DcMotor.Direction.FORWARD);

            fleft.setPower(fpower);
            bleft.setPower(power*fix);
            fright.setPower(fpower);
            bright.setPower(power*fix);
        }
        while(opModeIsActive() && (runtime.seconds() >= ((2 - scoot )/ rspeed))){
            fleft.setPower(0);
            bleft.setPower(0);
            fright.setPower(0);
            bright.setPower(0);
        }

        sleep(500);
        runtime.reset();

        while(opModeIsActive() && (runtime.seconds() < (0.5))) {
            //TURNGE LEFT
            fleft.setDirection(DcMotor.Direction.FORWARD);
            bleft.setDirection(DcMotor.Direction.FORWARD);
            fright.setDirection(DcMotor.Direction.FORWARD);
            bright.setDirection(DcMotor.Direction.FORWARD);

            fleft.setPower(fpower*fix);
            bleft.setPower(power);
            fright.setPower(fpower*fix);
            bright.setPower(power);
        }
        while(opModeIsActive() && (runtime.seconds() >= (0.5))){
            fleft.setPower(0);
            bleft.setPower(0);
            fright.setPower(0);
            bright.setPower(0);
        }

        sleep(500);
        runtime.reset();

        while(opModeIsActive() && (runtime.seconds() < ((1 - scoot )/ rspeed))) {
            //FORWARD 1
            fleft.setDirection(DcMotor.Direction.REVERSE);
            bleft.setDirection(DcMotor.Direction.REVERSE);
            fright.setDirection(DcMotor.Direction.FORWARD);
            bright.setDirection(DcMotor.Direction.FORWARD);

            fleft.setPower(fpower);
            bleft.setPower(power*fix);
            fright.setPower(fpower);
            bright.setPower(power*fix);
        }
        while(opModeIsActive() && (runtime.seconds() >= ((1 - scoot )/ rspeed))){
            fleft.setPower(0);
            bleft.setPower(0);
            fright.setPower(0);
            bright.setPower(0);
        }

        sleep(500);
        runtime.reset();

        //DO THE ARM DROP THINGS

        sleep(500);
        runtime.reset();

        while(opModeIsActive() && (runtime.seconds() < (0.5))) {
            //TURNGE RIGHT
            fleft.setDirection(DcMotor.Direction.REVERSE);
            bleft.setDirection(DcMotor.Direction.REVERSE);
            fright.setDirection(DcMotor.Direction.REVERSE);
            bright.setDirection(DcMotor.Direction.REVERSE);

            fleft.setPower(fpower*fix);
            bleft.setPower(power);
            fright.setPower(fpower*fix);
            bright.setPower(power);
        }
        while(opModeIsActive() && (runtime.seconds() >= (0.5))){
            fleft.setPower(0);
            bleft.setPower(0);
            fright.setPower(0);
            bright.setPower(0);
        }

        sleep(500);
        runtime.reset();

        while(opModeIsActive() && (runtime.seconds() < ((3 - scoot )/ rspeed))) {
            //FORWARD 3
            fleft.setDirection(DcMotor.Direction.REVERSE);
            bleft.setDirection(DcMotor.Direction.REVERSE);
            fright.setDirection(DcMotor.Direction.FORWARD);
            bright.setDirection(DcMotor.Direction.FORWARD);

            fleft.setPower(fpower);
            bleft.setPower(power*fix);
            fright.setPower(fpower);
            bright.setPower(power*fix);
        }
        while(opModeIsActive() && (runtime.seconds() >= ((3 - scoot )/ rspeed))){
            fleft.setPower(0);
            bleft.setPower(0);
            fright.setPower(0);
            bright.setPower(0);
        }



        //presses stop
        while (opModeIsActive()) {

            /*bCurrState = gamepad1.x;

            if (bCurrState && (bCurrState != bPrevState))  {
                bLedOn = !bLedOn;
                colorsensor.enableLed(bLedOn);
            }

            bPrevState = bCurrState;
            Color.RGBToHSV(colorsensor.red() * 8, colorsensor.green() * 8, colorsensor.blue() * 8, hsvValues);
*/
            //its BLACK
            /*if(colorsensor.alpha() < 15){
                telemetry.addData("Black", true);
                //drive.setPower(1);
            }
            //this aint color yellow black oooga booga why do they call it an oven when you oven the cold food of out hot eat the food
            else{
                telemetry.addData("this aint color bruh", true);
                //drive.setPower(0);
            }*/
            // lmao matt smellz
            /*telemetry.addData("LED", bLedOn ? "On" : "Off");
            telemetry.addData("Clear", colorsensor.alpha());
            telemetry.addData("Red  ", colorsensor.red());
            telemetry.addData("Green", colorsensor.green());
            telemetry.addData("Blue ", colorsensor.blue());
            telemetry.addData("Hue", hsvValues[0]);*/

           /* relativeLayout.post(new Runnable() {
                public void run() {
                    relativeLayout.setBackgroundColor(colorsensor.red());
                }
            });*/

            telemetry.update();
        }
    }

}
