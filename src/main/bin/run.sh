#!/bin/sh

###  ------------------------------- ###
###  dangchat-bots launcher script   ###
###  ------------------------------- ###

current_dir=$(pwd)
script_dir=$(dirname $0)
if [ $script_dir = '.' ]
then
  script_dir="$current_dir"
fi
parentDir="$(dirname "$script_dir")"

java -jar -Xms256M -Xmx256M -server $parentDir/lib/dangchat-bots-0.8.jar
