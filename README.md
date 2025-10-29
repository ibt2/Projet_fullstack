#  TP Full stack
- Spring Boot , React , MariaDB, Prometheus , Grafana

Ce projet met en œuvre une application full-stack complète reposant sur cinq conteneurs Docker, assurant à la fois la gestion backend, l’interface frontend, la base de données et le monitoring.
Composition de l’application:
- **Backend :** Spring Boot :**  http://localhost:9090/api
- **Frontend :** React :**  http://localhost:8088/
- **Base de données :** MariaDB  
- **Monitoring :** Prometheus & Grafana:**  http://localhost:9097/query &  http://localhost:3001/login

---

##  Étapes pour exécuter le projet

###  Construire toutes les images Docker et Démarrage des conteneurs
```bash
docker compose build --no-cache
docker compose up -d
