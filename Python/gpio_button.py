from gpiozero import Button, LED
from time import sleep


def main_method():
    button = Button(2)
    led = LED(4)

    button.when_pressed = led.on
    button.when_released = led.off

    while True:
        sleep(0.5)


if __name__ == '__main__':
    main_method()
