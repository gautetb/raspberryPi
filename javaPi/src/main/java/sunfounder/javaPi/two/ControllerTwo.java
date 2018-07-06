package sunfounder.javaPi.two;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTwo {

    private static GpioPinDigitalOutput pin;
    private static GpioPinDigitalInput myButton;
    private static GpioController gpio;

    @RequestMapping("/")
    public String greeting(){
        return "Hello World";
    }

    @RequestMapping("/button")
    public String buttonPress() {

            gpio = GpioFactory.getInstance();

            if (pin == null) {
                pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "My LED", PinState.LOW);
            }

            if (myButton == null) {
                myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);
            }

            myButton.addListener(new GpioPinListenerDigital() {
                @Override
                public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                    System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " +event.getState());
                    pin.toggle();
                }
            });


        return "bottonPress";
    }
}


