# Ben Shaw
# CS1400 - AO1 XL
# Assignment 4

import turtle
import random


def reset():
    turtle.clear()


def setup():
    turtle.setup(1000,800)
    # turtle.screensize(canvwidth=10000, canvheight=800)
    # turtle.screensize()
    turtle.speed(0)


def drawRectanglePattern(xpos, ypos, off, Width, Height, Count, Rotation):
    # print("This will call drawRectangle.")
    turtle.penup()
    turtle.goto(xpos, ypos)
    turtle.pendown()
    radpos = 360 / Count
    for i in range(Count):
        turtle.penup()
        turtle.goto(xpos, ypos)
        turtle.left(radpos + 90)
        turtle.forward(off)
        turtle.right(90 - Rotation)
        # turtle.setheading(Rotation)
        setRandomColor()
        turtle.pendown()
        drawRectangle(Width, Height)
        turtle.right(Rotation)
        # radpos += 360 / Count
        ## turtle.right(Rotation + radpos)

    # turtle.hideturtle()


def drawRectangle(wid, hei):
    turtle.forward(wid)
    turtle.left(90)
    turtle.forward(hei)
    turtle.left(90)
    turtle.forward(wid)
    turtle.left(90)
    turtle.forward(hei)
    turtle.left(90)


def drawCirclePattern(xpos, ypos, off, rad, Count):
    turtle.penup()
    turtle.goto(xpos, ypos)
    turtle.pendown()
    radpos = 360 / Count
    for i in range(Count):
        turtle.penup()
        turtle.goto(xpos, ypos)
        turtle.left(radpos + 90)
        turtle.forward(off)
        turtle.right(90)
        setRandomColor()
        turtle.pendown()
        turtle.circle(rad)

    # turtle.hideturtle()


def drawSuperPattern(Num=2):
    for i in range(Num):
        p1 = random.randint(0, 1)
        if p1 == 0:
            xp = random.randint(-300, 300)
            yp = random.randint(-300, 300)
            offs = random.randint(-100, 100)
            radi = random.randint(1, 200)
            Ct = random.randint(2, 100)
            drawCirclePattern(xp, yp, offs, radi, Ct)
        elif p1 == 1:
            xp = random.randint(-300, 300)
            yp = random.randint(-300, 300)
            offs = random.randint(-100, 100)
            Wid = random.randint(1, 200)
            Hei = random.randint(1, 200)
            Ct = random.randint(2, 100)
            rot = random.randint(0, 360)
            drawRectanglePattern(xp, yp, offs, Wid, Hei, Ct, rot)


def setRandomColor():
    colors = ["red", "blue", "green", "black", "purple", "orange", "pink"]
    p = random.randint(0, len(colors) - 1)
    turtle.color(colors[p])


def done():
    turtle.done()
