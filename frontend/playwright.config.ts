import { defineConfig, devices } from "@playwright/test";

export default defineConfig({
  testDir: "./tests/browser",
  use: { baseURL: "http://127.0.0.1:3031", headless: true },
  projects: [
    {
      name: "chromium",
      testIgnore: /webkit-compatibility\.spec\.ts/,
      use: { browserName: "chromium" },
    },
    {
      name: "webkit-desktop",
      testMatch: /webkit-compatibility\.spec\.ts/,
      use: { browserName: "webkit", viewport: { width: 1280, height: 900 } },
    },
    {
      name: "webkit-mobile",
      testMatch: /webkit-compatibility\.spec\.ts/,
      use: { ...devices["iPhone 13"], browserName: "webkit" },
    },
  ],
  webServer: {
    command: "npx next start --port 3031",
    url: "http://127.0.0.1:3031/dev/liquid-glass",
    reuseExistingServer: false,
    timeout: 60000,
  },
});
