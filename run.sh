#!/usr/bin/env sh
docker run -p 8088:8088 -d stock-service

open ./max_profit_calculator/build/index.html