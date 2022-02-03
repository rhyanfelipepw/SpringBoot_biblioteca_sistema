import React, { useEffect, useState } from "react";
import { encode } from "base-64";

const FormularioLivro = () => {
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
      nomeLivro: nome,
      editora: {
        idEditora: formValues.editora,
      },
      autor: {
        idAutor: formValues.autor,
      },
      categoria: {
        idCategoria: formValues.categoria,
      },
    };
    e.preventDefault();
    fetch("http://192.168.1.6:8080/livro/criar", {
      method: "POST",
      headers: headers,
      body: JSON.stringify(data),
    })
      .then((response) => response)
      .then(
        (result) => {
          if (result.status == 200) onSucess();
        },
        (error) => {
          window.alert(error);
        }
      );
  };
  const onSucess = () => {
    alert("Livro registrada");
    window.location.reload(true);
  };
  console.log("formValues", formValues);
  const [nome, setNome] = useState([]);
  const [editoras, setEditora] = useState([]);
  const [autores, setAutores] = useState([]);
  const [categorias, setCategoria] = useState([]);

  useEffect(() => {
    headers.append("Content-Type", "text/json");
    fetch("http://192.168.1.6:8080/editora/buscarTodos", {
      method: "GET",
      headers: headers,
    })
      .then((response) => response.json())
      .then(
        (result) => {
          setEditora(result);
        },
        (error) => {
          window.alert(error);
        }
      );
  }, []);
  useEffect(() => {
    headers.append("Content-Type", "text/json");
    fetch("http://192.168.1.6:8080/autor/getTodos", {
      method: "GET",
      headers: headers,
    })
      .then((response) => response.json())
      .then(
        (result) => {
          setAutores(result);
        },
        (error) => {
          window.alert(error);
        }
      );
  }, []);
  useEffect(() => {
    headers.append("Content-Type", "text/json");
    fetch("http://192.168.1.6:8080/categoria/getTodos", {
      method: "GET",
      headers: headers,
    })
      .then((response) => response.json())
      .then(
        (result) => {
          setCategoria(result);
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
          <h3>Nome</h3>
          <input
            onChange={(event) => setNome(event.target.value)}
            className="field"
            type="text"
            name="name"
          />

          <h3>Editora</h3>
          <select id="editora" name="editora" onChange={onChanged}>
            <option value="">Selecione uma editora</option>
            {editoras.map((editora) => {
              return (
                <option value={editora.idEditora} key={editora.idEditora}>
                  {editora.nome}
                </option>
              );
            })}
          </select>
          <br />
          <h3>Autor</h3>
          <select id="autor" name="autor" onChange={onChanged}>
            <option value="">Selecione um autor</option>
            {autores.map((autor) => {
              return (
                <option value={autor.idAutor} key={autor.idAutor}>
                  {autor.nome}
                </option>
              );
            })}
          </select>
          <h3>Categoria</h3>
          <select id="categoria" name="categoria" onChange={onChanged}>
            <option value="">Selecione uma categoria</option>
            {categorias.map((categoria) => {
              return (
                <option
                  value={categoria.idCategoria}
                  key={categoria.idCategoria}
                >
                  {categoria.nomeCategoria}
                </option>
              );
            })}
          </select>
          <button>Click me</button>
        </form>
      </div>
    </div>
  );
};

export default FormularioLivro;
