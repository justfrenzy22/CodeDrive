"use client";

import { useId, useState, type FormEvent } from "react";
import {
	LiquidButton,
	LiquidEmptyState,
	LiquidForm,
	LiquidInput,
	LiquidModal,
} from "@justfrenzy/liquid-glass";
import { SidebarFooterPlaceholder } from "./sidebar/sidebar-footer-placeholder";

type CreateFolderActionProps = {
	onCreate: (name: string) => void | Promise<void>;
};

export function CreateFolderAction({ onCreate }: CreateFolderActionProps) {
	const formId = useId();
	const [isOpen, setIsOpen] = useState(false);
	const [name, setName] = useState("");
	const [pending, setPending] = useState(false);
	const [error, setError] = useState("");

	async function submit(event: FormEvent<HTMLFormElement>) {
		event.preventDefault();
		const folderName = name.trim();
		if (pending) return;
		if (!folderName) {
			setError("Enter a folder name.");
			return;
		}
		setPending(true);
		setError("");
		try {
			await onCreate(folderName);
			setName("");
			setIsOpen(false);
		} catch {
			setError("The folder could not be created. Please try again.");
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
							label="Folder name"
							name="folderName"
							value={name}
							onChange={(value) => {
								setName(value);
								setError("");
							}}
							isRequired
							isDisabled={pending}
							isInvalid={Boolean(error)}
							inputProps={{ placeholder: "Name your folder", autoFocus: true }}
						/>
						{error && <p role="alert">{error}</p>}
					</div>
				</LiquidForm>
			</LiquidModal>
		</div>
	);
}
