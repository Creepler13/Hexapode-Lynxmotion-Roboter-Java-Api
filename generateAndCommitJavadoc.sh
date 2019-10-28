#!/bin/bash
args=("$@")

function goto
{
label=$1
cmd=$(sed -n "/$label:/{:a;n;p;ba};" $0 | grep -v ':$')
eval "$cmd"
exit
}

echo off
echo Generating Javadoc...
javadoc -use -classpath "Hexapode Java API\lib\*" -sourcepath "Hexapode Java Api\src" --allow-script-in-comments -d docs -use -windowtitle "Hexapod Lynxmotion API Documentation" -header "Lynxmotion Hexapod Java API (unofficial API)" -doctitle "Lynxmotion Hexapod Java API" -overview "Hexapode Java Api\src\overview.html" -J-Xmx512m -subpackages api test devServer
sleep 
echo Adding newly generated doc files...
git add -A docs
echo Committing...
git commit -a -m "Regenerated the Javadoc"
echo Pushing...
git push
echo Finished!
sleep