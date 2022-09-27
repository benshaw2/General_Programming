# Ben Shaw
# CS1400 - AO1 XL
# Assignment 5

class Wordinator:
    def __init__(self, Word):
        self.__word = Word

    def getValue(self):
        return self.__word

    def __str__(self):
        return str(self.getValue())

    def __lt__(self, other):
        return self.__word.lower() < other.getValue().lower()

    def __add__(self, other):
        return (self.__word + other.getValue()).capitalize()

    def __mul__(self, other):
        return self.__word * other

    def __truediv__(self, other):
        len1 = len(self.__word) if len(self.__word) > len(other.getValue()) else len(other.getValue())
        msg = ""
        for i in range(len1):
            msg += self.__word[i:i+1] + other.getValue()[i:i+1]

        return msg.capitalize()

    def __mod__(self, other):
        len1 = len(self.__word)
        len2 = len(other.getValue())
        if len1 != 3:
            Len1 = len1 // 3 if len1 % 3 != 0 else len1 //4
        else:
            Len1 = 1
        if len2 != 3:
            Len2 = len2 // 3 if len2 % 3 != 0 else len2 //4
        else:
            Len2 = 1
        return self.__word[Len1:len1 - Len1], other.getValue()[Len2:len2 - Len2]

    def __sub__(self, other):
        def switch(string):
            myself = ""
            for i in string:
                if i.isupper():
                    myself += i.lower()
                elif i.islower():
                    myself += i.upper()
            return myself

        return switch(self.__word), switch(other.getValue())

    def backWordSlice(self):
        return self.__word[::-1]

    def backWordManual(self):
        myself = ""
        for i in range(len(self.__word)-1, -1, -1):
            myself += self.__word[i]

        return myself
