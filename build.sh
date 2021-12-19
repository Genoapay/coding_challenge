#!/usr/bin/env sh

mvn -B package

docker build . -t stock-service