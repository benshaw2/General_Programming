# Ben Shaw
# CS1400 - AO1 XL
# Assignment 2

import turtle

#First, we collect the input from the user.
CENTX = float(input("The x-coordinate for the center of the target is: "))
CENTY = float(input("The y-coordinate for the center of the target is: "))
DIAMETER = float(input("The diameter of the bullseye is: "))

#Next, we define the concentric circles function.
#We begin at the largest in order to overlap the circles.
def circles(x,y,d):
    col = ["black", "turquoise", "red", "yellow"]
    r = 5 * d / 2
    sp = y - 5 * d / 2
    A = range(1,5)
    for i in A:
        r -= d / 2
        sp += d / 2
        turtle.penup()
        turtle.goto(x,sp)
        turtle.pendown()
        turtle.color(col[i-1])
        turtle.begin_fill()
        turtle.circle(r)
        turtle.end_fill()


#With the function defined, we now call the function.
circles(CENTX,CENTY,DIAMETER)

turtle.hideturtle()
turtle.done()