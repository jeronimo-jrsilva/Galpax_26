#!/bin/bash
# Script para compilar e executar o projeto Galpax no Linux

# Navega até o diretório do script
cd "$(dirname "$0")"

echo "🔨 Compilando o projeto Java..."
mkdir -p bin
javac -cp "lib/mysql-connector-j-9.0.0.jar:src" -d bin src/projeto/*.java

if [ $? -eq 0 ]; then
    echo "✅ Compilação concluída com sucesso!"
    echo "🚀 Iniciando a tela de Login..."
    java -cp "lib/mysql-connector-j-9.0.0.jar:bin" projeto.Login
else
    echo "❌ Erro na compilação!"
    exit 1
fi
