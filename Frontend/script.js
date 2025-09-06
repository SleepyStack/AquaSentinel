// ========== MAP INITIALIZATION ==========
const map = L.map("map").setView([20.5937, 78.9629], 5);
L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    attribution: "© OpenStreetMap contributors",
}).addTo(map);

// ========== TABS ==========
document.getElementById("tab-map").onclick = function() {
    document.getElementById("section-map").style.display = "";
    document.getElementById("section-report").style.display = "none";
    this.classList.add("active");
    document.getElementById("tab-report").classList.remove("active");
};
document.getElementById("tab-report").onclick = function() {
    document.getElementById("section-map").style.display = "none";
    document.getElementById("section-report").style.display = "";
    this.classList.add("active");
    document.getElementById("tab-map").classList.remove("active");
};

// ========== LOCATION BUTTON ==========
document.getElementById("getLocationBtn").onclick = function() {
    if (!navigator.geolocation) return alert("Geolocation not supported");
    navigator.geolocation.getCurrentPosition(pos => {
        document.getElementById("location").value = `${pos.coords.latitude},${pos.coords.longitude}`;
        map.setView([pos.coords.latitude, pos.coords.longitude], 12);
    }, err => {
        alert("Could not get location: " + err.message);
    });
};

// ========== REPORT FORM SUBMISSION ==========
document.getElementById("reportForm").onsubmit = async function(e) {
    e.preventDefault();

    // Show spinner
    const spinner = document.getElementById("spinnerContainer");
    const icon = document.getElementById("spinnerIcon");
    const text = document.getElementById("spinnerText");
    spinner.style.display = "block";
    icon.className = "fas fa-spinner fa-spin";
    text.textContent = "Submitting...";

    // Build payload for backend
    const payload = {
        geoTag: document.getElementById("location").value,
        description: document.getElementById("description").value,
        mediaUrl: "",
        severityLevel: document.getElementById("hazardType").value,
    };

    try {
        const response = await fetch("http://localhost:8080/ReportHazard", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Basic " + btoa("dakshkandpal@gmail.com:daksh@2005")
            },
            body: JSON.stringify(payload)
        });
        if (response.ok) {
            icon.className = "fas fa-check-circle";
            icon.style.color = "#34d399";
            text.textContent = "Report submitted!";
            this.reset();
        } else {
            icon.className = "fas fa-times-circle";
            icon.style.color = "#f87171";
            text.textContent = "Error: " + response.status;
        }
    } catch (err) {
        icon.className = "fas fa-times-circle";
        icon.style.color = "#f87171";
        text.textContent = "Network error";
    }
    setTimeout(() => { spinner.style.display = "none"; }, 2200);
};