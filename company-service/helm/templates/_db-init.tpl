{{- define "company-db-init" -}}
image: governmentpaas/psql:latest
command: [ "/bin/sh", "-c" ]
env:
    - name: PGPASSWORD
      value: "password"
args:
  - |
    echo 'Starting company database init'
    until psql "postgresql://postgres:$PGPASSWORD@app-companies-postgres/postgres" \
      -c "CREATE USER \"company\" WITH PASSWORD 'companypass'" \
      -c "CREATE DATABASE \"company\" OWNER \"company\"" \
      -c "GRANT ALL PRIVILEGES ON DATABASE \"company\" TO \"company\""
    do
      echo "Postgres is not ready yet. Retrying in 5 seconds..."
      sleep 5
    done
      echo 'Company database init completed'
{{- end -}}