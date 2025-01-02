{{- define "backend-library.db-init" -}}
image: governmentpaas/psql:latest
command: [ "/bin/sh", "-c" ]
env:
  - name: PGPASSWORD
    value: "{{ .Values.db.password }}"
args:
  - |
    echo 'Starting {{ .Values.db.name }} database init'

    until psql "postgresql://postgres:$PGPASSWORD@{{ .Values.db.host }}:{{ default 5432 .Values.db.port }}/postgres" -c "SELECT 1;" > /dev/null 2>&1; do
      echo "Postgres is not ready yet. Retrying in 5 seconds..."
      sleep 5
    done

    # Create database if it doesn't exist.
    psql "postgresql://postgres:$PGPASSWORD@{{ .Values.db.host }}:{{ default 5432 .Values.db.port }}/postgres" -tc "SELECT 1 FROM pg_database WHERE datname = '{{ .Values.db.name }}'" | grep -q 1 || \
    psql "postgresql://postgres:$PGPASSWORD@{{ .Values.db.host }}:{{ default 5432 .Values.db.port }}/postgres" -c "CREATE DATABASE \"{{ .Values.db.name }}\";"

    # Create role if it doesn't exist.
    psql "postgresql://postgres:$PGPASSWORD@{{ .Values.db.host }}:{{ default 5432 .Values.db.port }}/postgres" -tc "SELECT 1 FROM pg_roles WHERE rolname = '{{ .Values.db.user.name }}'" | grep -q 1 || \
    psql "postgresql://postgres:$PGPASSWORD@{{ .Values.db.host }}:{{ default 5432 .Values.db.port }}/postgres" -c "CREATE USER \"{{ .Values.db.user.name }}\" WITH PASSWORD '{{ .Values.db.user.password }}';"

    # Grant access to the schema.
    psql "postgresql://postgres:$PGPASSWORD@{{ .Values.db.host }}:{{ default 5432 .Values.db.port }}/{{ .Values.db.name }}" -c "GRANT ALL ON SCHEMA public TO \"{{ .Values.db.user.name }}\";"

    echo '{{ .Values.db.name }} database init completed'
{{- end -}}
