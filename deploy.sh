#!/bin/bash
gradle clean build
scp build/libs/Light.jar ev3:/home/lejos/programs/
