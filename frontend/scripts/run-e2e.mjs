import { spawnSync } from "node:child_process";
import { cpSync } from "node:fs";
import { fileURLToPath } from "node:url";

const frontend = fileURLToPath(new URL("../", import.meta.url));
const composeFile = fileURLToPath(new URL("../../docker-compose.e2e.yml", import.meta.url));
const composeArgs = ["compose", "--project-name", "codedrive-e2e", "--file", composeFile];

function run(command, args) {
  const result = spawnSync(command, args, { cwd: frontend, stdio: "inherit" });
  if (result.error) throw result.error;
  return result.status ?? 1;
}

let exitCode = 1;

try {
  // Next.js standalone builds need the public and static assets alongside them.
  cpSync(new URL("../public", import.meta.url), new URL("../.next/standalone/public", import.meta.url), { recursive: true });
  cpSync(new URL("../.next/static", import.meta.url), new URL("../.next/standalone/.next/static", import.meta.url), { recursive: true });

  const started = run("docker", [...composeArgs, "up", "--detach", "--build", "--wait", "--wait-timeout", "120"]);
  if (started !== 0) throw new Error("Could not start the isolated E2E backend and MongoDB. Is Docker running?");

  const playwright = fileURLToPath(new URL("../node_modules/@playwright/test/cli.js", import.meta.url));
  exitCode = run(process.execPath, [playwright, "test", ...process.argv.slice(2)]);
} catch (error) {
  console.error(error.message);
} finally {
  if (exitCode !== 0) run("docker", [...composeArgs, "logs", "--no-color"]);
  // Only this separate test stack is removed. Normal application data is untouched.
  const cleaned = run("docker", [...composeArgs, "down", "--volumes"]);
  if (cleaned !== 0) exitCode = 1;
}

process.exitCode = exitCode;
