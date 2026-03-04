const form = document.getElementById("loginForm");

form.addEventListener("submit", async (e) => {
  e.preventDefault();

  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  try {
    const response = await fetch("http://localhost:8080/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        email: email,
        password: password
      })
    });

    if (!response.ok) {
      throw new Error("Email ou senha inválidos");
    }

    const data = await response.json();

    localStorage.setItem("token", data.token);

    alert("Login realizado com sucesso!");
    window.location.href = "dashboard.html";

  } catch (error) {
    alert(error.message);
  }
});