class Node:
    """Class to represent a node in Python3"""

    def __init__(self, data):
        self.data = data  # Node value
        self.next = None  # Next node


class LinkedStack:
    """Class to represent a linked stack in Python3"""

    def __init__(self):
        self._top = None  # Top of stack
        self._size = 0  # The stack size

    def push(self, elem):
        """Adds an element at the top of a stack"""
        if self._size == 0:
            self._top = Node(elem)
        else:
            aux = self._top
            self._top = Node(elem)
            self._top.next = aux
        self._size += 1

    def pop(self):
        """Removes the topmost element of a stack"""
        if self._size == 0:
            raise Exception("Empty stack")
        elem, self._top = self._top.data, self._top.next
        self._size -= 1
        return elem

    def top(self):
        """Returns the element on the top of the stack but does not remove it"""
        if self._size == 0:
            raise Exception("Empty stack")
        return self._top.data

    def empty(self):
        """Returns true if the stack is empty, otherwise, it returns false"""
        if self._size == 0:
            return True
        return False

    def length(self):
        """Returns the size of stack"""
        return self._size

    def __del__(self):
        """Destructor method"""

    def __str__(self):
        """Method for representing the stack, excluding NoneType objects (user)"""
        rep = "\033[1;34m" + "top ->  " + "\033[0;0m"
        if self._size == 0:
            rep += "None"
            return rep
        pointer = self._top
        for i in range(self._size):
            if i == 0:
                rep += f"{str(pointer.data).rjust(2)}"
            else:
                rep += f"\n{str(pointer.data).rjust(10)}"
            pointer = pointer.next
        return rep

    def __repr__(self):
        """Method for representing the stack, excluding NoneType objects (developer)"""
        return str(self)
