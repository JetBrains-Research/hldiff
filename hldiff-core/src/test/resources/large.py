import numpy
import scipy as sp
from tensorflow import keras
from tensorflow import keras as Keras


class Superclass:

    def __init__(x):
        self.x = x


class St(Superclass):

    def __init__(x):
        super().__init__(x)

@lru_cache(max_size=1000)
def fib(n):
    x = n
    if n > 0:
        return n

    def fib_helper(a):
        return x + a
    return fib(fib_helper(x))



def loops():
    variable: int = 42
    a = [1, 2, 3]
    d = {}
    c = [x for i in range(10) if i > 3]
    c = (x for i in range(10) if i > 3)

    a[0] = 100

    d['hey'] = 'there'

    a.append(42)

    for x in a:
        print(x)
        print(x)

    for x in a[1:3:-1]:
        print(x)

    for x, y in d.items():
        print(x)
        print(y)

    while True:
        break
    else:
        print('foo')

def createGenerator():
    mylist = range(3)
    for i in mylist:
        yield i * i

def main():
    print('Hello')
    print(f'Format {3}')


if __name__ == '__main__':
    main()