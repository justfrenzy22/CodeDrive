// @vitest-environment node

import { expect, it, vi } from "vitest";
import { apiService } from "@/app/api/api-service";
import { createFolder } from "@/app/api/folder/create-folder";
import type { ICreateFolder } from "@/app/types/folder/ICreateFolder";

vi.mock("server-only", () => ({}));

const folder: ICreateFolder = {
  name: "Projects",
  description: "Source code",
  createdAt: new Date("2026-11-05T12:00:00Z"),
};

it("returns success when the backend creates the folder", async () => {
  const message = "The Folder was successfully created";
  const postMock = vi.spyOn(apiService, "post")
    .mockResolvedValue(new Response(message, { status: 201 }));

  const result = await createFolder(folder);

  expect(postMock).toHaveBeenCalledWith("/api/folder", { body: folder });
  expect(result).toEqual({ success: true, message });
});

it("returns the backend validation message for an invalid folder", async () => {
  const message = "Folder name is required.";
  vi.spyOn(apiService, "post")
    .mockResolvedValue(new Response(message, { status: 400 }));

  const result = await createFolder({ ...folder, name: "" });

  expect(result).toEqual({ success: false, message });
});

it("returns the backend message when saving fails", async () => {
  const message = "Folder could not be saved. Please try again later.";
  vi.spyOn(apiService, "post")
    .mockResolvedValue(new Response(message, { status: 503 }));

  const result = await createFolder(folder);

  expect(result).toEqual({ success: false, message });
});

it("returns a helpful message when the backend cannot be reached", async () => {
  vi.spyOn(apiService, "post").mockRejectedValue(new Error("Connection failed"));

  const result = await createFolder(folder);

  expect(result).toEqual({
    success: false,
    message: "The backend could not be reached. Please try again later.",
  });
});
