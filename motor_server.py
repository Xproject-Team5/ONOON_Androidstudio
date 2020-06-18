import socket
import threading
import time
import RPi.GPIO as GPIO

port = 65000


GPIO.setmode(GPIO.BCM)
sw = 27
s = 18
GPIO.setup(sw, GPIO.IN, GPIO.PUD_UP)
GPIO.setup(s, GPIO.OUT)
global servo
servo = GPIO.PWM(s,100)
servo.start(0.5)
servo.ChangeDutyCycle(11)

ctrCmd = ['open', 'close']

def closeservo():
        servo.ChangeDutyCycle(11)
        time.sleep(1)
        print("0")

def openservo():
        servo.ChangeDutyCycle(21)
        time.sleep(1)
        print("90")

def handle_client(client_socket, addr):
    recvData = client_socket.recv(1024)
    print(recvData.decode('utf-8'))
    if recvData == ctrCmd[0]:
        servo.ChangeDutyCycle(20)
        openservo()
        start_time = time.time()
        while(True):
                now_time = time.time()-start_time
                if(now_time >5):
                        break
        #time.sleep(5)
        closeservo()

    time.sleep(2)
    client_socket.close()

def accept_func():
    global server_socket
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    server_socket.bind(('', port))
    server_socket.listen(10)
        time.sleep(1)
        print("90")

def handle_client(client_socket, addr):
    recvData = client_socket.recv(1024)
    print(recvData.decode('utf-8'))
    if recvData == ctrCmd[0]:
        servo.ChangeDutyCycle(20)
        openservo()
        start_time = time.time()
        while(True):
                now_time = time.time()-start_time
                if(now_time >5):
                        break
        #time.sleep(5)
        closeservo()

    time.sleep(2)
    client_socket.close()

def accept_func():
    global server_socket
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    server_socket.bind(('', port))
    server_socket.listen(10)
    while 1:
        try:
            client_socket, addr = server_socket.accept()
        except KeyboardInterrupt:
            server_socket.close()
            print("Keyboard interrupt")
        t = threading.Thread(target=handle_client, args=(client_socket, addr))
        t.daemon = True
        t.start()

if __name__ == '__main__':
    accept_func()