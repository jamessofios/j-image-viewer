#!/bin/sh
javac src/*.java -d . &&
jar cmvf src/*.mf ImageGUI.jar *.class
