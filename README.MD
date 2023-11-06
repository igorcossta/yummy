<a id="readme-top" name="readme-top"></a>



<!-- PROJECT LOGO -->
<img src="https://i.imgur.com/7wlnLSi.png" align="right" />

# Yummy

> Platform for culinary enthusiasts, providing a space to share, discover, and discuss recipes.

Elements in beautiful READMEs include, but are not limited to: images, screenshots, GIFs, text formatting, etc.
<p align="right">
    <a href="https://example.com">View Demo</a>
    ·
    <a href="https://example.com">Report Bug</a>
    ·
    <a href="https://example.com">Request Feature</a>
</p>
<p align="right">
  <a href="#getting-started"><strong>Explore the docs »</strong></a>
</p>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#preview">Preview</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#license">License</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->

## About The Project

[![Product Name Screen Shot][product-screenshot]](https://github.com/igorcossta/yummy)

Yummy is a Java-based web application. It serves as a versatile platform for culinary enthusiasts, providing a space to
share, discover, and discuss recipes. Whether you're a professional chef or a home cook, Yummy is your go-to destination
to explore the world of delicious dishes.

Here's why:

* Save Time: Yummy allows you to focus on what you love most – creating and cooking delicious recipes.
* Connect with a Culinary Community: Yummy brings together food enthusiasts from all over the world. Join the community
  to meet like-minded individuals, share your culinary experiences, and learn from others.
* Endless Recipe Inspiration: Yummy provides a treasure trove of recipes and cooking ideas. If you ever find yourself in
  a cooking rut or seeking inspiration, Yummy's vast recipe database is there to spark your creativity.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

The Yummy application is meticulously crafted with an array of cutting-edge technologies to ensure a seamless and
delightful user experience. Here's a closer look at the technologies powering Yummy:

- [Java 17] - The foundation of the application, ensuring robust performance and security.
- [Spring Framework] - Empowering Yummy with security features and dynamic web pages.
- [Thymeleaf] - The engine for creating interactive web content.
- [Postgresql] - The reliable database for storage.
- [Lombok] - Simplifying Java code with reduced boilerplate.
- [Bootstrap] - Delivering a sleek and responsive user interface.
- [Javascript] - Enhancing user interactions.
- [IntelliJ] - The development environment of choice for efficient coding.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

Follow the instructions to RUN `Yummy` on your machine:

### Prerequisites

To run the application, you'll need the following essential technologies and configurations:

* Ensure you have Java 17 or a newer version installed. You can install it with SDKMAN:
  ```sh
  sdk install java 17.0.8-tem
  ```

* Set up a database for Yummy using Docker and PostgreSQL. Run the following command to create a PostgreSQL container
  with specific configurations:
    ```sh
    docker run --name mypostgres \
    -e POSTGRES_USER=root \
    -e POSTGRES_PASSWORD=password \
    -e POSTGRES_DB=mydatabase \
    -p 5432:5432 \
    -d postgres
    ```
  > Note: You can use whatever database you wish.

* Follow Google's official guide to allow less secure apps to access your Gmail account. (Generating a Gmail app
  password is necessary for user registration to work.)
    ```http request
    https://support.google.com/accounts/answer/185833?hl=en
    ```

* Ensure you have a free API Key from [https://calorieninjas.com](https://calorieninjas.com/).

### Installation

You can now download and set up the project on your machine by following these simple steps:

1. Use either of the following commands to clone the Yummy repository to your local machine:
    ```sh
    git clone git@github.com:igorcossta/yummy.git
    git clone https://github.com/igorcossta/yummy.git
    ```
2. Open the project in your favorite IDE.
3. In your project's `application-dev.yaml` file, locate the database configuration section and enter your docker
   container setup:
     ```yaml
     # DATABASE CONFIGURATION
     POSTGRES_HOST: localhost
     POSTGRES_PORT: 5432
     POSTGRES_DATABASE_NAME: mydatabase
     POSTGRES_USERNAME: postgres
     POSTGRES_PASSWORD: password
     ```
   > Note: This section must match with the docker or whatever database you are using.
4. After generation your Gmail app password enter your secret in `application-dev.yaml`
    ```yaml
    # EMAIL CONFIGURATION
    EMAIL_ID: <your email>
    EMAIL_PASSWORD: <app password>
    ```
5. In your project's `application.yaml` file, locate the API configuration section and enter your API Key:
   ```yaml
   # APIs config
    api:
      calories: <key>
   ```

With these straightforward steps, you'll have Yummy up and running with your Calorie Ninjas API Key.

<p align="right">(<a href="#readme-top">back to top</a>)</p>




<!-- PREVIEW -->

## Preview

[![Recipes Page][recipes-page-screenshot]](https://github.com/igorcossta/yummy)
[![My Recipes Page][my-recipes-page-screenshot]](https://github.com/igorcossta/yummy)
[![Recipe Details Page][recipe-details-page-screenshot]](https://github.com/igorcossta/yummy)
[![Recipe Comments Section][recipe-comments-section-screenshot]](https://github.com/igorcossta/yummy)
[![Recipe Creation Page][recipe-creation-page-screenshot]](https://github.com/igorcossta/yummy)
[![Action Warning Modal][recipe-warning-modal-screenshot]](https://github.com/igorcossta/yummy)


<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP -->

## Roadmap

- [ ] Allow users to have full control over their comments by enabling deletion and modification options for comments on
  recipes
- [ ] Users express their appreciation for a recipe by allowing them to "like" it
- [ ] Multi-language Support
    - [ ] Portuguese
    - [ ] English

See the [open issues](https://github.com/igorcossta/yummy/issues) for a full list of proposed features (and known
issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<br>

<!-- MARKDOWN LINKS & IMAGES -->

[product-screenshot]: https://i.imgur.com/caHCDxM.png

[recipes-page-screenshot]: https://i.imgur.com/GzWyv8J.png

[my-recipes-page-screenshot]: https://i.imgur.com/6G1Hntr.png

[recipe-details-page-screenshot]: https://i.imgur.com/xxbkWp2.png

[recipe-comments-section-screenshot]: https://i.imgur.com/oAFaYC8.png

[recipe-creation-page-screenshot]: https://i.imgur.com/gXfxUXK.png

[recipe-warning-modal-screenshot]: https://i.imgur.com/BZeXPbE.png