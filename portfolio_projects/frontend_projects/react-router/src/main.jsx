import "./index.css";
import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { Login } from "./pages/Login/index.jsx";
import { Register } from "./pages/Register/index.jsx";
import { Feed } from "./pages/Feed/index.jsx";
import { BlogPost } from "./pages/BlogPost/index.jsx";
import { BrowserRouter } from "react-router-dom";

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <BrowserRouter>
      {/* <Login /> */}
      {/* <Register /> */}
      {/* <Feed /> */}
      <BlogPost />
    </BrowserRouter>
  </StrictMode>,
);
