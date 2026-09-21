#!/bin/bash

set -e

echo "=========================================="
echo " DESPLIEGUE BLUE-GREEN - AMBIENTE DE TEST"
echo "=========================================="

DEPLOY_DIR="deployment/test"
BLUE_DIR="$DEPLOY_DIR/blue"
GREEN_DIR="$DEPLOY_DIR/green"
ACTIVE_FILE="$DEPLOY_DIR/active-environment.txt"

mkdir -p "$BLUE_DIR"
mkdir -p "$GREEN_DIR"

# Si no existe un ambiente activo, BLUE será el inicial.
if [ ! -f "$ACTIVE_FILE" ]; then
    echo "blue" > "$ACTIVE_FILE"
fi

ACTIVE=$(cat "$ACTIVE_FILE")

echo "Ambiente actualmente activo: $ACTIVE"

if [ "$ACTIVE" = "blue" ]; then
    TARGET="green"
    TARGET_DIR="$GREEN_DIR"
else
    TARGET="blue"
    TARGET_DIR="$BLUE_DIR"
fi

echo "Nuevo despliegue hacia ambiente: $TARGET"

JAR_FILE=$(find target -maxdepth 1 -name "*.jar" | head -n 1)

if [ -z "$JAR_FILE" ]; then
    echo "ERROR: No se encontro el artefacto JAR."
    exit 1
fi

rm -f "$TARGET_DIR"/*.jar
cp "$JAR_FILE" "$TARGET_DIR/"

echo "Artefacto desplegado correctamente en $TARGET_DIR"
echo "$TARGET" > "$ACTIVE_FILE"

echo "=========================================="
echo " DESPLIEGUE COMPLETADO"
echo " Ambiente anterior : $ACTIVE"
echo " Ambiente activo   : $TARGET"
echo "=========================================="