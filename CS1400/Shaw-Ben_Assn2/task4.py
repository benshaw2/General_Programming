# Ben Shaw
# CS1400 - AO1 XL
# Assignment 2

#First, we get input from the user.
NAME = input("Enter employee's name: ")
HOURS = input("Enter number of hours worked in a week: ")
PAYRATE = input("Enter hourly pay rate: ")
FWHRATE = input("Enter federal tax withholding rate (ex. 0.12): ")
SWHRATE = input("Enter state tax withholding rate (ex. 0.06): ")

#Next, we convert most input values to floating point to do math.
hours = float(HOURS)
payRate = float(PAYRATE)
fwhRate = float(FWHRATE)
swhRate = float(SWHRATE)

#Now we perform the mathematical computations.
myPay = round(hours * payRate, 2)
fedSteal = round(myPay * fwhRate, 2)
stateSteal = round(myPay * swhRate, 2)
totalSteal = round(fedSteal + stateSteal ,2)
meagerEarnings = round(myPay - totalSteal, 2)

#To clean what will come, we will define a few strings beforehand.
l1 = NAME.upper() + " PAY INFORMATION"
l2 = "Pay"
l3 = "Hours Worked:  "
l4 = "Pay Rate: $"
l5 = "Gross Pay: $"
l6 = "Deductions"
l7 = "Federal Withholding (" + str(round(fwhRate * 100, 1)) + "%): $"
l8 = "State Withholding (" + str(round(swhRate * 100, 1)) + "%): $"
l9 = "Total Deductions: $"
l10 = "Net Pay: $"

#Define the widths.
itemWidth = len(l7)
otherWidth = 10
totalWidth = itemWidth + otherWidth

#Now we will build the message to print.
msg = "\n"
msg += format(l1, "^" + str(totalWidth))
msg += "\n"
msg += "\n"
#First the pay information
msg += format(l2, "^" + str(totalWidth))
msg += "\n"
msg += format(l3, ">" + str(itemWidth))
msg += format(HOURS, ">" + str(otherWidth))
msg += "\n"
msg += format(l4, ">" + str(itemWidth))
msg += format(str(payRate), ">" + str(otherWidth))
msg += "\n"
msg += format(l5, ">" + str(itemWidth))
msg += format(str("{:.2f}".format(myPay)), ">" + str(otherWidth))
msg += "\n"
msg += "\n"
#Now onto the deductions section
msg += format(l6, "^" + str(totalWidth))
msg += "\n"
msg += format(l7, ">" + str(itemWidth))
msg += format(str("{:.2f}".format(fedSteal)), ">" + str(otherWidth))
msg += "\n"
msg += format(l8, ">" + str(itemWidth))
msg += format(str("{:.2f}".format(stateSteal)), ">" + str(otherWidth))
msg += "\n"
msg += format(l9, ">" + str(itemWidth))
msg += format(str("{:.2f}".format(totalSteal)), ">" + str(otherWidth))
msg += "\n"
msg += "\n"
#Let's now give the net pay.
msg += format(l10, ">" + str(itemWidth))
msg += format(str("{:.2f}".format(meagerEarnings)), ">" + str(otherWidth))


print(msg)