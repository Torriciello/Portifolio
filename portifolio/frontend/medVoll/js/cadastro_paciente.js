const form = document.querySelector('form');

form.addEventListener('submit', async (event) => {
    event.preventDefault();

    const token = localStorage.getItem('token'); 

    const patientData = {
        name: document.getElementById('nome').value,
        cpf: document.getElementById('cpf').value,
        email: document.getElementById('email').value,
        phone: document.getElementById('telefone').value,
        address: {
            logradouro: document.getElementById('logradouro').value,
            numero: document.getElementById('numero').value,
            complemento: document.getElementById('complemento').value,
            cidade: document.getElementById('cidade').value,
            uf: document.querySelector('select').value,
            cep: document.getElementById('cep').value
        }
    };

    try {
        const response = await fetch('http://localhost:8080/patients/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(patientData)
        });

        if (response.ok) {
            const data = await response.json();
            alert('Paciente cadastrado com sucesso!');
            window.location.href = 'lista_pacientes.html';
        } else if (response.status === 403) {
            alert('Sessão expirada ou sem permissão. Faça login novamente.');
        } else {
            const errorData = await response.json();
            console.error('Erro da API:', errorData);
            alert('Erro ao cadastrar: ' + (errorData.message || 'Verifique os dados.'));
        }
    } catch (error) {
        console.error('Erro na conexão:', error);
        alert('Servidor offline ou erro de rede.');
    }
});