# Ben Shaw
# CS1400 - AO1 XL
# Assignment 3

import math

print("Welcome to the Social Situation Analyzer System")

print("Person One")
person1Name = input("Enter your name: ")
person1Pos = eval(input("Enter your position (x,y): "))
person1Rad = eval(input("Enter your personal space radius: "))

print("Person Two")
person2Name = input("Enter your name: ")
person2Pos = eval(input("Enter your position (x,y): "))
person2Rad = eval(input("Enter your personal space radius: "))

distance = math.sqrt((person1Pos[0] - person2Pos[0])**2 + (person1Pos[1] - person2Pos[1])**2)

# Now we will say who the social butterfly is and who the social recluse is.
# This will narrow down the number of possible outcomes later: from 4 to 3.
if person1Rad >= person2Rad:
    socialRecluse = [person1Name, person1Pos, person1Rad]
    socialButterfly = [person2Name, person2Pos, person2Rad]
else:
    socialRecluse = [person2Name, person2Pos, person2Rad]
    socialButterfly = [person1Name, person1Pos, person1Rad]

# Person test.
if distance > socialRecluse[2]:
    msg = "Neither " + socialRecluse[0] + " nor " + socialButterfly[0] + " is in the other's personal space."
elif socialRecluse[2] >= distance > socialButterfly[2]:
    msg = socialButterfly[0] + " is in " + socialRecluse[0] + "'s personal space."
elif socialButterfly[2] >= distance:
    msg = socialRecluse[0] + " and " + socialButterfly[0] + " are in each other's personal spaces."
else:
    msg = "I don't think this is possible."

# Space test.
if distance > socialRecluse[2] + socialButterfly[2]:
    msg += "\n" + socialRecluse[0] + " and " + socialButterfly[0] + "'s personal spaces do not overlap."
elif socialRecluse[2] + socialButterfly[2] >= distance > socialRecluse[2] - socialButterfly[2]:
    msg += "\n" + socialRecluse[0] + " and " + socialButterfly[0] + "'s personal spaces overlap."
elif socialRecluse[2] - socialButterfly[2] >= distance:
    msg += "\n" + socialButterfly[0] + "'s personal space is entirely inside " + socialRecluse[0] + "'s personal space."
else:
    msg += "\nI don't think this is possible."

print(msg)
