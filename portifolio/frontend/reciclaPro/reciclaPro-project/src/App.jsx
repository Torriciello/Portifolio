import "./App.css";
import { Form_login } from "./components/form_login";

function App() {
  return (
    <main className="container">
      <div className="left">
        <img src="./logo.svg" className="logo" />

        <Form_login />
      </div>

      <div className="right">
        <img src="./reciclagem.png" className="image" />
      </div>
    </main>
  );
}

export default App;
