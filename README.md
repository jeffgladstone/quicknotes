# QuickNotes 📝

A simple full-stack note-taking app built with:

- 🔙 Spring Boot (Java + Liquibase + H2)
- 🔧 React frontend
- 🧪 JUnit + MockMvc tests
- 🔍 Swagger UI at `/swagger-ui.html`

## Running Locally

**Backend**:
```bash
cd backend
mvn clean install
mvn spring-boot:run
```
**Frontend**:
```bash
cd frontend
npm install     # (only once, to install dependencies)
npm run dev     # start the Vite dev server at http://localhost:3000
```
