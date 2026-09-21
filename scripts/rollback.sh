#!/bin/bash

set -e

echo "=========================================="
echo " ROLLBACK BLUE-GREEN"
echo "=========================================="

ACTIVE_FILE="deployment/test/active-environment.txt"

if [ ! -f "$ACTIVE_FILE" ]; then
    echo "ERROR: No existe informacion del ambiente activo."
    exit 1
fi

ACTIVE=$(cat "$ACTIVE_FILE")

echo "Ambiente actual: $ACTIVE"

if [ "$ACTIVE" = "green" ]; then
    PREVIOUS="blue"
else
    PREVIOUS="green"
fi

echo "$PREVIOUS" > "$ACTIVE_FILE"

echo "Rollback ejecutado correctamente."
echo "Ambiente anterior : $ACTIVE"
echo "Ambiente restaurado: $PREVIOUS"
echo "=========================================="