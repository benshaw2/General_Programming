# Ben Shaw
# CS1400 - AO1 XL
# Assignment [indicate]

import time

currentTime = time.time()
currentTimeInt = int(currentTime)

currentSeconds = currentTimeInt % 60
totalMinutes = currentTimeInt // 60
currentMinutes = totalMinutes % 60
totalHours = totalMinutes // 60
currentHours = totalHours % 24

print("The time across the pond is now", currentHours, ":", currentMinutes, ":", currentSeconds)

print("Hello",end="")
print("Goodbye")

print(round(6.5))

print("this is a test")
val = 2
val -= 2
print(val)