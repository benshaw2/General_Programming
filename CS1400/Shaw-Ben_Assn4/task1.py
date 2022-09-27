# Ben Shaw
# CS1400 - AO1 XL
# Assignment 4

# Code was not asked, but writing the code explicitly made the task of writing the plan more solid.
# The code is included more as an appendix and does not replace the actual task 1 assignment.

Num = int(input("Enter the number of rows: "))


def makeNumberPyramid(num):

    totalWidth = num * (len(str(num)) + 1)
    msg = ""

    for i in range(num + 1):
        line = ""
        for j in range(i):
            line += str(i) + " "

        msg += format(line, "^" + str(totalWidth))
        msg += "\n"

    return msg

output = makeNumberPyramid(Num)
print(output)
