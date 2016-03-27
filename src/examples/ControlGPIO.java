package examples;

import com.pi4j.io.gpio.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Leonti on 2016-03-27.
 */

public class ControlGPIO {
    public static void main(String[] args) throws IOException {
        final GpioController controller = GpioFactory.getInstance();
        final GpioPinDigitalOutput outputPin =
                controller.provisionDigitalOutputPin(RaspiPin.GPIO_29, "ledPin", PinState.LOW);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("GPIO test started\n" +
                "enter \"q\" to exit\n" +
                "enter \"on\" to turn on the led\n" +
                "enter \"off\" to turn off the led" +
                "------------------");

        String nextInput;
        System.out.print("Enter the option: ");
        while (!(nextInput = bufferedReader.readLine()).equals("q")) {
            switch (nextInput.toLowerCase()) {
                case "on":
                    outputPin.high();
                    break;
                case "off":
                    outputPin.low();
                    break;
                default:
                    System.out.println("Please, enter \"on\", \"off\" or \"q\"");
                    break;
            }
            System.out.print("Enter the option: ");
        }
        System.out.println("\nThe End");

        controller.shutdown();
    }
}
