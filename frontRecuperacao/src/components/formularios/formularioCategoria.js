import React, { useEffect, useState } from "react";
import { encode } from "base-64";
import { Alert } from "bootstrap";

const FormularioCategoria = () => {
  const [nome, setNome] = useState("");
  const [prazoCat, setPrazo] = useState("");


  let headers = new Headers();

  headers.set("Authorization", "Basic " + encode("rhyan" + ":" + "123"));

  const onSubmit = (e) => {
    headers.append("Content-Type", "application/json");
    const data = {
      nomeCategoria: nome,
      prazoCat: prazoCat,
     
    };
    e.preventDefault();
    fetch("http://192.168.1.6:8080/categoria/criar", {
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
      alert("Categoria registrada");
      window.location.reload(true);
      setNome("");
      setPrazo("");
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
          <h3>Prazo</h3>
          <input
            onChange={(event) => setPrazo(event.target.value)}
            type="number"
            name="name"
          />
          <button>Click me</button>
        </form>
      </div>
    </div>
  );
};

export default FormularioCategoria;
