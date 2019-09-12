cd Hexapode-Lynxmotion-Roboter-Java-Api/
git pull https://github.com/Creepler13/Hexapode-Lynxmotion-Roboter-Java-Api.git
cd Hexapode\ Java\ Api/src
sudo javac -classpath .:classes:/opt/pi4j/lib/'*' -d . Start.java
echo Compiled
sudo java -classpath .:classes:/opt/pi4j/lib/'*' Start
echo Started
