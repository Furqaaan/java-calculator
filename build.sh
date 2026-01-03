#!/bin/bash

echo "Cleaning..."
rm -rf build dist
mkdir build dist

echo "Compiling..."
javac -sourcepath src -d build $(find src -name "*.java")

echo "Packaging..."
jar cfm dist/Calculator.jar manifest.mf -C build .

if [[ " $1 " == " --exe " ]]; then
    echo "Creating executable..."
    jpackage --input dist --name Calculator --main-jar Calculator.jar --main-class App --type exe --icon resources/icon.ico --win-shortcut --win-menu
fi

echo "Done."