document.addEventListener('DOMContentLoaded', () => {
    // Retrieve movie data from localStorage
    const theaterDate = JSON.parse(localStorage.getItem('selectedTheter'));

    if (!theaterDate) {
        alert('No movie data found.');
        window.location.href = 'theaterList.html'; // Redirect back to movie list
        return;
    }

    // Display movie details
    // document.getElementById('movie-title').textContent = theaterDate.title;
    // document.getElementById('movie-poster').src = theaterDate.poster;
    // document.getElementById('movie-details').textContent = `${theaterDate.rating}% rating, ${theaterDate.votes} votes, Genre: ${theaterDate.genre}`;

    // Fetch seat data for the selected movie
    fetch(`http://localhost:8181/movies/seats?movieId=${theaterDate.movieId}`)
        .then(response => response.json())
        .then(data => displaySeats(data))
        .catch(error => console.error('Error fetching seat data:', error));

    // Display seats in theater layout
    function displaySeats(seatData) {
        const theater = document.getElementById('theater');
        theater.innerHTML = '';

        const rows = {};
        seatData.forEach(seat => {
            if (!rows[seat.rowNumber]) {
                rows[seat.rowNumber] = [];
            }
            rows[seat.rowNumber].push(seat);
        });

        Object.values(rows).forEach(rowData => {
            const row = document.createElement('div');
            row.className = 'row';

            rowData.forEach(seat => {
                const seatElement = document.createElement('div');
                seatElement.className = 'seat';
                if (seat.occupied) {
                    seatElement.classList.add('occupied');
                }
                seatElement.addEventListener('click', () => {
                    if (!seat.occupied) {
                        seatElement.classList.toggle('selected');
                    }
                });
                row.appendChild(seatElement);
            });

            theater.appendChild(row);
        });
    }

    // Book selected seats
    document.getElementById('book-button').addEventListener('click', () => {
        const selectedSeats = [];
        document.querySelectorAll('.seat.selected').forEach(seat => {
            const row = seat.parentNode;
            const rowIndex = Array.from(row.parentNode.children).indexOf(row);
            const seatIndex = Array.from(row.children).indexOf(seat);
            selectedSeats.push({ rowNumber: rowIndex + 1, seatNumber: seatIndex + 1 });
        });

        if (selectedSeats.length === 0) {
            alert('Please select at least one seat.');
            return;
        }

        const bookingData = {
            movieId: theaterDate.movieId, // Use the ID from theaterDate
            seats: selectedSeats
        };

        fetch('http://localhost:8181/movies/bookSeats', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(bookingData)
        })
        .then(response => {
            if (response.ok) {
                alert('Seats booked successfully!');
                // Optionally, reload seats to reflect booking
                fetch(`http://localhost:8181/movies/seats?movieId=${theaterDate.movieId}`)
                    .then(response => response.json())
                    .then(data => displaySeats(data))
                    .catch(error => console.error('Error fetching seat data:', error));
            } else {
                alert('Failed to book seats.');
            }
        })
        .catch(error => console.error('Error booking seats:', error));
    });
});