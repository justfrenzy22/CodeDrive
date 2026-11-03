"use client";

import { LiquidForm, LiquidSidebar } from "@justfrenzy/liquid-glass";
import { useState } from "react";
import { SidebarHeaderPlaceholder } from "./components/sidebar-header-placeholder";
import { CreateFolderAction } from "./components/create-folder-action";
import { SidebarFooterPlaceholder } from "./components/sidebar/sidebar-footer-placeholder";
// import { CreateFolderAction } from "/components/create-folder-action";
// import { SidebarHeaderPlaceholder } from "@components/sidebar-header-placeholder";

export default function Home() {
	const [folders, setFolders] = useState<{ id: string; label: string; href: string }[]>([]);

	function createFolder(name: string) {
		const id = crypto.randomUUID();
		setFolders((current) => [...current, { id, label: name, href: `#folder-${id}` }]);
	}

	return (
		<div
			style={{
				minHeight: "100dvh",
				backgroundImage: "url('/porto-moniz-portugal-island-beach-coast-sunset-night-city-3840x2160-6061.jpg')",
				backgroundSize: "cover",
				backgroundPosition: "center",
				backgroundRepeat: "no-repeat",
			}}
		>
			<LiquidSidebar
				className="h-dvh w-[260px]"
				header={<SidebarHeaderPlaceholder />}
				actions={<CreateFolderAction onCreate={createFolder} />}
				items={[
					// { id: "files", label: "Files", href: "/files" },
					// { id: "recent", label: "Recent", href: "/recent" },
					...folders,
				]}
        footer={
          <SidebarFooterPlaceholder />
        }
				onNavigate={(id) => console.log(id)}
			/>

      {/* <LiquidSkeleton style={{ width: 240, height: 24 }} /> */}
		</div>
	);
}
