import os
pid = os.fork()

//path = "C:\Users\jweun\Spring\sw-contest-2023\src\main\resources\python\test.py"

if pid > 0:
    print('부모 프로세스의 실행 흐름', os.getpid())
elif pid == 0:
    print('자식 프로세스의 실행 흐름', os.getpid())
    os.execl(sys.executable, sys.executable, 'test.py')
else:  # pid < 0
    print('fork 오류')

print('모두 호출', os.getpid())

