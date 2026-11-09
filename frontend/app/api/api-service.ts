import "server-only";
type QueryValue = string | number | boolean | null | undefined;

export type ApiRequestOptions = Omit<RequestInit, "method" | "body"> & {
  body?: unknown;
  query?: Record<string, QueryValue>;
};

type GetOptions = Omit<ApiRequestOptions, "body">;

function createBackendUrl(path: string, query?: ApiRequestOptions["query"]): URL {
  const baseUrl = new URL(process.env.BACKEND_API_URL ?? "http://localhost:8085");
  const url = new URL(path, baseUrl);

  if (url.origin !== baseUrl.origin) {
    throw new Error("API requests must use the configured backend.");
  }

  for (const [key, value] of Object.entries(query ?? {})) {
    if (value !== null && value !== undefined) url.searchParams.set(key, String(value));
  }
  return url;
}

function createHeaders(headers: HeadersInit | undefined, hasBody: boolean): Headers {
  const requestHeaders = new Headers(headers);
  if (hasBody && !requestHeaders.has("Content-Type")) {
    requestHeaders.set("Content-Type", "application/json");
  }
  return requestHeaders;
}

function createRequestOptions(method: string, options: Omit<ApiRequestOptions, "query">): RequestInit {
  const { body, headers, ...fetchOptions } = options;
  return {
    cache: "no-store",
    ...fetchOptions,
    method,
    headers: createHeaders(headers, body !== undefined),
    body: body === undefined ? undefined : JSON.stringify(body),
  };
}

async function request(method: string, path: string, options: ApiRequestOptions = {}): Promise<Response> {
  const { query, ...requestOptions } = options;
  return fetch(createBackendUrl(path, query), createRequestOptions(method, requestOptions));
}

export const apiService = {
  get: (path: string, options?: GetOptions) => request("GET", path, options),
  post: (path: string, options?: ApiRequestOptions) => request("POST", path, options),
  put: (path: string, options?: ApiRequestOptions) => request("PUT", path, options),
  patch: (path: string, options?: ApiRequestOptions) => request("PATCH", path, options),
  delete: (path: string, options?: ApiRequestOptions) => request("DELETE", path, options),
};
