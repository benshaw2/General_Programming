# Ben Shaw
# CS1400 - AO1 XL
# Assignment 6

A = []
smart = False

while not smart:
    number = input("Please enter a number: ")
    if number == "":
        smart = True
    else:
        A.append(number)
        # We are not validating the user input.

B = [float(a) for a in A]
numvals = len(A)
sumvals = 0 if len(B) >= 1 else ""
for i in B:
    sumvals += i

avVal = sumvals / numvals if numvals != 0 else ""

# The following is the hack and slash approach at getting the max without sorting.
if len(B) != 0:
    for i in range(len(B)):
        C = [B[i] for j in range(len(B)) if B[i] >= B[j]]
        if len(C) == len(B):
            break
else:
    C = []

maxval = C[0] if len(C) >= 1 else ""
minval = min(B) if len(B) >= 1 else ""

print("Number of entries: " + str(numvals))
print("Maximum value: " + str(maxval))
print("Minimum value: " + str(minval))
print("Sum of all values: " + str(sumvals))
print("Average value: " + str(avVal))
