# Ben Shaw
# CS1400 - AO1 XL
# Assignment 5

import turtle


class Face:
    def __init__(self):
        self.__smile = True
        self.__happy = True
        self.__darkEyes = True
        self.__radius = 100
        # self.__drawHead = DrawHead(100)

    def __drawHead(self):
        turtle.showturtle()
        if self.__happy:
            turtle.fillcolor("yellow")
        else:
            turtle.fillcolor("red")

        turtle.penup()
        turtle.goto(0, -self.__radius)
        turtle.pendown()
        turtle.begin_fill()
        turtle.circle(self.__radius)
        turtle.end_fill()

    def __drawEyes(self):
        if self.__darkEyes:
            Color = "black"
        else:
            Color = "blue"

        turtle.penup()
        turtle.goto(-1 / 2 * self.__radius, 1 / 2 * self.__radius)
        turtle.pendown()
        turtle.dot(self.__radius // 5, Color)
        turtle.penup()
        turtle.goto(1 / 2 * self.__radius, 1 / 2 * self.__radius)
        turtle.pendown()
        turtle.dot(self.__radius // 5, Color)

    def __drawMouth(self):
        if self.__smile:
            turtle.penup()
            turtle.goto(0, -3 / 4 * self.__radius)
            turtle.pendown()
            turtle.circle(3 / 4 * self.__radius, 45)
            turtle.penup()
            turtle.goto(0, -3 / 4 * self.__radius)
            turtle.right(45)
            turtle.pendown()
            turtle.circle(3 / 4 * self.__radius, -45)
            turtle.left(45)

        else:
            turtle.penup()
            turtle.right(180)
            turtle.goto(0, -1 / 4 * self.__radius)
            turtle.pendown()
            turtle.circle(3 / 4 * self.__radius, 45)
            turtle.penup()
            turtle.goto(0, -1 / 4 * self.__radius)
            turtle.right(45)
            turtle.pendown()
            turtle.circle(3 / 4 * self.__radius, -45)
            turtle.right(135)

        turtle.hideturtle()




    def draw_face(self):
        turtle.clear()
        self.__drawHead()
        self.__drawEyes()
        self.__drawMouth()

    def isSmile(self):
        return self.__smile # <Fill-In>

    def isHappy(self):
        return self.__happy # <Fill-In>

    def isDarkEyes(self):
        return self.__darkEyes # <Fill-In>

    def changeMouth(self):
        self.__smile = False if self.__smile else True # <Fill-In>
        self.draw_face()

    def changeEmotion(self):
        self.__happy = False if self.__happy else True # <Fill-In>
        self.draw_face()

    def changeEyes(self):
        self.__darkEyes = False if self.__darkEyes else True # <Fill-In>
        self.draw_face()


def main():
    face = Face() # <Fill-In>
    face.draw_face() # <Fill-In>

    done = False

    while not done:
        print("Change My Face")
        mouth = "frown" if face.isSmile() else "smile"
        emotion = "angry" if face.isHappy() else "happy"
        eyes = "blue" if face.isDarkEyes() else "black"
        print("1) Make me", mouth)
        print("2) Make me", emotion)
        print("3) Make my eyes", eyes)
        print("0) Quit")

        menu = eval(input("Enter a selection: "))

        if menu == 1:
            face.changeMouth() # <Fill-In>
        elif menu == 2:
            face.changeEmotion() # <Fill-In>
        elif menu == 3:
            face.changeEyes() # <Fill-In>
        else:
            break

    print("Thanks for Playing")

    turtle.hideturtle()
    turtle.done()


main()
