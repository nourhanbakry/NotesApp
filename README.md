# NotesApp
A simple and clean Android application for creating, viewing, updating, and deleting notes. This project demonstrates a modern Android development setup using Kotlin, MVVM architecture, and Jetpack components.

## Features

-   **Create Notes:** Easily add new notes with a title and content.
-   **View All Notes:** See a list of all your notes, sorted by the most recently updated.
-   **Update & Delete:** Tap on a note to edit its content or delete it.
-   **Search:** Quickly find notes by searching for text in their title or content.
-e   **Persistent Storage:** Your notes are saved locally on your device using the Room database, so they are available even after you close the app.
-   **Responsive UI:** The UI adapts to different states, showing loading indicators, an empty state message, or your list of notes.

## Architecture & Tech Stack

This application is built following the MVVM (Model-View-ViewModel) architectural pattern to ensure a clean separation of concerns and a scalable codebase.

-   **Language:** [Kotlin](https://kotlinlang.org/)
-   **Architecture:** MVVM (Model - View - ViewModel)
-   **UI:** XML with ViewBinding and Material Design Components.
-   **Asynchronous Programming:** Kotlin Coroutines for background operations.
-   **Dependency Injection (Manual):** Dependencies are provided manually where needed.
-   **Navigation:** [Jetpack Navigation Component](https://developer.android.com/guide/navigation) for managing in-app navigation between fragments.
-   **Database:** [Room Persistence Library](https://developer.android.com/training/data-storage/room) for local data storage.
-   **State Management:** A combination of [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata), and a custom `NotesViewState` sealed class to manage and observe UI state.
-   **UI List Management:** `RecyclerView` with `DiffUtil` to efficiently update the list of notes.

## Project Structure

The project is organized into the following main packages:

-   **`Model`**: Contains the data layer components.
    -   `Entity/Note.kt`: The data class representing a single note and the database table schema.
    -   `LocalData/NoteDAO.kt`: Data Access Object with methods for database operations (CRUD, search).
    -   `LocalData/NoteDatabase.kt`: The Room database class.
    -   `LocalData/NoteRepository.kt`: A repository that abstracts the data source and provides a clean API for data access to the ViewModel.

-   **`View`**: Contains UI-related classes.
    -   `MainActivity.kt`: The single activity that hosts all fragments.
    -   `Fragments/`: Contains all the fragments for different screens (Show, Add, Update notes).
    -   `ShowNotesAdapter.kt`: The `RecyclerView.Adapter` for displaying the list of notes.

-   **`ViewModel`**:
    -   `NoteViewModel.kt`: Acts as a bridge between the View and the Repository. It fetches and prepares data for the UI and handles user interactions.

## Project Snapshots
<p align="center">
  <img src="https://github.com/user-attachments/assets/f44a493b-6cb1-4549-884f-e8ee98283589" width="180"/>
  <img src="https://github.com/user-attachments/assets/9a8856cf-5768-4e40-bd38-448c62ff3046" width="180"/>
  <img src="https://github.com/user-attachments/assets/d8d5dbae-1b8d-4606-bfae-731208f406f5" width="180"/>
  <img src="https://github.com/user-attachments/assets/927a7595-772f-406f-be98-0042e1e44ef1" width="180"/>
  <img src="https://github.com/user-attachments/assets/c0f8a8fa-a7d8-4efb-be40-bc4e4879bac9" width="180"/>
  <img src="https://github.com/user-attachments/assets/cd9fa40f-acff-4a2c-83fd-67ec7ed6463a" width="180"/>
  <img src="https://github.com/user-attachments/assets/e932549d-5313-4d6c-aa18-c36a353cd457" width="180"/>
  <img src="https://github.com/user-attachments/assets/6bc51a52-ac18-4aa3-95a6-920850fdfd3e" width="180"/>
</p>

## How to Build and Run

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/nourhanbakry/NotesApp.git
    ```
2.  **Open in Android Studio:**
    Open the cloned project directory in Android Studio.
3.  **Sync Gradle:**
    Wait for Android Studio to download all the required Gradle dependencies.
4.  **Run the app:**
    Select an emulator or connect a physical device and click the 'Run' button.
