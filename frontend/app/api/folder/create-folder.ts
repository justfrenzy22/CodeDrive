"use server";

import "server-only";
import { apiService } from "../api-service";
import type { ICreateFolder } from "../../types/folder/ICreateFolder";

export type CreateFolderResult = { success: boolean; message: string };

function createPayload(folder: ICreateFolder) {
  return { name: folder.name, description: folder.description, createdAt: folder.createdAt };
}

async function readResult(response: Response): Promise<CreateFolderResult> {
  const message = await response.text();
  return {
    success: response.ok,
    message: message || "The folder could not be created. Please try again.",
  };
}

export async function createFolder(folder: ICreateFolder): Promise<CreateFolderResult> {
  if (folder === null || typeof folder !== "object" || Array.isArray(folder)) {
    return { success: false, message: "Folder must be an object." };
  }

  try {
    const response = await apiService.post("/api/folder", { body: createPayload(folder) });
    return await readResult(response);
  } catch {
    return { success: false, message: "The backend could not be reached. Please try again later." };
  }
}
