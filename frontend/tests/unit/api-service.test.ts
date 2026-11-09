// @vitest-environment node

import { beforeEach, expect, it, vi } from "vitest";
import { apiService } from "@/app/api/api-service";

// Allow server-only code to run in the test environment.
vi.mock("server-only", () => ({}));

const fetchMock = vi.fn();

beforeEach(() => {
  fetchMock.mockReset();
  fetchMock.mockResolvedValue(new Response("OK"));
  vi.stubGlobal("fetch", fetchMock);
  vi.stubEnv("BACKEND_API_URL", "http://backend:8080");
});

it("GET sends the URL, query parameters, and custom headers", async () => {
  await apiService.get("/api/example", {
    query: { page: 1 },
    headers: { Authorization: "Bearer test-token" },
  });

  const [url, options] = fetchMock.mock.calls[0];
  expect(url.toString()).toBe("http://backend:8080/api/example?page=1");
  expect(options.method).toBe("GET");
  expect(options.headers.get("Authorization")).toBe("Bearer test-token");
});

it("POST sends the body as JSON", async () => {
  const body = { name: "Projects" };

  await apiService.post("/api/folder", { body });

  const [url, options] = fetchMock.mock.calls[0];
  expect(url.toString()).toBe("http://backend:8080/api/folder");
  expect(options.method).toBe("POST");
  expect(options.body).toBe(JSON.stringify(body));
  expect(options.headers.get("Content-Type")).toBe("application/json");
});

it("rejects requests to a different server", async () => {
  await expect(apiService.get("https://another-server.example/api/folder"))
    .rejects.toThrow("API requests must use the configured backend.");

  expect(fetchMock).not.toHaveBeenCalled();
});
