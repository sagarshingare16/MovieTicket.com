document.addEventListener('DOMContentLoaded', (event) => {
    fetchLocations();

    function fetchLocations() {
        fetch('http://localhost:8181/movies/locations')
            .then(response => response.json())
            .then(data => {
                let dropdown = document.getElementById('locationDropdown');
                dropdown.innerHTML = '';
                data.forEach(location => {
                    let a = document.createElement('a');
                    a.href = '#';
                    a.innerText = location;
                    dropdown.appendChild(a);
                });
            })
            .catch(error => console.error('Error fetching locations:', error));
    }
});
