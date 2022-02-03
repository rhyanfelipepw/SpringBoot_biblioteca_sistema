import React, { useEffect, useState } from "react";
import { encode } from "base-64";

const FormularioEmprestimo = () => {
  const [formValues, setFormValues] = useState({});

  let headers = new Headers();

  headers.set("Authorization", "Basic " + encode("rhyan" + ":" + "123"));
  
  const onChanged = (e) => {
    e.preventDefault();
    const { value, name } = e.target;
    setFormValues({ ...formValues, [name]: value });
  };
  const onSubmit = (e) => {
    headers.append("Content-Type", "application/json");
    const data = {
      "pessoa": {
        "idPessoa": formValues.pessoa
      },
      "exemplar":{
        "livro": {
          "idLivro": formValues.livro
        } 
      }
    }
    e.preventDefault();
    fetch("http://192.168.1.6:8080/emprestimo/criar", {
      method: "POST",
      headers: headers,
      body: JSON.stringify(data)
    })
      .then((response) => response.json())
      .then(
        (result) => {
          window.alert(result.message);
        },
        (error) => {
          window.alert(error);
        }
      );
  };
  console.log("formValues", formValues);

  const [pessoas, setPessoas] = useState([]);
  const [livros, setLivros] = useState([]);

 
  useEffect(() => {
    headers.append("Content-Type", "text/json");
    fetch("http://192.168.1.6:8080/livro/buscarTodos", {
      method: "GET",
      headers: headers,
    })
      .then((response) => response.json())
      .then(
        (result) => {
          setLivros(result);
        },
        (error) => {
          window.alert(error);
        }
      );
  }, []);
  useEffect(() => {
    headers.append("Content-Type", "text/json");
    fetch("http://192.168.1.6:8080/pessoa/buscarTodas", {
      method: "GET",
      headers: headers,
    })
      .then((response) => response.json())
      .then(
        (result) => {
          setPessoas(result);
        },
        (error) => {
          window.alert(error);
        }
      );
  }, []);

  return (
    <div className="containerForm">
      <div className="paddingContainer">
        <form onSubmit={onSubmit}>
          <h3>Livro</h3>
          <select id="livro" name="livro" onChange={onChanged}>
            <option value="">Selecione um Livro</option>
            {livros.map((livro) => {
              return (
                <option value={livro.idLivro} key={livro.idLivro}>
                  {livro.nomeLivro}
                </option>
              );
            })}
          </select>
          <br />
          <h3>Pessoa</h3>
          <select id="pessoa" name="pessoa" onChange={onChanged}>
            <option value="">Selecione uma Pessoa</option>
            {pessoas.map((pessoa) => {
              return (
                <option value={pessoa.idPessoa} key={pessoa.idPessoa}>
                  {pessoa.nome}
                </option>
              );
            })}
          </select>
          <button >Click me</button>
        </form>
      </div>
    </div>
  );
};

export default FormularioEmprestimo;
