# 🔺 Sierpiński Triangle Visualizer (Java + Swing)

This is a Java Swing application that dynamically visualizes the [Sierpiński triangle](https://en.wikipedia.org/wiki/Sierpi%C5%84ski_triangle) using the **Chaos Game algorithm**.
Users can interactively control the number of plotted dots, animation speed, dot color, background color, and even pause/resume the rendering.

---

## 📦 Features

- ✅ User-defined dot count
- ✅ Animated dot-by-dot plotting
- ✅ Live dot counter
- ✅ Pause/Resume animation
- ✅ Speed control via slider
- ✅ Dot color cycling
- ✅ Background toggle (dark/light)
- ✅ Zoom (mouse wheel) and pan (mouse drag)
- ✅ Thin triangle outline for visual reference

---

## 🧠 How It Works

The app uses the **Chaos Game algorithm**:
1. Start with 3 triangle vertices.
2. Choose a random initial point inside the triangle.
3. Repeatedly:
   - Randomly pick a vertex.
   - Move halfway toward it.
   - Plot the resulting point.

---

## 🚀 Getting Started

### 🛠 Requirements
- Java 8 or higher
- Maven 3.x

### 📥 Clone & Run

```bash
https://github.com/automation-craftsman/sierpinski-triangle.git
cd sierpinski-triangle
mvn compile
mvn exec:java

