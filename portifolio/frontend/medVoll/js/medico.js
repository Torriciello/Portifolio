document.addEventListener('DOMContentLoaded', async () => {
    const token = localStorage.getItem('token');
    const listaContainer = document.getElementById('lista-medicos');

    if (!token) {
        window.location.href = './login.html';
        return;
    }

    try {
        const response = await fetch('http://localhost:8080/doctor', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        });

        if (response.ok) {
            const data = await response.json();
            renderizarMedicos(data.content);
        } else {
            console.error("Erro ao buscar médicos");
        }
    } catch (error) {
        console.error("Erro de conexão:", error);
    }
});

function renderizarMedicos(medicos) {
    const container = document.getElementById('lista-medicos');
    
    const searchBox = container.querySelector('.search-box');
    container.innerHTML = '';
    container.appendChild(searchBox);

    medicos.forEach(medico => {
        const doctorDiv = document.createElement('div');
        doctorDiv.className = 'doctor';
        doctorDiv.innerHTML = `
            <div class="doctor-info">
                <h3>${medico.name}</h3>
                <p>${medico.specialty} | CRM ${medico.crm}</p>
            </div>
            <i class="fa-solid fa-chevron-down"></i>
        `;
        
        doctorDiv.addEventListener('click', () => {
            alert(`E-mail: ${medico.email}\nTelefone: ${medico.phone}`);
        });

        container.appendChild(doctorDiv);
    });
}