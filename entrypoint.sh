#!/bin/bash

set -e

if [ -z $MEM_MIN ]; then
  if [[ $ENV == *"production"* ]]; then
    export MEM_MIN=1024M
  else
    export MEM_MIN=512M
  fi
fi
if [ -z $MEM_MAX ]; then
  if [[ $ENV == *"production"* ]]; then
    export MEM_MAX=1024M
  else
    export MEM_MAX=512M
  fi
fi

if [ "$1" = "java" ]; then
  java \
    -Djava.security.egd=file:/dev/./urandom \
    -Denv=$ENV \
    -Xms$MEM_MIN -Xmx$MEM_MAX \
    -jar /*.jar
fi
exec "$@"
