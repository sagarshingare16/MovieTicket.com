document.addEventListener('DOMContentLoaded', () => {
    // Retrieve movie data from localStorage
    const movieData = JSON.parse(localStorage.getItem('selectedMovie'));
    console.log(movieData);


    if (!movieData) {
        alert('No movie data found.');
        window.location.href = 'index.html'; // Redirect back to movie list
        return;
    }
    console.log(movieData.movieId);

    // display movie details
    document.getElementById('movie-title').textContent = movieData.title;
    document.getElementById('movie-poster').src = movieData.poster;
    document.getElementById('movie-details').textContent = `${movieData.rating}% rating, ${movieData.votes} votes, Genre: ${movieData.genre}`;

    fetch(`http://localhost:8181/api/v1/movie-service/theaters?movieId=${movieData.movieId}`)
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        console.log(data);
        const theaterContainer = document.getElementById('theater-container');
        
        if (!theaterContainer) {
            throw new Error('The element with id "theater-container" does not exist.');
        }
        
        if (!data) {
            throw new Error('The "theaters" property is missing in the response data.');
        }

        data.forEach(theater => {
             const li = document.createElement('li');
            // li.textContent = theater.theaterName;
            // theaterContainer.appendChild(li);

            const a = document.createElement('a');
            a.textContent = theater.theaterName;

            li.addEventListener('click',function(event){
                event.preventDefault();
                localStorage.setItem('selectedTheater',JSON.stringify(theater));
                window.location.href='booking.html';
            });
            a.href = `booking.html`; // Example URL for booking
            li.appendChild(a);
            theaterContainer.appendChild(li);
        });
    })
    .catch(error => console.error('Error fetching theaters:', error));

});
