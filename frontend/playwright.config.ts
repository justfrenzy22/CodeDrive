import { defineConfig } from "@playwright/test";

const port = process.env.E2E_PORT ?? "3031";
const baseURL = `http://127.0.0.1:${port}`;

export default defineConfig({
  testDir: "./tests/e2e",
  fullyParallel: false,
  workers: 1,
  forbidOnly: Boolean(process.env.CI),
  retries: process.env.CI ? 1 : 0,
  reporter: [
    ["list"],
    ["html", { open: "never" }],
    ["junit", { outputFile: "test-results/e2e-results.xml" }],
  ],
  use: {
    baseURL,
    headless: true,
    trace: "retain-on-failure",
    screenshot: "only-on-failure",
  },
  projects: [
    {
      name: "chromium",
      use: { browserName: "chromium" },
    },
  ],
  webServer: {
    command: "node .next/standalone/server.js",
    url: baseURL,
    env: {
      PORT: port,
      HOSTNAME: "127.0.0.1",
      BACKEND_API_URL: "http://127.0.0.1:8086",
    },
    reuseExistingServer: false,
    timeout: 60000,
  },
});
