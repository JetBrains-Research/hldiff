def taskDecorator(a, b):
    return 0


def execute(a):
    pass


def decoratedTaskMap(a, b, c) :
    pass


decorate = 0
command = 0
put = 0


def foo():
    decorated = taskDecorator(decorate, command)

    if decorated != command:
        decoratedTaskMap(put, decorated, command)

    execute(decorated)
