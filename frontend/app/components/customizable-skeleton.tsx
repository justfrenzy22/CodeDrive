"use client";

import { LiquidSkeleton } from "@justfrenzy/liquid-glass";
import type { CSSProperties } from "react";
import styles from "./customizable-skeleton.module.css";

type TextSize = "sm" | "md" | "lg";

export interface CustomizableSkeletonProps {
  variant?: "text" | "brand" | "table";
  textSize?: TextSize;
  subtitleSize?: TextSize;
  showSubtitle?: boolean;
  showIcon?: boolean;
  iconSize?: number;
  rows?: number;
  columns?: number;
  spacing?: number;
  className?: string;
  style?: CSSProperties;
}

const TEXT_SIZES = {
  sm: { width: 80, height: 10 },
  md: { width: 112, height: 12 },
  lg: { width: 160, height: 16 },
};

export function CustomizableSkeleton({
  variant = "text",
  textSize = "md",
  subtitleSize = "sm",
  showSubtitle = false,
  showIcon = true,
  iconSize,
  rows = 5,
  columns = 4,
  spacing = 12,
  className = "",
  style,
}: CustomizableSkeletonProps) {
  const size = iconSize ?? (variant === "brand" ? 48 : 12);
  const rootStyle = { "--skeleton-spacing": `${spacing}px`, ...style } as CSSProperties;

  if (variant === "table") {
    return (
      <div aria-hidden="true" className={`${styles.root} ${styles.table} ${className}`} style={rootStyle}>
        {Array.from({ length: rows + 1 }, (_, row) => (
          <div key={row} className={styles.tableRow} style={{ gridTemplateColumns: `repeat(${columns}, minmax(0, 1fr))` }}>
            {Array.from({ length: columns }, (_, column) => (
              <LiquidSkeleton key={column} className={styles.shape} style={{ width: row === 0 ? "65%" : "90%", height: row === 0 ? 16 : 12 }} />
            ))}
          </div>
        ))}
      </div>
    );
  }

  return (
    <div aria-hidden="true" className={`${styles.root} ${styles.row} ${className}`} style={rootStyle}>
      {showIcon && (
        <LiquidSkeleton className={styles.shape} style={{ width: size, height: size, flexShrink: 0, borderRadius: variant === "brand" ? 16 : "50%" }} />
      )}
      <div className={styles.lines}>
        <LiquidSkeleton className={styles.shape} style={TEXT_SIZES[textSize]} />
        {showSubtitle && <LiquidSkeleton className={styles.shape} style={TEXT_SIZES[subtitleSize]} />}
      </div>
    </div>
  );
}
