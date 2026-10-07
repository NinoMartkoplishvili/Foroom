# Android 4 — Device Matrix

Test class: `ConversationTests`

| # | Device (AVD name) | Type | Android / API | Screen resolution | Scenario 1 | Scenario 2 | Scenario 3 |
|---|---|---|---|---|---|---|---|
| 1 | Pixel_4 | Emulator | Android 13 / API 33 | 1080x2280 | Pass | Pass | Pass |
| 2 | Pixel_7_Pro | Emulator | Android 14 / API 34 | 1440x3120 | Pass | Pass | Pass |
| 3 | Small_Phone | Emulator | Android 15 / API 35 | 720x1280 | Pass | Pass | Pass |

## Scenarios
- **Scenario 1** — `sentMessageInJohnWeekRemainsAfterReopeningChat`
- **Scenario 2** — `questionIsDisplayedInOwnChat`
- **Scenario 3** — `userBReadsGreetingFromUserAAndReplies`

## Notes
- Each scenario was run independently, and the full test class was run on all three devices.
- The full test class was run again to confirm that earlier messages and saved sessions do not invalidate the assertions.

## Screenshot
- `screenshots/android4/all_devices.png`