"use client";

import { LiquidSkeleton } from "@justfrenzy/liquid-glass";

export function SidebarFooterPlaceholder() {
	return (
		<div
			aria-hidden="true" className="flex items-center gap-12"
		>
			<LiquidSkeleton style={{ width: 10, height: 10, borderRadius: 10 }} />
			<div style={{ display: "grid", gap: 8 }}>
				<LiquidSkeleton style={{ width: 96, height: 10 }} />
				{/* <LiquidSkeleton style={{ width: 64, height: 8 }} /> */}
			</div>
		</div>
	);
}
