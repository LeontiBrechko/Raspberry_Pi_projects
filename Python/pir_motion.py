from gpiozero import MotionSensor
from time import sleep

import time

pir = MotionSensor(4)

while True:
	if pir.motion_detected:
		print(time.asctime(time.localtime(time.time())))
		sleep(1)
