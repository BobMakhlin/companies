{{- define "analytics-db-init" -}}
image: governmentpaas/psql:latest
command: [ "/bin/sh", "-c" ]
env:
    - name: PGPASSWORD
      value: "password"
args:
  - |
    echo 'Starting analytics database init'
    until psql "postgresql://postgres:$PGPASSWORD@app-companies-postgres/postgres" \
      -c "CREATE USER \"analytics\" WITH PASSWORD 'analyticspass'" \
      -c "CREATE DATABASE \"analytics\" OWNER \"analytics\"" \
      -c "GRANT ALL PRIVILEGES ON DATABASE \"analytics\" TO \"analytics\""
    do
      echo "Postgres is not ready yet. Retrying in 5 seconds..."
      sleep 5
    done
      echo 'Analytics database init completed'
{{- end -}}