# Ben Shaw
# CS1400 - AO1 XL
# Assignment 7

from random import randint

# Remember how to use this kind of variable?
count = 0

def binarySearch(list, Key):
    low = 0
    high = len(list) - 1
    return binarySearchRecursive(list, Key, low, high)

def binarySearchRecursive(List, key, Low, High):
    global count
    count += 1
    if Low > High:
        return -1
    mid = (Low + High) // 2
    if key == List[mid]:
        return mid
    elif key < List[mid]:
        return binarySearchRecursive(List, key, Low, mid - 1)
    else:
        return binarySearchRecursive(List, key, mid + 1, High)


def aggienacci(Value):
    if Value == 0:
        return 0
    elif Value == 1:
        return 1
    elif Value == 2:
        return 2
    else:
        return (aggienacci(Value-3) + aggienacci(Value-2)) / aggienacci(Value-1)


def main():
    print("Welcome to Recursion Fun")

    # aggienacci calculation
    value = eval(input("Enter a number to find it's aggienacci value: "))
    print("The aggienacci value of " + str(value) + " is " + str(round(aggienacci(value), 4)))

    print()

    # Recursive search and sort
    key = eval(input("Enter a number to search for: "))
    numList = []
    for i in range(200000):
        if randint(0, 2) == 0:
            numList.append(i)

    numPos = binarySearch(numList, key)

    if numPos == -1:
        print("Your number, " + str(key) + ", is not in the list")
    else:
        print("Your number, " + str(key) + ", is in the list at position " + str(numPos))

    print("Total recursive calls: " + str(count))




main()

