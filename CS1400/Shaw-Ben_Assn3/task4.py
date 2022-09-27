# Ben Shaw
# CS1400 - AO1 XL
# Assignment 3

import random

runAgain = "yes"

while runAgain == "yes":

    A = []
    B = []
    iterations = 100000
    count = 1

    while count < iterations + 1:
        # These values are the pens chosen by each elephant and the zookeeper.
        elephant1 = random.randint(1, 6)
        elephant2 = random.randint(1, 6)
        zooKeeper = random.randint(1, 6)

        if zooKeeper == elephant1 or zooKeeper == elephant2:
            # If the zookeeper saw one of the elephants: that's one event to log in the list A.
            A.append(count)
            if zooKeeper == elephant1 and zooKeeper == elephant2:
                # If not only did the zookeeper see one elephant, but the zookeeper in fact saw both: log this into B.
                B.append(count)

        count += 1

    # Now we will calculate the percentages for both events.
    atLeastOneElephant = round(len(A) / iterations * 100, 2)
    bothElephants = round(len(B) / len(A) * 100, 2)

    # Now we will print the results. Both statistics need to be within the error for the zookeeper to be right.
    print("Percentage of the time there was at least one elephant in the checked pen: " + str(atLeastOneElephant) + "%")
    print("When at least one elephant was in the checked pen, percentage of the time both elephants were in the pen: "
          + str(bothElephants) + "%")

    if (35.34 >= atLeastOneElephant >= 31.33) and (18.67 >= bothElephants >= 14.66):
        print("The zookeeper is right.")
    else:
        print("The custodian is right.")

    runAgain = input("Run the simulation again? (yes or no): ").lower()

    # Here is a crude method of dealing with bad inputs:
    if runAgain != "no" and runAgain != "yes":
        print("That was a 'yes or no' question. The program will now end.")
