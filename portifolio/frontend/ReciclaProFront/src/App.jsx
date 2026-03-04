import './App.css';

function App() {
  const handleLogin = (e) => {
    e.preventDefault();
    alert('Login clicado!');
  };

  return (
    <main className="login-container">
      <section className="login-form-side">
        <header>
          <img src="/Logo.png" alt="Logo ReciclaPro" className="logo-img" />
        </header>
        
        <div className="welcome-text">
          <h2>Olá,</h2>
          <h3>Bem-vindo de volta!</h3>
        </div>
        
        <form onSubmit={handleLogin}>
          <input type="email" placeholder="Digite o seu e-mail" required />
          <input type="password" placeholder="Senha" required />
          
          <div className="buttons-row">
            <button type="button" className="btn-create">Criar conta</button>
            <button type="submit" className="btn-login">Login</button>
          </div>
        </form>
      </section>

      <section className="login-image-side">
        <img src="/image5.png" alt="Ilustração Reciclagem" />
      </section>
    </main>
  );
}

export default App;