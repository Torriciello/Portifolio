document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("formMedico");

  if (!form) return;

  form.addEventListener("submit", async (event) => {
    event.preventDefault();

    const token = localStorage.getItem("token");
    if (!token) {
      alert("Você precisa estar logado!");
      window.location.href = "./login.html";
      return;
    }

    const dadosMedico = {
      name: document.getElementById("nome").value,
      email: document.getElementById("email").value,
      crm: document.getElementById("crm").value,
      phone: document.getElementById("telefone").value,
      
      especialidade: document.getElementById("especialidade").value,

      cpf: "12345678901",

      address: {
        publicPlace: document.getElementById("logradouro").value,
        number: document.getElementById("numero").value,
        complement: document.getElementById("complemento").value,
        city: document.getElementById("cidade").value,
        uf: document.getElementById("uf").value,
        zipCode: document.getElementById("cep").value,
      },
    };

    try {
      const response = await fetch("http://localhost:8080/doctor/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(dadosMedico),
      });

      if (response.ok) {
        alert("Médico cadastrado com sucesso!");
        window.location.href = "./lista_medicos.html";
      } else {
        const erro = await response.json();
        console.error("Erro na API:", erro);
        const msg = erro[0]?.message || "Verifique os dados.";
        alert(`Erro ao cadastrar: ${msg}`);
      }
    } catch (error) {
      console.error("Erro de conexão:", error);
      alert("Não foi possível conectar ao servidor.");
    }
  });

  const btnCancelar = document.querySelector(".btn-secondary");
  if (btnCancelar) {
    btnCancelar.addEventListener("click", () => {
      window.location.href = "./home.html";
    });
  }
});