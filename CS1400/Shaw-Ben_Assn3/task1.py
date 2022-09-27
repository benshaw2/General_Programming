# Ben Shaw
# CS1400 - AO1 XL
# Assignment 3

import random
from math import fabs

randNum = random.randint(1, 10)

print("Welcome to the Guessing Game")
print("The computer has picked a number from 1 - 10. Try to match it.")
guess = int(input("What number do you choose (1-10)? "))

print("You picked " + str(guess) + ", and the actual number was " + str(randNum) + ".")

if guess - randNum == 0:
    msg = "Honored to play with you, Master."
elif fabs(guess - randNum) == 1:
    msg = "You are a worthy opponent, Knight."
elif fabs(guess - randNum) == 2:
    msg = "You have much to learn, Padawan."
elif fabs(guess - randNum) == 3:
    msg = "Youngling, your time will come."
else:
    msg = "Keep working hard in the Service Corps."

print(msg)
