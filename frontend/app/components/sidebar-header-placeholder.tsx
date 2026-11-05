// "use client";

import { LiquidSkeleton } from "@justfrenzy/liquid-glass";

// import { LiquidSkeleton } from "@justfrenzy/liquid-glass";

// export function SidebarHeaderPlaceholder() {
//   return (
//     <div aria-hidden="true" className="flex items-center gap-3">
//       <LiquidSkeleton className="size-9 rounded-xl" />
//       <div className="grid gap-2">
//         <LiquidSkeleton className="h-2.5 w-24" />
//         {/* <LiquidSkeleton style={{ width: 64, height: 8 }} /> */}
//       </div>
//     </div>
//   );
// }

export interface SidebarSkeletonProps {
  variant?: "header" | "footer";
  textSize?: "sm" | "md" | "lg";
  subtitleSize?: "sm" | "md" | "lg";
  showSubtitle?: boolean;
  avatarClassName?: string;
  className?: string;
}

const PRIMARY_TEXT_SIZES = {
  sm: "h-2 w-16",
  md: "h-2.5 w-24",
  lg: "h-3.5 w-32",
};

const SUBTITLE_TEXT_SIZES = {
  sm: "h-1.5 w-12",
  md: "h-2 w-16",
  lg: "h-2.5 w-20",
};

export function SidebarSkeleton({
  variant = "header",
  textSize = "md",
  subtitleSize = "md",
  showSubtitle = false,
  avatarClassName,
  className = "",
}: SidebarSkeletonProps) {
  const defaultAvatarClass =
    variant === "header" ? "size-9 rounded-xl" : "size-2.5 rounded-full";
  const defaultContainerGap = variant === "header" ? "gap-3" : "gap-12";

  return (
    <div
      aria-hidden="true"
      className={`flex items-center ${defaultContainerGap} ${className}`.trim()}
    >
      {/* Avatar / Icon Skeleton */}
      <LiquidSkeleton
        className={avatarClassName ?? defaultAvatarClass}
      />

      {/* Text Lines */}
      <div className="grid gap-2">
        {/* Primary Text Line */}
        <LiquidSkeleton className={PRIMARY_TEXT_SIZES[textSize]} />

        {/* Optional Subtitle / Secondary Line */}
        {showSubtitle && (
          <LiquidSkeleton className={SUBTITLE_TEXT_SIZES[subtitleSize]} />
        )}
      </div>
    </div>
  );
}