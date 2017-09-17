import random

class TreeNode:
    __right = None
    __left = None
    __value = 0

    #constructor
    def __init__(self):
        self.__right = None
        self.__left = None
        self.__value = 0

    #factory method, since multupel constructors are not allowed
    def many(self, right, left, value):
        self.__right = right
        self.__left = left
        self.__value = value

    #getters
    def getRight(self):
        return self.__right

    def getLeft(self):
        return self.__left

    def getValue(self):
        return self.__value

    #setters
    def setRight(self, right):
        self.__right = right

    def setLeft(self, left):
        self.__left = left

    def setValue(self,value):
        self.__value = value

    def toString(self):
        return self.__value
#END OF TREENODE
class BSTree:
    __root = None

    def __init__(self):
        self.__root = None

    #factory method, since multiple constructors are not allowed
    def given(self,root):
        self.__root = root

    def getRoot(self):
        return self.__root

    def isEmpty(self):
        if(self.__root == None):
            return 1
        else:
            return 0

    def __helper(self, current): #needs more testing, is supposed to allow for recursive traversal of the tree to support count
        if(not (current.getLeft() == None) ):
            return ( self.__helper(current.getLeft()) + 1)
        if(not(current.getRight() == None)):
            return ( self.__helper(current.getRight()) + 1)
        else:
            return 1

    def count(self):
        if(self.isEmpty()):
            print("Tree is empty")
            return 0

        number = self.__helper(self.__root)

        return number

    def __seek(self, start, node): #needs work, is supposed to allow for recursive traversal of the tree to support add()

        if (node.getValue() < start.getValue() and start.getLeft() == None):
            start.setLeft(node)
        elif (node.getValue() >= start.getValue() and start.getRight() == None):
            start.setRight(node)
        else:
            if (node.getValue() < start.getValue()):
                self.__seek(start.getLeft(), node)
            else:
                self.__seek(start.getRight(), node)

    def add(self, node):
        start = self.__root
        if(self.isEmpty()):
            self.__root = node
            return None

        elif( node.getValue() < start.getValue()  and start.getLeft() == None):
            start.setLeft(node)
        elif( node.getValue() >= start.getValue() and start.getRight() == None ):
            start.setRight(node)

        else:
            if(start.getValue() < node.getValue()):
                self.__seek(start.getLeft(), node)
            else:
                self.__seek(start.getRight(), node)


        '''
        if( start.getLeft()== None or start.getRight()== None):
            if(start.getValue() < node.getValue()):
                start.setLeft()
        '''

        '''
        left off
        '''

    def remove(self,element):
        if(self.isEmpty()):
            print("The tree is empty")
            return None

        start = temp = self.__root

        if(start.getValue() == element): #need to work on how to manage the garbage generated by removing
            if( not(start.getLeft() == None) ):
                self.__root = self.__root.getLeft()
            elif( not(start.getRight() == None)):
                self.__root = self.__root.getRight()
            else:
                print("The tree has been emptied")
                self.__root = None

            del start
            return temp

        else:
            if(start.getValue() < start.getValue()):
                temp = self.__find(start.getLeft(),element)
            else:
                temp = self.__find(start.getRight(),element)


        return temp

    def __find(self, current, element ): #supports the remove method, remember to do more testing later

        temp = current

        if(current == None):
            print("Element not found")
            return None

        if (current.getValue() == element): #not sure
            if(not current.getLeft() == None):
                left = current.getLeft()
                current.setValue(left.getValue())
                current.setLeft(left.getLeft())
                current.setRight(left.getRight())

                del left
                return temp

            elif(not current.getLeft() == None):
                right = current.getRight()
                current.setValue(right.getValue())
                current.setLeft(right.getLeft())
                current.setRight(right.getRight())

                del right
                return temp
        else:
            if (current.getValue() < element):
                self.__find(current.getLeft(), element)
            else:
                self.__find(current.getRight(), element)
        return None

    def __help_visualize(self, start): #Doesn't quite work, need to take into account what it'll be printing(print's in the wrong order, prints root twice)
        if(not start == None):
            print(start.getValue())
            if(not start.getLeft() == None):
                print("/ ")
                self.__help_visualize(start.getLeft())
        if(not start.getRight() == None):
            print("\\")
            self.__help_visualize(start.getRight())


    def visualize(self):
        if(self.isEmpty()):
            print("Tree is empty")
            return None

        start = self.__root
        print("%d" % start.getValue())
        print("/ \\")
        self.__help_visualize(start)

#END OF BSTREE

#this is the where execution happens

tree = BSTree()
#for i in range(0,10):
 #   temp = TreeNode()
  #  temp.setValue(i)
   # tree.add(temp)

'''
temp = TreeNode()
temp.setValue(1)
tree.add(temp)
temp = TreeNode()
temp.setValue(0)
tree.add(temp)
temp = TreeNode()
temp.setValue(2)
tree.add(temp)
temp = TreeNode()
temp.setValue(3)
tree.add(temp)

tree.visualize()
'''
#temp = TreeNode()
#tree.add(temp)
#temp.setValue(5)
#tree.add(temp)
#tree.add(temp)
#tree.add(TreeNode())

#temp.setValue(5)

#print(temp.getValue())
#for i in range(0,10):
   # tree.add(temp)

#print(tree.count())

#tree.visualize()

#tree.remove(5)

#tree.visualize()
#for i in range(0,9):
 #   tree.remove(TreeNode())

#print(tree.count())
#tree.remove(1)
#tree.remove(0)
#tree.remove(0)