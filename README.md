#  TP Complet - Spring Boot + React + MariaDB + Prometheus + Grafana

Ce projet met en place une application complète basée sur 5 conteneurs Docker :  
- **Backend :** Spring Boot :**  http://localhost:9090/api
- **Frontend :** React :**  http://localhost:8088/
- **Base de données :** MariaDB  
- **Monitoring :** Prometheus & Grafana:**  http://localhost:9097/query &  http://localhost:3001/login

---

##  Étapes pour exécuter le projet

###  Construire toutes les images Docker
```bash
docker compose build --no-cache
docker compose up -d
