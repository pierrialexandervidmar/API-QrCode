````markdown
# 📦 QR Code Generator API

API para geração de QR Codes em **PNG**, com upload para **storage externo** (ex.: Amazon S3) e retorno da **URL pública** de acesso.  
Desenvolvida em **Java + Spring Boot**, seguindo conceitos de **Ports & Adapters (Hexagonal Architecture)**.

---

## 🚀 Tecnologias Utilizadas
- **Java 21**
- **Spring Boot**
- **Spring Web**
- **ZXing** (geração de QR Codes)
- **AWS SDK / S3** (storage)
- **Maven** (build e dependências)
- **Docker** (infraestrutura)

---

## 📂 Estrutura do Projeto
```bash
src/main/java/com/pierri/qrcode
│
├── dtos/                  # Objetos de transporte (Request / Response)
│   ├── QrCodeGenerateRequest.java
│   └── QrCodeGenerateResponse.java
│
├── infra/                 # Implementações concretas de infraestrutura
│   └── S3StorageAdapter.java
│
├── ports/                 # Definições de interfaces (contratos)
│   └── StoragePort.java
│
├── resources/             # Controllers / Endpoints REST
│   └── QrCodeResource.java
│
├── services/              # Casos de uso / Regras de negócio
│   └── QrcodeApplication.java
│
└── QrcodeApplication.java # Classe principal (Spring Boot Application)
````

---

## 🔥 Funcionalidades

* Receber um texto de entrada.
* Gerar QR Code em PNG.
* Enviar o arquivo para o **storage configurado**.
* Retornar a **URL pública** para acesso ao QR Code.

---

## 📡 Endpoints

### `POST /qrcode`

Gera um QR Code a partir de um texto.

**Request Body:**

```json
{
  "text": "https://meusite.com"
}
```

**Response 200:**

```json
{
  "url": "https://bucket-s3.s3.amazonaws.com/uuid.png"
}
```

**Possíveis Erros:**

* `400 Bad Request` → texto inválido.
* `500 Internal Server Error` → falha inesperada na geração ou upload.

---

## ⚙️ Como Rodar o Projeto

### Clonar o repositório

```bash
git clone git@github.com:pierrialexandervidmar/API-QrCode.git
cd API-QrCode
```

### Build do projeto

```bash
./mvnw clean package
```

### Rodar com Spring Boot

```bash
./mvnw spring-boot:run
```

### Rodar com Docker

```bash
docker build -t qrcode-generator .
docker run -p 8080:8080 qrcode-generator
```

---

## 🧩 Arquitetura (Ports & Adapters)

* **Ports (interfaces):** definem contratos (`StoragePort`) independentes de implementação.
* **Adapters (infra):** implementam esses contratos (`S3StorageAdapter`).
* **Services:** contêm a regra de negócio de geração de QR Codes.
* **Resources:** expõem a API REST para o mundo externo.

Isso permite **facilidade de testes**, **isolamento de regras de negócio** e **troca simples de infraestrutura** (ex.: trocar S3 por Google Cloud Storage).


