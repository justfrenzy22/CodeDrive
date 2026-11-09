import { MongoClient } from "mongodb";

// Only connects to the separate database started by docker-compose.e2e.yml.
const mongo = new MongoClient("mongodb://127.0.0.1:27018");
const folders = mongo.db("codedrive_e2e").collection("Folder");

export async function clearTestFolders() {
  await folders.deleteMany({});
}

export async function findTestFolder(name: string) {
  return folders.findOne({ name });
}

export async function closeTestDatabase() {
  await mongo.close();
}
