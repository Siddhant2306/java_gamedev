# java_gamedev


- `src/` → Contains all Java source files.  
- `bin/` → Compiled `.class` files (generated automatically).  
- `Main` → Package containing all classes.  

---

## ⚙️ Requirements

- Java Development Kit (JDK) 8 or higher
- Command-line tools (`javac` and `java`)  
  OR any IDE like IntelliJ IDEA, Eclipse, or VS Code

---

## 💻 Build Instructions

1. Open a terminal in the **project root** folder.  
2. Compile all Java files into the `bin/` folder:

```bash
compile_commoand: javac -d bin src/Main/*.java src/Entity/*.java
run_commoand: java -cp bin src.Main.Main
