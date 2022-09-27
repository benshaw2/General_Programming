# Ben Shaw
# CS1400 - AO1 XL
# Assignment 7

# from modules.card import Card
from modules.deck import Deck
import sys
# import random
import time

def thinking(n):
    for i in range(n):
        print(".", end="")
        time.sleep(0.2)
    print()

def UpdatePoints(list,ind):
    hand = list[ind][1]
    point = 0
    for card in hand:
        point += card.getCardValue()
    return point

def main():
    deck = Deck()
    numPlayers = int(input("Number of players: "))

    if numPlayers > 5 or numPlayers < 1:
        print("You must specify a valid number of players.")
        sys.exit()

    playerList = ["Player " + str(i + 1) for i in range(numPlayers)]
    playerList.append("Dealer")

    playerData = []
    for i in range(len(playerList)):
        plist = [[100],[]] # [moneylist, cardlist]
        playerData.append(plist)


    stopPlaying = False
    count = 0
    while not stopPlaying:
        # Collect bets from players.
        bets = []
        for i in range(len(playerList)-1):
            trybet = eval(input(playerList[i] + " has a current balance of " + str(playerData[i][0][count]) + ". Please enter your bet amount: "))
            if 5 <= trybet <= playerData[i][0][count]:
                bet = trybet
            elif trybet > playerData[i][0][count]:
                bet = playerData[i][0][count]
            elif trybet < 5:
                bet = playerData[i][0][count]
            else:
                bet = 0
            bets.append(bet)
        bets.append(0)

        # playerPoints = []
        # Now we will Deal the cards.
        print("Shuffling cards")
        thinking(5)
        deck.shuffle()

        cardAmount = 2
        for i in range(cardAmount):
            for j in range(len(playerList)):
                playerData[j][1].append(deck.draw())

        # print(playerData)
        print("The Dealer's second card is: ", end="")
        print(playerData[-1][1][-1])

        # Now each player takes a turn
        playerTotals = [UpdatePoints(playerData, i) for i in range(len(playerList))]
        for i in range(len(playerList)-1):
            print(playerList[i] + ": it is your turn.")
            input("Press ENTER to continue (and view your hand).")
            print()
            print("Your cards:")
            for card in playerData[i][1]:
                print(card)
            done = False
            while not done:
                print()
                print("You have " + str(len(playerData[i][1])) + " cards.")
                print("\t(1) Hit")
                print("\t(2) Hold")
                choice = int(input("Choose an option: "))
                print()
                if choice == 1:
                    newcard = deck.draw()
                    playerData[i][1].append(newcard)
                    playerTotals[i] += newcard.getCardValue()

                    if playerTotals[i] > 21:
                        print(playerList[i] + " busts.")
                        break

                    print("Your cards:")
                    for card in playerData[i][1]:
                        print(card)
                elif choice == 2:
                    done = True

        # Now it's the dealer's turn.
        print(playerList[-1] + ": it is now your turn.")
        print("Dealer's cards:")
        while playerTotals[-1] < 17:
            print("The Dealer is thinking", end="")
            thinking(5)
            print("The Dealer is drawing a card", end="")
            thinking(5)
            newcard = deck.draw()
            playerData[-1][1].append(newcard)
            playerTotals[-1] += newcard.getCardValue()

        if playerTotals[-1] > 21:
            print("The Dealer busts.")
        else:
            print("The Dealer holds.")

        # The Dealer's turn is now over
        # Now it's time to see who won.
        winlist = []
        for i in range(len(playerList)):
            if playerTotals[i] > 21:
                winlist.append(-1)
            elif playerTotals[-1] > 21 and playerTotals[i] <= 21:
                winlist.append(1)
            elif playerTotals[-1] < 21 and playerTotals[-1] < playerTotals[i] <=21:
                winlist.append(1)
            elif playerTotals[-1] == playerTotals[i]:
                winlist.append(0)
            else:
                winlist.append(-1)
        print()
        print("Total points for the round:")
        for i in range(len(playerList)):
            print(playerList[i] + ": " + str(playerTotals[i]))

        input("Press ENTER to continue.")
        print("Calculating new balances")
        thinking(10)

        # resolve bets
        for i in range(len(playerList)):
            playerData[i][0].append(playerData[i][0][count] + winlist[i] * bets[i])

        for i in range(len(playerList)-1):
            print(playerList[i] + ": " + str(playerData[i][0][count+1]))


        # Now everyone discards their hand.
        for i in range(len(playerList)):
            playerData[i][1] = []

        count += 1
        print("\t(1) Play again (default)")
        print("\t(2) Stop playing")
        response = int(input("Choose an option: "))
        if response == 2:
            stopPlaying = True
            print("Thanks for playing.")
        else:
            list = []
            for i in range(len(playerList)):
                if playerData[i][0][count] == 0:
                    list.append(i)

            remlist = [playerData[i] for i in list]
            rem2list = [playerList[i] for i in list]
            for i in remlist:
                playerData.remove(i)

            for i in rem2list:
                playerList.remove(i)

            if len(playerList) == 1:
                print("Nice try, but you are all out of money.")
                stopPlaying = True



main()


