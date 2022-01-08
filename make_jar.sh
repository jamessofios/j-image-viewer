#!/bin/sh
javac src/*.java -d . &&
jar cmvf src/*.mf j_image_viewer.jar *.class
