# Ben Shaw
# CS1400 - AO1 XL
# Assignment 5

import time

class Blobber:
    def __init__(self, Name, Color, Radius, Height):
        self.__birthRadius = Radius
        self.__height = Height
        self.__color = Color.lower()
        self.__name = Name.capitalize()
        self.__currentRadius = Radius
        self.__time = time.time()
        self.__shrinkRate = 0.002

    def DisplayName(self):
        return self.__name

    def DisplayColor(self):
        return self.__color

    def ChangeName(self, Name):
        self.__name = Name.capitalize()

    def ChangeColor(self, Color):
        self.__color = Color.lower()

    def FeedBlobber(self, Food):
        self.__currentRadius += Food

    def BlobberSpeak(self):
        hapLevel = self.__currentRadius / self.__birthRadius
        msg = ""
        msg += "My name is " + self.__name + ", and I am " + self.__color + "."
        msg += "\nMy current happiness level is " + format(hapLevel, ".2%")
        if hapLevel > 1.1 or hapLevel < 0.9:
            msg += "\nI have now turned to dust. Thanks for nothing."
        return msg

    def vitalsOK(self):
        timeElapsed = self.__time - time.time()
        self.__time = time.time()
        radLoss = self.__shrinkRate * self.__birthRadius * timeElapsed
        self.__currentRadius += radLoss
        hapLevel = self.__currentRadius / self.__birthRadius
        alive = True if 1.1 >= hapLevel >= 0.9 else False

        return hapLevel, alive

