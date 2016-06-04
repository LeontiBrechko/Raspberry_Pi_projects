from gpiozero import Button, LED, TrafficLights
from time import sleep
from sys import exit


def btn_clicked():
    lights.off()
    quit()


button = Button(21)
lights = TrafficLights(17, 27, 22)

button.when_released = btn_clicked


def main():
    print('Ready to use!')
    while True:
        # button.wait_for_press()
        # print('Button pressed')
        lights.green.on()
        sleep(1)
        lights.green.off()
        lights.amber.on()
        sleep(1)
        lights.amber.off()
        lights.red.on()
        sleep(2)
        lights.amber.on()
        sleep(1)
        lights.off()


if __name__ == '__main__':
    print('Starting...')
    main()
