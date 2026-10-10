# Android Flashcards Application

### A customisable flashcard app built with Java and Android Studio.

This project started with a simple idea: make studying with flashcards a little more interesting. I built an Android app with flashcard management features, custom colour themes, gradient backgrounds, and buttons for different actions.

I've been working with Java, XML layouts, and Android resources to bring everything together. It's still a work in progress, and I want to keep improving both how the app works and how it looks.

## Features

### Flashcard Management

- **Add flashcards:** Enter a question and its answer.
- **View flashcards:** Display the questions and answers added during the current session.
- **Edit flashcards:** Modify flashcard content using the current editing feature.
- **Delete flashcards:** Remove cards from the current list.
- **Flashcard counter:** Check the total number of cards added.

### Custom Themes

I wanted the app to have more personality than a default Android interface, so I experimented with different colour palettes, gradients, and backgrounds.

| Theme | Description |
|---|---|
| Lavender Galaxy | A soft lavender-inspired colour theme. |
| Rose | A bold pink colour palette. |
| Daisy Galaxy | A floral-inspired colour theme. |
| Sunflower | A warm, bright colour palette. |
| Gradient Background | A background with a gradient effect. |
| Dark Background Toggle | Switch between different background appearances. |

The app also uses custom drawable resources, gradients, and rounded shapes to customise the interface.

### Interactive Buttons

The app has separate buttons for adding, viewing, editing, and deleting flashcards, checking the total count, switching themes, and changing the background.

Each button connects the XML interface to Java code that handles the corresponding action.

## Tech Stack

- **Java** — application logic, flashcard objects, list management, and button interactions.
- **XML** — screen layouts and interface elements.
- **Android Studio** — application development and testing.
- **Git and GitHub** — version control and collaboration.

## Project Structure

- `MainActivity.java` — handles the main screen, flashcard actions, and theme changes.
- `Flashcard.java` — defines the flashcard object and its question and answer fields.
- `activity_main.xml` — contains the main screen layout and interface elements.
- `res/drawable/` — contains custom backgrounds, gradients, and shape resources.
- `res/values/` — contains colour definitions, strings, and theme resources.

## Getting Started

1. Clone or download this repository.
2. Open the project in Android Studio.
3. Allow Gradle to sync and resolve the required dependencies.
4. Run the app on an Android emulator or a connected Android device.

## Roadmap

These are some of the improvements I'd like to work on next.

- [ ] Add a study mode with question-and-answer reveal.
- [ ] Save flashcards between app sessions.
- [ ] Improve editing so users can select a specific card.
- [ ] Organise flashcards into subject categories.
- [ ] Add a quiz mode with score tracking.
- [ ] Improve navigation and usability.
- [ ] Add screenshots and more testing documentation.

## Contributing

I'd like other people to be able to explore this project, suggest ideas, and help improve it.

You can contribute by:

- Suggesting new features.
- Reporting bugs and unexpected behaviour.
- Improving the interface or accessibility.
- Working on planned features.
- Improving the documentation.

### How to Contribute

1. Fork the repository.
2. Create a branch for your changes.
3. Make your changes and test them.
4. Submit a pull request explaining what you changed.

For larger changes, please open an issue first so we can discuss the idea.

## Project Status

**In development.**

The main flashcard features and theme options have been implemented, but some functionality still needs testing and improvement. Permanent storage and additional study features are planned.

## Feedback

Suggestions, bug reports, and constructive feedback are welcome. If you find something that could work better, feel free to open an issue.

I'm building this project while learning more about Java and Android development, and I'm looking forward to improving it over time.

**Repository:** [Android Flashcards Application](https://github.com/EvaRai123/Android-Flashcards-Application)
