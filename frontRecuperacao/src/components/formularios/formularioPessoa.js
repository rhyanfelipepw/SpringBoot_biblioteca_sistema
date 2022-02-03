import React, { useEffect, useState } from "react";
import { encode } from "base-64";
import { Alert } from "bootstrap";

const FormularioPessoa = () => {
  const [nome, setNome] = useState("");
  const [rg, setRg] = useState("");
  const [cpf, setCpf] = useState("");
  const [cepRes, setCepRes] = useState("");
  const [numeroRes, setNumeroRes] = useState("");
  const [compleRes, setCompleRes] = useState("");
  const [cepCom, setCepCom] = useState("");
  const [numeroCom, setNumeroCom] = useState("");
  const [compleCom, setCompleCom] = useState("");
  const [email, setEmail] = useState("");
  const [telefone, setTelefone] = useState("");

  let headers = new Headers();

  headers.set("Authorization", "Basic " + encode("rhyan" + ":" + "123"));

  const onSubmit = (e) => {
    headers.append("Content-Type", "application/json");
    const data = {
      nome: nome,
      rg: rg,
      cpf: cpf,
      enderecoCom_refEndereco: cepCom,
      enderecoCom_numero: numeroCom,
      endereoCom_complem: compleCom,
      enderecoRes_refEndereco: cepRes,
      enderecoRes_numero: numeroRes,
      endereoRes_complem: compleRes,
      email: email,
      telefone: telefone
    };
    e.preventDefault();
    fetch("http://192.168.1.6:8080/pessoa/criar", {
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
      alert("Pessoa registrada");
      window.location.reload(true);
      setNome("");
      setRg("");
      setCpf("");
      setCepRes("");
      setNumeroRes("");
      setCompleRes("");
      setCepCom("");
      setNumeroCom("");
      setCompleCom("");
  
  };

  return (
    <div className="containerFormLarge">
      <div className="paddingContainer">
        <form onSubmit={onSubmit}>
          <h3 className="">Nome</h3>
          <input
            onChange={(event) => setNome(event.target.value)}
        
            type="text"
            name="name"
          />
          <h3 className="">RG</h3>
          <input
            onChange={(event) => setRg(event.target.value)}
            type="number"
            name="name"
          />
          <h3 className="">cpf</h3>
          <input
            onChange={(event) => setCpf(event.target.value)}
            type="number"
            name="name"
          />
                 <h3 className="">Email</h3>
          <input
            onChange={(event) => setEmail(event.target.value)}
            type="text"
            name="name"
          />
              <h3 className="">Telefone</h3>
          <input
            onChange={(event) => setTelefone(event.target.value)}
            type="text"
            name="name"
          />
          <br />
          <br />
          <h3>CEP Comercial</h3>
          <input
            onChange={(event) => setCepCom(event.target.value)}
            type="number"
            name="name"
          />
          <h3>Numero Comercial</h3>
          <input
            onChange={(event) => setNumeroCom(event.target.value)}
            type="number"
            name="name"
          />
          <h3>Complemento Comercial</h3>
          <input
            onChange={(event) => setCompleCom(event.target.value)}
            type="text"
            name="name"
          /><br/>
          <br/>
          <h3>CEP Residencial</h3>
          <input
            onChange={(event) => setCepRes(event.target.value)}
            type="number"
            name="name"
          />
          <h3>Numero Residencial</h3>
          <input
            onChange={(event) => setNumeroRes(event.target.value)}
            type="number"
            name="name"
          />
          <h3>Complemento Residencial</h3>
          <input
            onChange={(event) => setCompleRes(event.target.value)}
            type="text"
            name="name"
          />
          <button>Click me</button>
        </form>
      </div>
    </div>
  );
};

export default FormularioPessoa;
