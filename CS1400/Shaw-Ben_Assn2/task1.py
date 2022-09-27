# Ben Shaw
# CS1400 - AO1 XL
# Assignment 2

AMOUNT = input("The investment amount is: ")
PAYAMOUNT = input("The monthly payment amount is: ")
RATE = input("The annual interest rate (percentage) is: ")
TERM = input("The number of years is: ")

monthlyInterestRate = float(RATE) / 12 / 100
numberOfMonths = float(TERM) * 12

s0 = 1 + monthlyInterestRate
s1 = s0 ** numberOfMonths - 1

futureValue = float(AMOUNT) * s0 ** numberOfMonths + float(PAYAMOUNT) * s1 / monthlyInterestRate * s0
roundedFutureValue = round(futureValue,2)

print("The future value is: ", roundedFutureValue)
