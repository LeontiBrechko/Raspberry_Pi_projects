from gpiozero import LED, Button, MotionSensor
import time

sensor_activation_btn = Button(21)
sensor_activation_led = LED(15)
sensor_detection_led = LED(18)
exit_btn = Button(20)
is_sensor_activated = False
sensor = MotionSensor(4)


def exit_app():
    sensor_activation_led.off()
    sensor_detection_led.off()
    quit()


def change_sensor_status():
    global is_sensor_activated
    if is_sensor_activated:
        sensor_activation_led.off()
        is_sensor_activated = False
        print 'Sensor activated: ', is_sensor_activated
    else:
        sensor_activation_led.on()
        is_sensor_activated = True
        print 'Sensor activated: ', is_sensor_activated


def motion_detected():
    if is_sensor_activated:
        if not sensor_detection_led.is_lit:
            sensor_detection_led.on()
        print 'Motion detected'
        print time.asctime(time.localtime(time.time()))


def no_motion_detected():
    print '\nNo more motion'
    if sensor_detection_led.is_lit:
        sensor_detection_led.off()


def main():
    sensor_activation_btn.when_released = change_sensor_status
    exit_btn.when_released = exit_app
    sensor.when_motion = motion_detected
    sensor.when_no_motion = no_motion_detected

    print 'Program started'


if __name__ == '__main__':
    main()
    while True:
        time.sleep(1)

