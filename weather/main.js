const apiKey = "de3ff96fdcc94a90b5b152604241705";
const apiUrl = "https://api.weatherapi.com/v1/current.json?";
const searchBox = document.querySelector('.search input');
const searchBtn = document.querySelector('.search button');
const weatherIcon = document.querySelector('.weather-icon');

async function weather(city) {
    try {
        const response = await fetch(`${apiUrl}key=${apiKey}&q=${city}`);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        console.log(data);

        document.querySelector(".city").innerHTML = data.location.name;
        document.querySelector(".temp").innerHTML = Math.round(data.current.temp_c) + "°C";
        document.querySelector(".humidity").innerHTML = data.current.humidity + "%";
        document.querySelector(".wind").innerHTML = data.current.wind_kph + " km/h";
        weatherIcon.src = data.current.condition.icon;
        weatherIcon.alt = data.current.condition.text;
        document.querySelector(".weather").style.display = "block";
    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
    }
}

searchBtn.addEventListener("click", () => {
    weather(searchBox.value);
});

