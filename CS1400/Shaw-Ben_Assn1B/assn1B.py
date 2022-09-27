# Ben Shaw
# CS1400 - AO1 XL
# Assignment 1B

import turtle

def circles(x,s,d,n):
    #We draw the first circle and define the starting point.
    turtle.color("black","white")
    sp=-x
    turtle.penup()
    turtle.goto(0, sp)
    turtle.pendown()
    turtle.begin_fill()
    turtle.circle(x)
    turtle.end_fill()

#Now we recursively draw the other circles. the circles have decreasing radii and ever-moving starting points.
    A=range(1,n)
    for i in A:
        r=x*s**(i)
        sp=sp+2*d*x*s**(i-1)
        turtle.penup()
        turtle.goto(0,sp)
        turtle.pendown()
        turtle.begin_fill()
        turtle.circle(r)
        turtle.end_fill()

    turtle.penup()
    turtle.goto(0,sp+2*r)

#end of function definition.

#d=1 means no intersection. anything greater than 1 is a "floating snowman."
#d=0 gives each circle the same starting position.
#The shrinking factor should be between 0 and 1.
#not all choices of 0<d<1 and 0<s<1 give us a snowman: the shrinking factor may be too small.
#Here is our well-balanced snowman for the assignment submission:

circles(100,0.7,0.8,3)

#Now we will draw the triangle hat.

turtle.color("black","yellow")
turtle.begin_fill()
turtle.pendown()
turtle.forward(50)
turtle.left(120)
turtle.forward(100)
turtle.left(120)
turtle.forward(100)
turtle.left(120)
turtle.forward(50)
turtle.end_fill()

#Now the black eyes
turtle.right(90)
#we'll go down half the radius of the top circle
turtle.penup()
turtle.forward(100/2*0.7**2)
turtle.left(90)
#and to the side a third of the radius.
turtle.forward(100/3*0.7**2)
turtle.dot(15)
#Now the other side.
turtle.right(180)
turtle.forward(3*100/4*0.7**2)
turtle.dot(15)

#From here let's start the smile.
turtle.left(90)
turtle.forward(2/3*100*0.7**2)
turtle.left(45)
turtle.pendown()
turtle.color("red","white")
turtle.begin_fill()
turtle.width(5)
turtle.circle(20,90)
turtle.end_fill()

#Now the bottons
turtle.penup()
turtle.goto(0,100)
turtle.dot(10,"green")
turtle.goto(0,120)
turtle.dot(10,"green")
turtle.goto(0,140)
turtle.dot(10,"green")

#Now the arms.
turtle.goto(0.7*100,100/2+100*0.8)
turtle.color("brown")
turtle.pendown()
turtle.right(45)
#arm
turtle.forward(100)
#go to palm and draw other fingers.
turtle.goto(0.7*100+90,100/2+100*0.8)
turtle.right(45)
turtle.forward(10)
turtle.goto(0.7*100+90,100/2+100*0.8)
turtle.left(90)
turtle.forward(10)
#Now do the same on the other side.
turtle.penup()
turtle.goto(-0.7*100,100/2+100*0.8)
turtle.left(135)
turtle.pendown()
turtle.forward(100)
#Go to palm again
turtle.goto(-0.7*100-90,100/2+100*0.8)
turtle.right(45)
turtle.forward(10)
turtle.goto(-0.7*100-90,100/2+100*0.8)
turtle.left(90)
turtle.forward(10)

turtle.hideturtle()
turtle.done()