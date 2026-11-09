# Frontend tests

Run commands from `frontend`.

```text
tests/
  unit/                 Vitest tests and their setup
  e2e/                  Playwright tests
    helpers/            E2E database setup
```

## Unit tests

```powershell
npm test
```

These use mocks and do not need Docker or the backend.

## Folder creation end-to-end tests

First install dependencies and Chromium:

```powershell
npm ci
npx playwright install chromium
```

With Docker Desktop running:

```powershell
npm run build
npm run test:e2e
```

The script starts an isolated Spring Boot backend and MongoDB using
`docker-compose.e2e.yml`. Playwright starts the production frontend, submits
the actual folder form, and checks the saved document in MongoDB.

There is one simple full-stack test: open the form, enter a name and description,
click Create, check that the form closes, and check that the folder was saved.
The database setup lives in `e2e/helpers/test-database.ts` so the test itself only
shows those steps. The backend is real, not mocked.

Ports: frontend `3031`, backend `8086`, MongoDB `27018`. Only the separate
`codedrive_e2e` database is cleared before the test. Test containers are removed
after the run, including on test failure. Normal application containers and
database volumes are not used.

If another test session is already using port 3031, set `$env:E2E_PORT = "3034"`
before running `npm run test:e2e`.

Open `playwright-report/index.html` for results, or run
`npx playwright show-report`. Failed tests include screenshots and traces.

The GitHub Actions workflow runs build, unit tests, E2E tests, Docker build,
publication, and frontend deployment in order. Publication and deployment
only run on pushes to `main`. The backend workflow deploys the backend and
MongoDB separately. Both use the same `codedrive` Compose project.
