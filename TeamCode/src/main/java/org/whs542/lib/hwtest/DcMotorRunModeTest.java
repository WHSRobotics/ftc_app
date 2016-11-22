package org.whs542.lib.hwtest;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.w3c.dom.Document;
import org.whs542.lib.Toggler;

/**
 * Created by Amar2 on 11/19/2016.
 */
@Autonomous(name = "RunModeTest", group = "Tests")
public class DcMotorRunModeTest extends OpMode{

    DcMotor a;
    DcMotor b;
    Toggler tog = new Toggler(40);
    Toggler tog2 = new Toggler(2);
    int power;

    @Override
    public void init() {
        a = hardwareMap.dcMotor.get("a");
        b = hardwareMap.dcMotor.get("b");
        a.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        b.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void start(){
        resetStartTime();
    }

    @Override
    public void loop() {
        tog2.changeState(gamepad1.x);
        tog.changeState(gamepad1.a, gamepad1.b);
        power = (tog.currentState()+1)*100;
        a.setMaxSpeed(power);
        b.setMaxSpeed(power);
        //a.setMaxSpeed(4200);
        //b.setMaxSpeed(2100);


        if( tog2.currentState() == 1 && a.getCurrentPosition() <= 20000){
            a.setPower(1.0);
            b.setPower(1.0);
        } else {
            a.setPower(0);
            b.setPower(0);
        }

        if(gamepad1.y){
            a.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            b.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            a.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            b.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        telemetry.addData("A Enc Ticks", a.getCurrentPosition());
        telemetry.addData("B Enc Ticks", b.getCurrentPosition());
        telemetry.addData("Encoder ticks per sec", power);
        telemetry.addData("Loop Runtime", getRuntime());

    }
}
