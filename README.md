# Horse Race Simulator 
 By SID:240610148
Welcome to the Horse Race Simulator - a dynamic JavaFX application where you can create custom horses, place bets, and watch them race. 
 ## Known Limitations
 - Winner is currently random (can be extended using horse stats)

  - No persistent storage (wallet resets on restart)

   - Betting history not saved beyond session

## Features

### Customisable Horses
- Select **breed**, **coat colour**, and **equipment** (saddle, horseshoes, accessories)
- Pick a **unique symbol or emoji** to represent each horse
- Customisation influences horse confidence (affecting performance)

### Betting System
- Start with a wallet of **£100**
- Choose a horse to bet on and enter a wager
- Win or lose based on the race result
- Wallet updates after each race
- Betting summary displayed after every match

### Animated Races
- Horses race across the screen using JavaFX `TranslateTransition`
- Winner chosen randomly (can be expanded to stat-based)
- Visual representation of the race in motion

### Stats & Feedback
- Dynamic **betting stats panel**
- **Wallet tracker**
- Visual race output
- Emoji-based feedback (for fun and clarity)

### UX Enhancements
- **ScrollPane** wraps full window: scrolls smoothly when window overflows
- **Race track** has its own scroll zone (if lots of horses)
- Maximum of **6 horses** to ensure clean UI and readable display


## Setup Instructions

### Prerequisites
- Java 17+
- JavaFX SDK (added to module path)
- IntelliJ IDEA (recommended) with JavaFX support

### To Run:
1. Clone or download the repository
2. Open it in IntelliJ
3. Add JavaFX SDK:
    - Go to Project Structure ->Modules ->Dependencies -> Add JavaFX `lib` folder
4. Add VM options (Run -> Edit Configurations -> VM Options):

 ## Testing & Notes
 - Custom unit tests (Part 1) validate core logic

 - Part 2 focuses on GUI behavior; functional testing via user interaction

 - Screenshots available in the report.

