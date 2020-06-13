from socket import *

import threading

import time

import RPi.GPIO as GPIO

 

#import servomotor

 

GPIO.setmode(GPIO.BCM)

sw = 27

s = 18

GPIO.setup(sw, GPIO.IN, GPIO.PUD_UP)

GPIO.setup(s, GPIO.OUT)

global servo

servo = GPIO.PWM(s,100)

#cur_X=0

#servoPin

#SERVO_MAX_DUTY = 12

#SERVO_MIN_DUTY = 3

 

servo.start(0.5)

 

servo.ChangeDutyCycle(11)

ctrCmd = ['open', 'close']

 

def send(sock):

    while True:

        sendData = input('client:')

        # sendData = "hi"

        sock.send(sendData.encode('utf-8'))

 

def closeservo():

        servo.ChangeDutyCycle(11)

        time.sleep(1)

        print("0")

        #global cur_X

        #cur_X+=2.5

        #if cur_X>12:

        #       cur_X=12.5

        #servo.ChangeDutyCycle(cur_X)

        #time.sleep(1)

 

def openservo():

        servo.ChangeDutyCycle(21)

        time.sleep(1)

        print("90")

        #global cur_X

        #cur_X-=2.5

        #if cur_X<2.5:

        #        cur_X=2.5

        #servo.ChangeDutyCycle(cur_X)

        #time.sleep(1)  


def receive(sock):
    while True:
        recvData = sock.recv(1024)
        print('server :', recvData.decode('utf-8'))
        if recvData == ctrCmd[0]:
                servo.ChangeDutyCycle(20)

                print(recvData)
                openservo()

        if recvData == ctrCmd[1]:
                print(recvData)
                closeservo()

port = 8081

clientSock = socket(AF_INET, SOCK_STREAM)
clientSock.connect(('192.168.0.97', port))

print('connect')

sender = threading.Thread(target=send, args=(clientSock,))
receiver = threading.Thread(target=receive, args=(clientSock,))

sender.start()
receiver.start()

while True:
        #time.sleep(1)
        pass