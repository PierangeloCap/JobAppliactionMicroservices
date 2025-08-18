# 🧩 Microservizi con Kubernetes, PostgreSQL e RabbitMQ

Questo progetto contiene tre microservizi containerizzati:

- `companyms`
- `jobms`
- `reviewms`

Ogni servizio comunica con **PostgreSQL** e interagisce tramite code asincrone usando **RabbitMQ**. È possibile eseguire tutto localmente con Docker Compose o distribuirlo su Kubernetes.

---

## 🧱 Microservizi

### 🔹 `companyms`
- Gestione delle aziende
- Comunica con `jobms` e `reviewms`
- Pubblica eventi su RabbitMQ

### 🔹 `jobms`
- Gestione offerte di lavoro
- Ascolta eventi da `companyms`
- DB: PostgreSQL

### 🔹 `reviewms`
- Recensioni sulle aziende
- Ascolta eventi da `companyms`
- DB: PostgreSQL

---

## 🐳 Esecuzione Locale con Docker Compose

### ✅ Requisiti

- Docker
- Docker Compose

### ▶️ Avvio

```bash
docker-compose up --build

Servizi disponibili:
Servizio	Porta	URL
companyms	8001	http://localhost:8001
jobms	8002	http://localhost:8002
reviewms	8003	http://localhost:8003
PostgreSQL	5432	(interno, non esposto)
RabbitMQ UI	15672	http://localhost:15672
RabbitMQ AMQP	5672	(porta AMQP standard)

🔐 RabbitMQ UI Login:

Username: guest

Password: guest

💾 Database: PostgreSQL

Ogni microservizio utilizza un database dedicato o uno schema separato. Le connessioni vengono configurate tramite variabili d'ambiente.

Connessione tipo:
Host: postgres
Port: 5432
User: postgres
Password: postgres
Database: company_db / job_db / review_db

📬 Messaggistica: RabbitMQ

I microservizi comunicano in modo asincrono tramite RabbitMQ. Esempi d’uso:

companyms pubblica un evento quando un’azienda viene creata

jobms e reviewms ascoltano per sincronizzare i dati

Esempio Exchange
Exchange	Tipo	Coda	Consumer
company.events	topic	jobms.company.created	jobms
		reviewms.company.created	reviewms
☸️ Deploy su Kubernetes
✅ Requisiti

Cluster Kubernetes (minikube, kind, GKE, etc.)

kubectl configurato

(Opzionale) Helm

▶️ Deploy Manuale
kubectl apply -f k8s/postgres/
kubectl apply -f k8s/rabbitmq/
kubectl apply -f k8s/companyms/
kubectl apply -f k8s/jobms/
kubectl apply -f k8s/reviewms/


Verifica i pod e i servizi:

kubectl get pods
kubectl get svc


📎 Consigliato: usare Ingress o LoadBalancer per accedere ai servizi dall’esterno.

📁 Struttura del Progetto
.
├── companyms/
│   └── Dockerfile
├── jobms/
│   └── Dockerfile
├── reviewms/
│   └── Dockerfile
├── k8s/
│   ├── postgres/
│   ├── rabbitmq/
│   ├── companyms/
│   ├── jobms/
│   └── reviewms/
├── docker-compose.yaml
└── README.md

🧪 Testing API e Code

Puoi testare localmente con:

📬 API REST
curl http://localhost:8001/companies
curl http://localhost:8002/jobs
curl http://localhost:8003/reviews

🕸️ RabbitMQ UI

Vai su http://localhost:15672 per gestire code, exchange, binding e monitoraggio.

🔐 Sicurezza & Best Practice

Usa Secrets su Kubernetes per credenziali DB e RabbitMQ

Isola code RabbitMQ per microservizio

Implementa retry/backoff per la messaggistica

Configura healthcheck & liveness probe per i pod

👥 Autori

Progetto sviluppato da PierCap