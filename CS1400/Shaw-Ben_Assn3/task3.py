# Ben Shaw
# CS1400 - AO1 XL
# Assignment 3

import time
import random
import math

# We will begin the clock now.
initialTime = time.time()


# The following function is designed to find the factors of an integer n. See software design file for more details.
def factor(n):
    A = []
    possibility = range(1, n//3 + 1)

    for i in possibility:
        if n % i == 0:
            A.append(i)
    if n % 2 == 0:
        A.append(n // 2)

    return A


# We now define the list where the flunky numbers will be appended to and start the loop counter at zero.
C = []
loops = 0

# We now search the integers 1 - 10,000 for flunky numbers, stopping when we have found 7.
for j in range(1, 10000 + 1):

    factors = factor(j)
    # The following if statement will ensure that we count the loops from the "factor" program.
    if j % 2 == 0:
        loops += j//3 + 1
    else:
        loops += j//3

    B = []

    for thing in factors:
        random.seed(thing)
        num = random.randint(1, j)
        B.append(num)
        loops += 1

    # The following will append the number, if flunky, to the list C.
    if int(math.fsum(B)) == j:
        C.append(j)

    if len(C) == 7:
        # loops = j
        break

# Now we've found the flunky numbers, and the timer is stopped.
finalTime = time.time()
totalTime = round(finalTime - initialTime, 2)

# We now print the results.
msg = ""
for k in range(0, len(C)):
    msg += "Flunky Number: " + str(C[k]) + "\n"

msg += "Total Time: " + str(totalTime) + " seconds\n"
msg += "Total Loops: " + str(loops)

print(msg)
