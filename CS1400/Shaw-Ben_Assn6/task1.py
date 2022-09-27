def main():
    baseList = list(range(1, 101))

    list1 = [thingy for thingy in baseList if thingy % 2 == 0]  # <Fill In>
    print(list1)

    list2 = [thingy for thingy in baseList if thingy % 3 == 0 and thingy < 50]  # <Fill In>
    print(list2)

    list3 = [thingy * 10 for thingy in baseList if thingy % 5 == 0 and thingy > 50]  # <Fill In>
    print(list3)

main()