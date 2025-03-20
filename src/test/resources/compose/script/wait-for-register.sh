#!/bin/bash
CONFIG_SERVER_NAME="CONFIG-SERVER"
EUREKA_URL="http://discovery-service:8761/eureka/apps/$CONFIG_SERVER_NAME"

echo "Waiting for $CONFIG_SERVER_NAME is registered in Eureka..."

while true; do
  STATUS_CODE=$(curl -s -o /dev/null -w "%{http_code}" $EUREKA_URL)
  if [ "$STATUS_CODE" -eq 200 ]; then
    echo "$CONFIG_SERVER_NAME registered in Eureka!"
    break
  fi
  echo "Wait 5 seconds..."
  sleep 5
done
