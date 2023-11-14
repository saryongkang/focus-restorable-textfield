# Focus Restorable TextField

This project suggests workaround for https://issuetracker.google.com/192776771

### Motivation

Unlike `EditText` in Android Views, `TextField` in Compose doesn’t automatically retain or 
restore its states as the device goes through configuration changes such as rotating the device 
between landscape and portrait orientations, folding / unfolding the device, resizing window, 
switching dark mode, etc.

### What I have done**

https://github.com/saryongkang/focus-restorable-textfield/assets/978709/6121e199-c4e2-4cb4-b226-34625d09c3d2

This project shows how to retain these 4 states:
- Position of cursor
- Text selection
- Focus
- Keyboard visibility

To realize this, I...
- added focusRestorer modifier to retain focus and keyboard keyboard
- added logic to retain focus and keyboard after configuration changed
- added logic to not view keyboard when a user canceled keyboard by tapping back button or 'search' button
- added logic to ignore change of focus and keyboard during configuration changes

### Considerations
- Need to cache focus flag value during initialization to avoid incorrect modification during initial composition
- Need to ignore focus changes while the activity is changing configurations
- Need to work well with multiple `TextField`s
