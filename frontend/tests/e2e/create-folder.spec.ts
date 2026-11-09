import { test, expect } from "@playwright/test";
import { clearTestFolders, findTestFolder, closeTestDatabase } from "./helpers/test-database";

test.beforeAll(clearTestFolders);
test.afterAll(closeTestDatabase);

test("creates a folder", async ({ page }) => {
  // open the form and fill in the details
  await page.goto("/");
  await page.getByRole("button", { name: "New folder", exact: true }).click();
  await page.getByLabel("Folder name").fill("Projects");
  await page.getByLabel("Description").fill("Source code");

  // save and check if the form closes
  await page.getByRole("button", { name: "Create folder", exact: true }).click();
  await expect(page.getByRole("dialog")).not.toBeVisible();

  // check that the backend saved the folder
  const savedFolder = await findTestFolder("Projects");
  expect(savedFolder).toMatchObject({ name: "Projects", description: "Source code" });
});
