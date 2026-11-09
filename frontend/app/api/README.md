# Backend calls through a Server Action

The flow is: client form -> createFolder(data) Server Action -> apiService.post() -> Spring Boot.

- folder/create-folder.ts uses "use server" and runs on the Next.js server.
- api-service.ts is guarded by server-only and owns the backend URL, HTTP methods, headers, query parameters, and JSON body serialization.
- The client imports createFolder from @/app/api/folder/create-folder and passes an ICreateFolder object, including a JavaScript Date.
- createFolder returns { success, message }. Show message when success is false; close the form only on success.

Next.js handles the browser-to-server request automatically. There is no client API wrapper or /api/folder Route Handler. The Server Action makes one fetch call to Spring Boot's POST /api/folder.

Spring still receives name, optional description, and createdAt as JSON. The Date is serialized as an ISO string. Spring's plain-text success and validation messages are returned as serializable action results, so they can be displayed in production.

Set BACKEND_API_URL in .env.local or the deployment runtime to the Spring Boot server origin. It stays on the server. apiService supports get, post, put, patch, and delete with configurable headers, query parameters, body, and fetch options. No automatic timeout is added; an optional AbortSignal can be supplied by server code.

Tests mock fetch and check the API service and Server Action without contacting a real backend. Run npm test. A production build checks that the client can import the Server Action without bundling server-only code.
