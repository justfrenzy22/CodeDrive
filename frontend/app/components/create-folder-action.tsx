"use client";

import { useId, useState, type FormEvent } from "react";
import {
	LiquidButton,
	LiquidForm,
	LiquidInput,
	LiquidModal,
	LiquidTextArea,
} from "@justfrenzy/liquid-glass";
import type { ICreateFolder } from "../types/folder/ICreateFolder";
import { createFolder } from "../api/folder/create-folder";

export function CreateFolderAction() {
	const formId = useId();
	const [isOpen, setIsOpen] = useState<boolean>(false);
	const [name, setName] = useState<ICreateFolder[`name`]>("");
	const [description, setDescription] =
		useState<ICreateFolder[`description`]>("");
	const [pending, setPending] = useState<boolean>(false);
	const [error, setError] = useState<string>("");

	async function submit(e: FormEvent) {
		e.preventDefault();
		const folderName = name.trim();
		if (pending) return;
		if (!folderName) {
			setError("Enter a folder name.");
			return;
		}
		setPending(true);
		setError("");
		try {
			const result = await createFolder({
				name: folderName,
				description: description,
				createdAt: new Date(),
			});
			if (!result.success) {
				setError(result.message);
				return;
			}
			setIsOpen(false);
		} catch (error) {
			setError(error instanceof Error ? error.message : "The folder could not be created. Please try again.");
		} finally {
			setPending(false);
		}
	}

	return (
		<div>
			<LiquidModal
				triggerLabel="New folder"
				title="Create a folder"
				isOpen={isOpen}
				onOpenChange={setIsOpen}
				onClose={() => {
					setName("");
					setDescription("");
					setError("");
				}}
				hideCloseButton={pending}
				isDismissable={!pending}
				isKeyboardDismissDisabled={pending}
				footer={({ close }) => (
					<>
						<LiquidButton
							appearance="normal"
							onPress={close}
							isDisabled={pending}
						>
							Cancel
						</LiquidButton>
						<LiquidButton
							type="submit"
							form={formId}
							appearance="colored"
							isDisabled={pending || !name.trim()}
						>
							{pending ? "Creating..." : "Create folder"}
						</LiquidButton>
					</>
				)}
			>
				<LiquidForm id={formId} onSubmit={submit}>
					<div className="flex flex-col gap-4">
						<LiquidInput
							className={`w-full`}
							label="Folder name"
							name="folderName"
							value={name}
							onChange={(val) => {
								setName(val);
								setError("");
							}}
							isRequired
							isDisabled={pending}
							isInvalid={Boolean(error)}
							inputProps={{ placeholder: "Name your folder", autoFocus: true }}
						/>
						<LiquidTextArea
							label="Description"
							name="folderDescription"
							value={description}
							onChange={(val) => {
								setDescription(val);
								setError("");
							}}
							isDisabled={pending}
							isInvalid={Boolean(error)}
						/>
						{error && <p role="alert">{error}</p>}
					</div>
				</LiquidForm>
			</LiquidModal>
		</div>
	);
}
