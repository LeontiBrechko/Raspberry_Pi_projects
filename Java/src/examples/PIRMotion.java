package examples;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;

import java.util.concurrent.Callable;

/**
 * Created by Leonti on 2016-05-02.
 */
public class PIRMotion {
    private final static GpioController controller = GpioFactory.getInstance();

    public static void main(String[] args) throws InterruptedException {
        GpioPinDigitalInput input = controller.provisionDigitalInputPin(RaspiPin.GPIO_07);
        System.out.println("Starting...");

        Callable<Void> callback = () -> {
            System.out.println("-----Got a callback from sensor-----");
            return null;
        };

        input.addTrigger(new GpioCallbackTrigger(callback));

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Interrupted, stopping...\n");
                controller.shutdown();
            }
        });

        for (;;) {
            Thread.sleep(100);
        }
    }
}
