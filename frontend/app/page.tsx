"use client";

import { LiquidSidebar } from "@justfrenzy/liquid-glass";
import { useState } from "react";
import { CustomizableSkeleton } from "./components/customizable-skeleton";
import { CreateFolderAction } from "./components/create-folder-action";
import { SidebarFooterPlaceholder } from "./components/sidebar/sidebar-footer-placeholder";

export default function Home() {
	const [folders, setFolders] = useState<
		{ id: string; label: string; href: string }[]
	>([]);
	
	return (
		<div
			style={{
				minHeight: "100dvh",
				backgroundImage:
					"url('/porto-moniz-portugal-island-beach-coast-sunset-night-city-3840x2160-6061.jpg')",
				backgroundSize: "cover",
				backgroundPosition: "center",
				backgroundRepeat: "no-repeat",
			}}
		>
			<LiquidSidebar
				className="h-dvh w-[260px]"
				header={<CustomizableSkeleton variant="brand" textSize="lg" />}
				actions={<CreateFolderAction />}
				items={[
					...folders,
				]}
				footer={<SidebarFooterPlaceholder />}
				onNavigate={(id) => console.log(id)}
			>
				<CustomizableSkeleton textSize="lg" />
				<CustomizableSkeleton textSize="md" />
				<CustomizableSkeleton textSize="sm" />
				<CustomizableSkeleton textSize="md" />
				<CustomizableSkeleton textSize="lg" />
			</LiquidSidebar>
		</div>
	);
}
