# Ben Shaw
# CS1400 - AO1 XL
# Assignment 2

from math import tan
from math import pi
import math

n = float(input("The number of sides is: "))
s = float(input("The length of the sides is: "))

s1 = n * math.pow(s,2)
s2 = 4 * tan(pi / n)

area = s1 / s2

print(round(area,5))