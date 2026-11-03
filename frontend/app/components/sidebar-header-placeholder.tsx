"use client";

import { LiquidSkeleton } from "@justfrenzy/liquid-glass";

export function SidebarHeaderPlaceholder() {
  return (
    <div aria-hidden="true" className="flex items-center gap-3">
      <LiquidSkeleton className="size-9 rounded-xl" />
      <div className="grid gap-2">
        <LiquidSkeleton className="h-2.5 w-24" />
        {/* <LiquidSkeleton style={{ width: 64, height: 8 }} /> */}
      </div>
    </div>
  );
}
