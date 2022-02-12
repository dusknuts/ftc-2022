package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static java.lang.String.*;

@TeleOp(name="newdrive", group="Linear Opmode")
public class drive extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    public ColorSensor colorsensor;
    private DcMotor fleft = null;
    private DcMotor bleft = null;
    private DcMotor fright = null;
    private DcMotor bright = null;

    private DcMotor actuator = null;
    private DcMotor duck = null;
    Servo lalle;
    Servo ralle;
    double lpos = 0.5;
    double rpos = 0.5;
    double rate = 0.004;
    Servo armguy;
    double armpos = 0.5;
    double armrate = 0.004;

    private DcMotor vacuumsama = null;
    //private DcMotor rightDrive = null;

    static final double INCREMENT   = 0.01;
    static final int    CYCLE_MS    =   50;
    static final double MAX_POS     =  1.0;
    static final double MIN_POS     =  0.0;

    int fix = -1;

    //Servo   servo;
    //double  position = (MAX_POS - MIN_POS) / 2; // Start at halfway position
    //boolean rampUp = true;


    // "my balls look like armadillos" -Sebby 2021 (he realy said that u guys im serios :(((( pls i am not lying guyszzz)
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        lalle = hardwareMap.get(Servo.class, "lalle");
        ralle = hardwareMap.get(Servo.class, "ralle");
        armguy = hardwareMap.get(Servo.class, "armguy");


        float hsvValues[] = {0F,0F,0F};
        final float values[] = hsvValues;

        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        //color sensor stuff ignore unless you are in agony
        boolean bPrevState = false;
        boolean bCurrState = false;
        boolean bLedOn = true;

        //le speed
        double duckpower = 0.8;
        double power = 0.5;
        double fpower = 0.75;
        double vpower = 0.25;

        //colorsensor = hardwareMap.get(ColorSensor.class, "sensor_color");

        //  setting motors and such
        fleft  = hardwareMap.get(DcMotor.class, "fleft");
        bleft  = hardwareMap.get(DcMotor.class, "bleft");
        fright  = hardwareMap.get(DcMotor.class, "fright");
        bright  = hardwareMap.get(DcMotor.class, "bright");
        actuator  = hardwareMap.get(DcMotor.class, "actuator");
        duck = hardwareMap.get(DcMotor.class, "duck");


        //vacuumsama = hardwareMap.get(DcMotor.class, "vacuumsama");
        //rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

        //drive.setDirection(DcMotor.Direction.FORWARD);
        //rightDrive.setDirection(DcMotor.Direction.REVERSE);

        //presses start
        waitForStart();
        runtime.reset();

        //presses stop
        while (opModeIsActive()) {


            /*ITS ALL COLOR STUFF !!!!111!! DONT LISTEN!!1!11!!
            //color sensor stuff
            bCurrState = gamepad1.x;

            if (bCurrState && (bCurrState != bPrevState))  {
                bLedOn = !bLedOn;
                colorsensor.enableLed(bLedOn);
            }

            bPrevState = bCurrState;
            Color.RGBToHSV(colorsensor.red() * 8, colorsensor.green() * 8, colorsensor.blue() * 8, hsvValues);

            //its BLACK
            if(colorsensor.alpha() < 15){
                telemetry.addData("Black", true);
                //drive.setPower(1);
            }
            //this aint color yellow black oooga booga why do they call it an oven when you oven the cold food of out hot eat the food
            else{
                telemetry.addData("this aint color bruh", true);
                //drive.setPower(0);
            }
            
            // lmao matt smellz
            telemetry.addData("LED", bLedOn ? "On" : "Off");
            telemetry.addData("Clear", colorsensor.alpha());
            telemetry.addData("Red  ", colorsensor.red());
            telemetry.addData("Green", colorsensor.green());
            telemetry.addData("Blue ", colorsensor.blue());
            telemetry.addData("Hue", hsvValues[0]);

            relativeLayout.post(new Runnable() {
                public void run() {
                    relativeLayout.setBackgroundColor(colorsensor.red());
                }
            });
            /*END OF COLOR GARbage PLEASE PAY ATTNTION NOW !!!111
             */

            //DRIVING AROUND!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

            //FORWARD
            if(gamepad1.left_stick_y > 0 && gamepad1.right_stick_y > 0){
                fleft.setDirection(DcMotor.Direction.REVERSE);
                bleft.setDirection(DcMotor.Direction.REVERSE);
                fright.setDirection(DcMotor.Direction.FORWARD);
                bright.setDirection(DcMotor.Direction.FORWARD);

                fleft.setPower(fpower);
                bleft.setPower(power*fix);
                fright.setPower(fpower);
                bright.setPower(power*fix);
            }
            //BACKWARD
            else if(gamepad1.left_stick_y < 0 && gamepad1.right_stick_y < 0){
                fleft.setDirection(DcMotor.Direction.FORWARD);
                bleft.setDirection(DcMotor.Direction.FORWARD);
                fright.setDirection(DcMotor.Direction.REVERSE);
                bright.setDirection(DcMotor.Direction.REVERSE);

                fleft.setPower(fpower);
                bleft.setPower(power*fix);
                fright.setPower(fpower);
                bright.setPower(power*fix);
            }
            //LEFT
            else if(gamepad1.left_stick_x < 0 && gamepad1.right_stick_x < 0){
                fleft.setDirection(DcMotor.Direction.FORWARD);
                bleft.setDirection(DcMotor.Direction.REVERSE);
                fright.setDirection(DcMotor.Direction.FORWARD);
                bright.setDirection(DcMotor.Direction.REVERSE);

                fleft.setPower(fpower);
                bleft.setPower(power*fix);
                fright.setPower(fpower);
                bright.setPower(power*fix);
            }
            //RIGHT
            else if(gamepad1.left_stick_x > 0 && gamepad1.right_stick_x > 0){
                fleft.setDirection(DcMotor.Direction.REVERSE);
                bleft.setDirection(DcMotor.Direction.FORWARD);
                fright.setDirection(DcMotor.Direction.REVERSE);
                bright.setDirection(DcMotor.Direction.FORWARD);

                fleft.setPower(fpower);
                bleft.setPower(power*fix);
                fright.setPower(fpower);
                bright.setPower(power*fix);
            }
            //ROTATE CW
            else if(gamepad1.left_stick_y < 0 && gamepad1.right_stick_y > 0){
                fleft.setDirection(DcMotor.Direction.REVERSE);
                bleft.setDirection(DcMotor.Direction.REVERSE);
                fright.setDirection(DcMotor.Direction.REVERSE);
                bright.setDirection(DcMotor.Direction.REVERSE);

                fleft.setPower(fpower*fix);
                bleft.setPower(power);
                fright.setPower(fpower*fix);
                bright.setPower(power);
            }
            //ROTATE CCW
            else if(gamepad1.left_stick_y > 0 && gamepad1.right_stick_y < 0){
                fleft.setDirection(DcMotor.Direction.FORWARD);
                bleft.setDirection(DcMotor.Direction.FORWARD);
                fright.setDirection(DcMotor.Direction.FORWARD);
                bright.setDirection(DcMotor.Direction.FORWARD);

                fleft.setPower(fpower*fix);
                bleft.setPower(power);
                fright.setPower(fpower*fix);
                bright.setPower(power);
            }
            //NONE OF THEM? DO NOOOOOOOOOOOOOOOOOOOOOOOT MOVE
            else{
                fleft.setPower(0);
                bleft.setPower(0);
                fright.setPower(0);
                bright.setPower(0);
            }
            //END OF DRIve, DUMBASS

            //DUCK THING
            if(gamepad1.a){
                duck.setDirection(DcMotor.Direction.FORWARD);
                duck.setPower(duckpower);
            }
            else if(gamepad1.y){
                duck.setDirection(DcMotor.Direction.REVERSE);
                duck.setPower(duckpower);
            }
            else{
                duck.setPower(0);
            }

            //walle arms OwO
            lalle.setPosition(lpos);
            if(gamepad2.dpad_down && lpos >= 0){
                lpos += rate;
            }
            else if(gamepad2.dpad_up && lpos <= 1){
                lpos -= rate;
            }
            ralle.setPosition(rpos);
            if(gamepad2.a && rpos >= 0){
                rpos -= rate;
            }
            else if(gamepad2.y && lpos <= 1){
                rpos += rate;
            }

            //actuator thingss
            armguy.setPosition(armpos);
            if((gamepad2.right_stick_y > 0) && armpos >= 0){
                armpos += armrate;
            } else if((gamepad2.right_stick_y < 0) && armpos <= 1){
                armpos -= armrate;
            }
            if(gamepad2.left_stick_y > 0){
                actuator.setDirection(DcMotor.Direction.FORWARD);
                actuator.setPower(0.5);
            } else if(gamepad2.left_stick_y < 0){
                actuator.setDirection(DcMotor.Direction.REVERSE);
                actuator.setPower(0.5);
            } else{
                actuator.setPower(0);
            }


            //THIS THE VACUUM :)  <OBSOLETE HAHAHAHAHAHAHAHHAHAHAHAHAHHAHAHAHHAHAHAHHAHAHAHAHHAHAHAHAHAHHAHHA>
            /*if(gamepad2.a){
                vacuumsama.setDirection(DcMotor.Direction.REVERSE);
                vacuumsama.setPower(vpower);
            }
            else{
                vacuumsama.setPower(0);
            }*/

            //drive.setPower(power);
            //rightDrive.setPower(rightPower);

            //  saying stuff
            //telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);

            telemetry.update();
        }
    }
}
