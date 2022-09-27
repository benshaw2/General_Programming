from modules.deck import Deck
from time import sleep
from modules.gronkyutil import convertCardToId
from modules.gronkyutil import TITLE, GANG

def main():
    print("Welcome to Gronky Cards\n")
    print("Shuffling Cards", end="")
    thinking()

    deck = Deck()
    playerHand = []

    cardCount = int(input("How many cards would you like?: "))

    for i in range(cardCount):
        playerHand.append(deck.draw())  # <Fill-In> # Single line

    done = False
    while not done:
        print()
        print("Menu")
        print("\t(1) Display hand")
        print("\t(2) Sort by title")
        print("\t(3) Sort by gang")
        print("\t(4) Search for card")
        print("\t(5) Quit")
        choice = int(input("Choose an option: "))
        print()

        if choice == 1:
            displayHand(playerHand)
        elif choice == 2:
            playerHand = sortByTitle(playerHand)  # <Fill-In> # Single line
        elif choice == 3:
            playerHand = sortByGang(playerHand)  # <Fill-In> # Single line
        elif choice == 4:
            cardSearch(playerHand)  # <Fill-In> # Single line
        elif choice == 5:
            done = True  # <Fill-In> # Not a function and not 'break'

def thinking():
    for i in range(5):
        print(".", end="")
        sleep(0.5)
    print()

def displayHand(hand):
    for crd in hand:
        print(crd)
    # print(hand)
    # <Fill-In> # Not a single line. The entire function body

# Add other functions you need below

def sortByTitle(hand):
    insertionSort(hand)
    print("Insertion sort by title", end="")
    thinking()
    return hand


def sortByGang(hand):
    l2 = bubbleSort(hand)
    print("Bubble sort by gang", end="")
    thinking()
    return l2



def bubbleSort(inputList):
    didSwap = True

    while didSwap:
        didSwap = False
        sortCnt = 1
        for i in range(len(inputList) - sortCnt):
            if inputList[i] >= inputList[i+1]:
                inputList[i], inputList[i+1] = inputList[i+1], inputList[i]
                didSwap = True

        sortCnt += 1

    return inputList


def insertionSort(inputList):
    for i in range(1, len(inputList)):
        currElement = inputList[i]
        j = i-1
        while j >=0 and inputList[j] > currElement:
            inputList[j + 1] = inputList[j]
            j -= 1

        inputList[j+1] = currElement


def binarySearch(inputList, key):
    low = 0
    high = len(inputList) - 1
    while high >= low:
        mid = (high + low) // 2
        if key == inputList[mid]:
            return mid
        elif key < inputList[mid]:
            high = mid - 1
        else:
            low = mid + 1

    return -1

def cardSearch(hand):
    print("Search for Card")
    print("\t(1) One")
    print("\t(2) Two")
    print("\t(3) Three")
    print("\t(4) Four")
    print("\t(5) Five")
    print("\t(6) Six")
    print("\t(7) Seven")
    print("\t(8) Eight")
    print("\t(9) Nine")
    print("\t(10) Ten")
    print("\t(11) Baker")
    print("\t(12) Jester")
    print("\t(13) Page")
    print("\t(14) Scribe")
    print("\t(15) Squire")
    print("\t(16) Armorer")
    print("\t(17) Marshal")
    choiceTitle = int(input("Choose a Title: "))
    print("\t(1) Jets")
    print("\t(2) Pollos")
    print("\t(3) Slugs")
    print("\t(4) Yokels")
    print("\t(5) Keiths")
    print("\t(6) Elbows")
    choiceGang = int(input("Choose a gang: "))

    l1 = [crd.getID() for crd in hand]
    # l1.sort()
    insertionSort(l1)
    print("Insertion sort by title", end="")
    thinking()
    query = (choiceGang - 1) * len(TITLE) + (choiceTitle - 1)  # convertCardToId(choiceTitle, choiceGang)
    print("Binary Search for " + TITLE[choiceTitle-1] + " of " + GANG[choiceGang-1], end="")
    thinking()
    result = binarySearch(l1, query)
    if result == -1:
        print("Sorry, but you do not have this card.")
    else:
        print("You do indeed have this card.")



main()