# Ben Shaw
# CS1400 - AO1 XL
# Assignment 4

import turtle


def drawChessboard(xpoint, ypoint, width=250, height=250):
    # We start by drawing the zig-zap outline of the chessboard.
    # turtle.speed(0) # If desired, the speed can be increased dramatically.
    turtle.penup()
    turtle.goto(xpoint, ypoint)
    turtle.pendown()
    turtle.begin_fill()

    for i in range(1, 5):
        turtle.forward(width)
        turtle.left(90)
        turtle.forward(height / 8)
        turtle.left(90)
        turtle.forward(width)
        turtle.right(90)
        turtle.forward(height / 8)
        turtle.right(90)

    turtle.forward(width)

    # Now we're done drawing the zig-zag outline of the chessboard. We will now call the drawAllRectangles function.

    drawAllRectangles(width, height)

    # Now we actually fill in the colors of the rectangles drawn with the drawRectangle command.
    turtle.end_fill()

    # Now we need to fill in the missing sides (Just for optimal cosmetics).

    turtle.right(180)
    turtle.forward(height)
    turtle.right(90)
    turtle.forward(width)

    turtle.hideturtle()
    turtle.done()


def drawAllRectangles(widthAR, heightAR):
    # We are starting at the top right corner, facing left. Let's get into position.

    turtle.right(90)
    turtle.forward(heightAR)

    for i in range(1, 5):
        turtle.right(90)
        turtle.forward(widthAR / 8)
        turtle.right(90)
        for j in range(1, 9):
            drawRectangle(j, widthAR, heightAR)

        turtle.left(90)
        turtle.forward(widthAR / 8)
        turtle.left(90)
        for j in range(1, 9):
            drawRectangle(j+1, widthAR, heightAR)




def drawRectangle(ind, widthR, heightR):
    # colors = ["red", "black"] # these colors were used in testing, since white is hard to see.
    colors = ["black", "white"]
    p = int(1 / 2 - ((-1) ** (ind + 1)) / 2)
    turtle.color(colors[p])
    turtle.forward(heightR / 8)
