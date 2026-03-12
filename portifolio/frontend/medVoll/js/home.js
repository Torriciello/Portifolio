document.addEventListener('DOMContentLoaded', () => {
    const token = localStorage.getItem('token');

    if (!token) {
        alert('Acesso negado. Por favor, faça login.');
        window.location.href = 'login.html';
        return;
    }

    const cards = document.querySelectorAll('.card');
    
    const rotas = [
        './medicos/lista_medicos.html',  
        './pacientes/lista_paciente.html', 
        './consultas/consultas.html'       
    ];

    cards.forEach((card, index) => {
        card.addEventListener('click', (e) => {
            e.preventDefault(); 
            window.location.href = rotas[index];
        });
    });

    console.log('Usuário autenticado. Menu carregado.');
});