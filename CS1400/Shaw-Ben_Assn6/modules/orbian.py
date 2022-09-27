from math import pi
from random import shuffle # Hint hint
from random import randint
import time

class Orbian:
    # DO NOT MODIFY THE CONSTRUCTOR
    def __init__(self, name, headRadius, bodyRadius, bodyHeight):
        # NOTE: These are constants
        self.__HEAD_RADIUS = headRadius
        self.__BODY_RADIUS = bodyRadius
        self.__BODY_HEIGHT = bodyHeight
        self.__NAME = name
        self.__BIRTH_TIME = time.time()

        # This is the only variable
        self.__adult = False

    def __getHeadRadius(self):
        return self.__HEAD_RADIUS

    def __getBodyRadius(self):
        return self.__BODY_RADIUS

    def __getBodyHeight(self):
        return self.__BODY_HEIGHT

    def __getHeadVolume(self):
        return 4 / 3 * pi * self.__getHeadRadius() ** 3

    def __getBodyVolume(self):
        return pi * self.__getBodyRadius() ** 2 * self.__getBodyHeight()

    def __ageCheck(self):
        # Become an adult at 2
        if self.getAge() >= 2:
            self.__adult = True

    ####### ADD OTHER REQUIRED METHODS BELOW. SEE THE ASSIGNMENT DESCRIPTION AND OTHER STARTER CODE FOR INSIGHT ######

    def getAge(self):
        timenow = time.time()
        return (timenow - self.__BIRTH_TIME) / 5

    def __repr__(self):
        return "I am Orbian " + self.__NAME

    def getName(self):
        return self.__NAME

    def getVolume(self):
        return self.__getBodyVolume() + self.__getHeadVolume()

    def __gt__(self, other):
        return self.getVolume() > other.getVolume()

    def __eq__(self, other):
        return self.getVolume() == other.getVolume()

    def __len__(self):
        return self.__BODY_HEIGHT + 2 * self.__HEAD_RADIUS

    def __add__(self, other):
        namelen = ( len(self.__NAME) + len(other.__NAME) ) // 2
        name1 = self.__NAME
        name2 = other.__NAME
        firstpart = list(name1 + name2)
        shuffle(firstpart)
        Name = ("".join(firstpart))[0:namelen].capitalize()
        headRadius = (self.__HEAD_RADIUS + other.__HEAD_RADIUS) // 4
        bodyRadius = (self.__BODY_RADIUS + other.__BODY_RADIUS) // 4
        bodyHeight = (self.__BODY_HEIGHT + other.__BODY_HEIGHT) // 8
        return Orbian(Name, headRadius, bodyRadius, bodyHeight)




