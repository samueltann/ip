# ByteBuddy Project

ByteBuddy is a Java chatbot project inspired by the Java mascot _Duke_. This project uses JavaFX to provide a graphical user interface (GUI) for interacting with the chatbot.

---

## Prerequisites

- **JDK 17**
- Latest version of **IntelliJ IDEA**
- Optional: Gradle for building and running the project

---

## Setting Up in IntelliJ

1. Open IntelliJ.  
   *(If you are not on the welcome screen, click `File` > `Close Project` to close any open project.)*

2. Open the project:
    1. Click `Open`.
    2. Select the project root directory, and click `OK`.
    3. Accept any further prompts with the default options.

3. Configure the project to use **JDK 17**:
    - Go to `File` > `Project Structure` > `Project`.
    - Set **Project SDK** to JDK 17.
    - Set **Project language level** to `SDK Default`.
    - More details: [JetBrains Guide](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk)

4. Running the app:
    - **Option 1 (IntelliJ):**  
      Locate the `Main` class under `src/main/java/bytebuddy/gui/Main.java`, right-click it, and choose **Run 'Main.main()'**. The GUI should open with a welcome message from ByteBuddy.
    - **Option 2 (Terminal / Gradle):**  
      In the project root folder, run the following command in the terminal:
      ```
      ./gradlew run
      ```
      This will launch the JavaFX GUI.

---

## Notes

- Keep `src/main/java` as the root folder for Java source files.  
  *(Do not rename or move Java files outside this folder, as build tools like Gradle expect this structure.)*

- All user interaction now happens via the GUI, so CLI commands are no longer used.

---