<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.sydney.edu.au/adha5655/currency-converter">
    <img src="img/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Currency Converter</h3>

  <p align="center">
    A Windows XP-esk currency exchange coded in 100% Java.
    <br />
    <a href="https://github.sydney.edu.au/adha5655/currency-converter">View Demo</a>
    ·
    <a href="https://github.sydney.edu.au/adha5655/currency-converter/issues">Report Bug</a>
  </p>
</div>

<!-- ABOUT THE PROJECT -->
## About The Project

![Currency Converter][product-screenshot-2]

Created for Project 1 in SOFT2412: Agile Software Development Practices, the Currency Converter project delivers a manually-administrated currency exchange program. 

Created in Java 17 using `java.swing` for the GUI, the currency exchange allows users to:

- convert between various currencies
- view current exchange rates of popular currencies, and 
- view historical exchange rate data

Admins of the application are responsible for setting which currencies are popular and adding and updating currencies and their exchange rates. All data is persistant and is stored in a local SQLite database.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


### Built With

[![Java][Java]][Java-url]
[![SQLite][SQLite]][SQLite-url]


<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites

This project is built in Java 17 using Gradle 7.4 for build automation, JUnit 5.8.1 for automated testing, and SQLite 3.36.0.3 for database storage. The following section goes in-detail on how to install each of these dependencies.

We recommend using the [`sdkman`](https://sdkman.io) package manager to install Java 17 and Gradle 7.5.

* Java 17

    ```sh
    sdk list java
    sdk install java 17.0.2.8.1-amzn
    sdk default java 17.0.2.8.1-amzn
    ````

* Gradle 7.5

    ```sh
    sdk list gradle
    sdk install gradle 7.5
    sdk default gradle 7.5
    ```


### Installation

1. Clone the repo to your local machine

   ```sh
   git clone https://github.sydney.edu.au/adha5655/currency-converter.git
   ```
   
2. Navigate to the `/currency_convert/app` directory
3. Run using `gradle`

    ```sh
    gradle run
    ```
    
4. To exit the application, either press `Ctrl+Q` OR open the in-app start menu and click *Turn off computer.*

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->
## Usage

Add usage here.

![Currency Converter][product-screenshot-3]


### How to test code

1. Navigate to the `currency-converter/app` directory
2. Run testing using `gradle` 

  ```sh
  gradle test
  ```

3. On successful testing, the terminal will display *BUILD SUCCESSFUL*

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- CONTRIBUTING -->
## Contributing

**Notes for the team on how to contribute/collaborate on the codebase:** [main/notes/README.md](https://github.sydney.edu.au/adha5655/currency-converter/blob/main/notes/README.md)

We welcome any collaboration and contributions from the public to this open source project. If you have a suggestion that would make this better, please fork the repo and create a pull request. These steps are listed below:

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTACT -->
## Contact

Created for SOFT2412: Agile Software Development Practices in Semester 2, 2022.

```bash
| name           | unikey   | sid       |
|----------------|----------|-----------|
| Antriksh Dhand | adha5655 | 510415022 |
| Nemo Gage      | ngag3129 | 500496851 |
| Udit Samant    | usam6049 | 500700976 |
| Sulav Malla    | smal8154 | 500495980 |
```

Project Link: [https://github.sydney.edu.au/adha5655/currency-converter](https://github.sydney.edu.au/adha5655/currency-converter)



Important Information on Linking Jenkins with discord, set up by a fellow team member : [https://edstem.org/au/courses/9767/discussion/1014406]

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/adha5655/currency_converter.svg?style=for-the-badge
[contributors-url]: https://github.sydney.edu.au/adha5655/currency-converter/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/adha5655/currency_converter.svg?style=for-the-badge
[forks-url]: https://github.sydney.edu.au/adha5655/currency-converter/network/members
[stars-shield]: https://img.shields.io/github/stars/adha5655/currency_converter.svg?style=for-the-badge
[stars-url]: https://github.sydney.edu.au/adha5655/currency-converter/stargazers

[product-screenshot-1]: img/desktop.png
[product-screenshot-2]: img/welcomeScreen.png
[product-screenshot-3]: img/converter.png
[product-screenshot-4]: img/adminPortal.png

[SQLite]: https://img.shields.io/badge/sqlite-%2307405e.svg?style=for-the-badge&logo=sqlite&logoColor=white
[SQLite-url]: https://www.sqlite.org/index.html
[Java]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white
[Java-url]: https://www.java.com/en/




