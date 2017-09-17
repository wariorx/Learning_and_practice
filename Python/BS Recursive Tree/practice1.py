import random
import sys
import os

#print("Hello world")
#lol
'''
MULTILINE
'''

#name = "lol"

#print(name)

#name = 25

#print(name + 2)

'''
Data types:
String, Integers, Lists(better arrays), Tuples, Dictionaries
'''

'''
Operators:
+ - * / % **(exponential) // floor (division)
'''

#print(25//2)

#print(2**4)

#multi_line = ''' look at this
#there's many lines
#lol '''

#print(multi_line)

#random_quote = "hello"

#print(random_quote + " " +  multi_line)

#print("no new lines?", )
#print("there you go")
'''
FORMATING
print("%s %s" % ("hi","there")) STRINGS || "{}{}".format("hi","there)
print("%d %d" % (1, 2)) NUMBERS
'''
list = ["this","works",1,2,3,4] #YOU CAN HAVE ANITHING INSIDE LISTS

#print("%s %s %s" % (list[0], list[1], list[2]))

#list[0] = "that"

#print(list[0:2]) #print a subset, first included, second one not included

#store = list[0:3]

#print(store)

super_list = ["WOW", "AMAZING", list] #LIST INSIDE A LIST, WORKS LIKE A 2D ARRAY, DUH

#print(super_list)
'''
USEFUL LIST STUFF
'''
#list.insert(1,"that")

#super_list.sort()  #IT DOES SECONDARY LIST, THEN NUMBERS, THEN ALPHABETICAL, ==> all in ascending

#super_list[0].sort() #now sort the list that's inside this list

#list.reverse()

#del list

#len(list)
#max(list) this is the last thing sort would return
#min(list) this is the first thing sort would return, so if there's a list inside that's what will be returned
#print(len(list))
#print("%s %s" % ( max(list), min(list) ) )
#print(super_list)

'''
TUPLES: THEY'RE JUST CONSTANT LISTS
'''

#tuple = (3,1,2,32,2,1)

#print(tuple[0])

#new_tuple = list(tuple)

#list_tuple = tuple(new_tuple)

#LISTS FUNCTIONS ALSO WORK FOR TUPLES

'''
DICTIONARY:
'''

#dictionary = {"KEY" : "this is what \'KEY\' MAPS TO",
 #             "SECRET" : "this is what \'SECRET\' maps to"}

#print(dictionary["KEY"])

#del dictionary["SECRET"]

#dictionary["KEY"] = "stuff"
#LEN ALSO WORKS ON THIS

#print(dictionary.get("KEY"))
#print(dictionary.get("SECRET") ) # IF THERE'S NOTHING MAPPED, IT PRINTS None
#print(dictionary.keys())
#print(dictionary.values())

'''
FLOW CONTROL
'''
#If
#age = 21

#if age > 20 :
 #   print("DRINK")
#elif age > 30 :
 #   print("randomness")
#else :
#    print(":(")

#logical operators YOU LITERALLY TYPE and, or, not depending on what you want
#if(age == 1) and (age > 0) :
 #       print("hi there")
'''
RANDOM
'''

random = random.randrange(0,10)

print(random)
'''
LOOPS
'''
#for loops
#for x in range(0,10) : #10 is not inclusive
 #   print(x)

#for x in ("potato", 1) : #this is just like shell!!!
 #   print(x)

#while loops
#while(random > 5):
  #  print(random)
#i = 0

#while(i<10):
  #  print(i)
   # i += 1
#break and continue can stil be used

'''
FUNCTIONS
'''

#def add(first, second):
 #   result = first + second
  #

'''
USER INPUT
'''

#print("enter your name")

#name = sys.stdin.readline()

#print("hi, " + name)

'''
PLAYING WITH STRINGS
'''

string = "this is a test" #cause strings are literally character arrays, duh

#print(string[0:5])
#print(-5:) #print the last five characters
#print(:-5) #print everything except the last 5 characters

#print("%c for charcters, %s for strings, %d for numbers, %.5f for decimals" % ("A", "lol", 1, 1.123456678))
#string.capitalize()
#print(string)
#string.replace("test", "TEST")
#print(string.find("TEST") )
#string.split(" ")

'''
FILES
'''
#file = open("name.txt", "ab+") #create if it doesn't exits, apend to it if it's already there
#file2 = open("name.txt" "wb") #write to file
#file3 = ("name.txt", "r+") read and write
#print(file.name)
#print(file.mode)

#file.write(bytes("text to write\n", "UTF-8"))
#file.close()

#delete a file
#os.remove("name.txt")

'''
OBJECT ORIENTED


class Thing:
    __name = "" #NOTE : PRECEDING WITH TWO UNDERSCORES MAKES THEM PRIVATE
    __value = 0

    def __init__(self): #CONSTRUCTOR
        self.__name = ""
        self.__value = 0

    def __init__(self, name, value):
        self.__name = name
        self.__value = value

    def setName(self,name):
        self.__name = name

    def getName(self):
        return self.__name

    def setValue(self,value):
        self.__value = value

    def getValue(self):
        return self.__value

    def toString(self):
        #return ("Name: " + self.__name + "\nValue: " + self.__value)
        return ("Name: %s \nValue: %s" % (self.__name,self.__value ))

cat = Thing("Shade", 200)

print(cat.toString())

'''