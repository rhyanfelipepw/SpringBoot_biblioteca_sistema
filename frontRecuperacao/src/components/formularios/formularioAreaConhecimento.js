import React, { useEffect, useState } from "react";
import { encode } from "base-64";
import { Alert } from "bootstrap";

const FormularioAreaConhecimento = () => {
  const [nome, setNome] = useState("");


  let headers = new Headers();

  headers.set("Authorization", "Basic " + encode("rhyan" + ":" + "123"));

  const onSubmit = (e) => {
    headers.append("Content-Type", "application/json");
    const data = {
        nomeAreaConhecimento: nome,    
    };
    e.preventDefault();
    fetch("http://192.168.1.6:8080/areaConhecimento/criar", {
      method: "POST",
      headers: headers,
      body: JSON.stringify(data),
    })
      .then((response) => response)
      .then(
        (result) => {
          if (result.status == 200) {
            onSucess();
          } else {
            window.alert(result.json().message);
          }
        },
        (error) => {
          window.alert(error);
        }
      );
  };
  const onSucess = () => {
      alert("Area registrada");
      window.location.reload(true);
      setNome("");
  };

  return (
    <div className="containerFormLarge">
      <div className="paddingContainer">
        <form onSubmit={onSubmit}>
          <h3>Nome</h3>
          <input
            onChange={(event) => setNome(event.target.value)}
            className="field"
            type="text"
            name="name"
          />
          <button>Click me</button>
        </form>
      </div>
    </div>
  );
};

export default FormularioAreaConhecimento;
