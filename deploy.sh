#!/bin/bash
gradle clean build
DATE=$(date "+%Y-%m-%d %H:%M:%S")
ssh ev3 date -s \"${DATE}\"
scp build/libs/lego-ev3-battery-tester.jar ev3:/home/lejos/programs/
