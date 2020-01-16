#!/bin/sh
javac *.java &&
jar cmvf *.mf ImageGUI.jar *.class
