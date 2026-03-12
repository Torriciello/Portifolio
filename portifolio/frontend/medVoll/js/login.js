const loginForm = document.getElementById('formLogin');

loginForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const loginData = {
        email: document.getElementById('email').value,
        password: document.getElementById('password').value
    };

    try {
        const response = await fetch('http://localhost:8080/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(loginData)
        });

        if (response.ok) {
            const data = await response.json();
            localStorage.setItem('token', data.token); 
            alert('Login realizado!');
            window.location.href = './home.html';
        } else {
            alert('Usuário ou senha inválidos');
        }
    } catch (error) {
        console.error('Erro no login:', error);
    }
});