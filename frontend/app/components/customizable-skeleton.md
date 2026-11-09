# Customizable skeleton

```tsx
// Text rows: short, medium, and long; the icon is optional.
<CustomizableSkeleton textSize="sm" />
<CustomizableSkeleton textSize="md" />
<CustomizableSkeleton textSize="lg" showIcon={false} />

// Logo and text, with an optional second line.
<CustomizableSkeleton variant="brand" textSize="lg" showSubtitle />

// A header and five rows, with four columns.
<CustomizableSkeleton variant="table" rows={5} columns={4} />
```

Adjacent skeletons automatically have 12px of vertical spacing. Override it
with `spacing={20}`. The icon-to-text gap is 12px. `iconSize`, `subtitleSize`,
`className`, and `style` allow further customization. Widths shrink to fit
their container. Skeletons are decorative and hidden from screen readers;
put loading announcements on the section that owns the loading state.
