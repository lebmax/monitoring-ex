#!/bin/bash

echo "Validating Prometheus configuration..."
docker run --rm -v $(pwd)/prometheus:/etc/prometheus prom/prometheus:latest --config.file=/etc/prometheus/prometheus.yml --dry-run

echo "Validating Alertmanager configuration..."
docker run --rm -v $(pwd)/alertmanager:/etc/alertmanager prom/alertmanager:latest --config.file=/etc/alertmanager/alertmanager.yml --dry-run

echo "Validation complete."