#!/bin/bash
scp ev3:/home/lejos/programs/samples.csv ./samples-$(date +%Y%m%d%H%M%S).csv
