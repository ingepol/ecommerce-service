#!/bin/bash

PARENT_DIR=$(pwd)
DEST_DIR="$PARENT_DIR/src/test/resources/compose"

echo "🚀 Generating JARs of all modules"

mvn clean package -DskipTests

MODULES=("config-server" "discovery-service" "api-gateway" "notification-service" "order-service" "payment-service" "product-service" "shipping-service" "user-service")

echo "📦 Copying jars in $DEST_DIR"
for MODULE in "${MODULES[@]}"; do
  if [ -d "$DEST_DIR/$MODULE" ]; then
    echo "🧹 Cleaning folder: $DEST_DIR/$MODULE"
    rm -rf "$DEST_DIR/$MODULE"
  fi

  mkdir -p "$DEST_DIR/$MODULE"

  JAR_PATH="$PARENT_DIR/$MODULE/target"
  JAR_FILE=$(ls "$JAR_PATH"/*.jar 2>/dev/null | grep -v 'original-' | head -n 1)

  if [ -f "$JAR_FILE" ]; then
    cp "$JAR_FILE" "$DEST_DIR/$MODULE/$MODULE.jar"
    echo "✅ Copied: $JAR_FILE → $DEST_DIR/$MODULE/$MODULE.jar"
  else
    echo "⚠️ Jar not found in $JAR_PATH"
  fi
done

echo "🎉 Process completed."
