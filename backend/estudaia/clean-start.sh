echo 🔄 Limpando ambiente Docker...

docker-compose down
docker container prune -f
docker network prune -f

echo 🚀 Iniciando aplicação...
docker-compose up

echo ⏳ Aguardando serviços...
timeout /t 30 /nobreak

echo ✅ Serviços iniciados:
echo    📊 PHPMyAdmin: http://localhost:8081
echo    🚀 Backend API: http://localhost:8080
echo    📚 API Docs: http://localhost:8080/swagger-ui.html