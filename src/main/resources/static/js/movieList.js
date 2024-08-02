const moviesContainer = document.getElementById("movie-container");

fetch("http://localhost:8181/api/v1/movie-service/movies")
  .then((response) => response.json())
  .then((data) => displayMovies(data))
  .catch((error) => console.error("Error fetching movies:", error));

function displayMovies(movies) {
  moviesContainer.innerHTML = "";
  movies.forEach((movie) => {
    const movieElement = document.createElement("a");
    movieElement.href = `#`;
    movieElement.className = "movie";
    movieElement.style.textDecoration = "none";
    movieElement.style.color = "inherit";
        movieElement.innerHTML = `
            <div class="card">
                <img src="${movie.posterPath}" class="card-img" alt="${movie.title}">
                <div class="card-body">
                    <ion-icon name="heart-sharp"></ion-icon>
                    <p>${movie.rating}% &ThinSpace; ${movie.votes} votes</p>
                </div>
            </div>
            <h3>${movie.title}</h3>
            <p class="detail">${movie.genre}</p>
        `;
        movieElement.addEventListener("click", function (event) {
          event.preventDefault();
          localStorage.setItem("selectedMovie", JSON.stringify(movie));
          window.location.href = "theaterList.html";
        });
        moviesContainer.appendChild(movieElement);
  });
}
