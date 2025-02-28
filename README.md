# 🔧 Self-Guided Mobile Application for Anxiety & Depression Management
*A self-guided mobile application to assess and manage anxiety and depression among IT faculty students.*

---

## 📝 Project Description
This mobile application helps to Horizon campus students assess and manage their **mental health**, specifically focusing on **anxiety and depression**. It includes self-assessment tools, counseling interactions, and mental health exercises. A secure **admin panel** allows the main counselor to manage assistant counselors and grant them access to specific students based on their consent.

---

## 🌟 Features
- 🔒 **Secure User Authentication** – Sign up and login functionality.
- 🏢 **Self-Assessment Tools** – Users can check their mental health levels.
- 📊 **Anxiety (GAD-7) & Depression (PHQ-9) Tests** – Displays scores on a progress bar and provides recommendations to manage symptoms.
- 🏆 **Personalized Mental Health Insights** – Users receive tailored recommendations without numerical score displays.
- 💬 **Chat with University Counselor** – Built-in chat functionality using **Firebase**.
- 📞 **24/7 Mental Health Support Hotline (1333)** – Supports **Sinhala, Tamil, and English**.
- 🎮 **Activity Section** – Includes meditation, breathing exercises, and a mini-game called **Battle of the Feeling**.
- 🛠️ **Admin Panel for Counselors** – Manage students & counselors.
- 🔧 **Role-Based Access Control** – Only authorized counselors can access student details.
- 📖 **Profile Section** – Stores previously received recommendations.
- 🎨 **Interactive UI/UX** – Smooth and intuitive user experience.

---

## 👨‍💼 Tech Stack
### **Mobile Application:**
- 📲 **Android Studio**
- ✨ **Java**
- 🌐 **Firebase** (for chat application data storage)

### **Backend API:**
- 🚀 **Spring Boot**
- 📅 **MySQL**

### **Admin Panel:**
- 💻 **PHP (with XAMPP)**
- 💡 **MySQL Database**

---

## 🚿 Installation & Setup

### **1. Clone the Repository**
```bash
 git clone https://github.com/Thenuka09/Self-Guided-Mobile-Application.git
 cd Self-Guided-Mobile-Application
```

### **2. Setting Up the Mobile Application**
- Open the project in **Android Studio**.
- Use **Pixel 6 Pro** as the emulator.
- Set **API Level to 24 or above**.

### **3. Setting Up the Spring Boot Backend**
- Open the backend project in **IntelliJ IDEA**.
- Install **JDK 22 or above**.
- Start **XAMPP** (or use MySQL Workbench).
- Run the **Spring Boot application**.
- The required tables will be created automatically, but you need to import additional tables manually:
  - Import `assistentcounselor.sql` inside the **database folder**.
  - Replace `counselor.sql` in the database before accessing the **admin panel**.

### **4. Running the Mobile Application**
- Start the **Spring Boot API**.
- Install and run the mobile application on the emulator.
- Sign up and log in to check your **mental health status**.

### **5. Setting Up the Admin Panel**
- Place the **admin panel files** inside the `htdocs` folder in **XAMPP**.
- Start XAMPP and navigate to the admin panel.
- Use the following credentials to log in as the **Main Counselor**:
  ```
  Email: counselor@gmail.com
  Password: counselor@123
  ```
- After logging in, update the credentials in the **Manage Counselor** section.

---

## 🛠️ Admin Panel Functionalities
- **Main Counselor**:
  - Can **view all student details**, only if they have given consent.
  - Can **add assistant counselors** and grant them access to specific students.
  - Can **update or delete counselors**, but deleting the main counselor requires assigning a new one first.

- **Assistant Counselors**:
  - Can **access specific students** if granted permission by the main counselor.

---

## 🚀 Future Enhancements
- ✨ **AI-based mental health recommendations**.
- 🔐 **More secure data encryption & storage**.
- 🌐 **Cloud-based admin panel**.

---

## 🙏 Acknowledgments
- **Android Studio Documentation:** [developer.android.com](https://developer.android.com/develop)
- **Spring Boot Documentation:** [spring.io](https://spring.io/)

---

## 📢 Contact
If you have any questions or feedback, feel free to reach out:
- **LinkedIn:** [Charana Thenuka](https://www.linkedin.com/in/charana-thenuka-b5b48826a/)
- **Email:** [cthenuka09@gmail.com]

---

**🌟 If you like this project, don’t forget to star the repository!** ⭐

