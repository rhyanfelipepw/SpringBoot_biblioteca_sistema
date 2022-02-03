import React, { useEffect, useState } from "react";
import { encode } from "base-64";
import "../index.css";
import DataTable from "react-data-table-component";
import FormularioLivro from "../components/formularios/formularioLivro";

const Livro = () => {
  const [dataLivros, setData] = useState([]);
  
  const onClick = () => {
  };
  
  let headers = new Headers();
  headers.append("Content-Type", "text/json");
  headers.set("Authorization", "Basic " + encode("rhyan" + ":" + "123"));
  useEffect(() => {
    fetch("http://192.168.1.6:8080/livro/buscarTodos", {
      method: "GET",
      headers: headers,
    })
      .then((response) => response.json())
      .then(
        (result) => {
          setData(result);
        },
        (error) => {
          window.alert(error);
        }
      );
  }, []);
  const columnsLivros = [
    {
      name: "Nome",
      selector: (row) => row.nomeLivro,
    },
    {
      name: "Editora",
      selector: (row) => row.editora.nome,
    },
    {
      name: "Autor",
      selector: (row) => row.autor.nome,
    },
    // {
    //   name: "Ação",
    //   selector: () => <button onClick={}>Adicionar</button>
    // },
  ];

  return (
    <div className="sec__one">
      <div className="sec__one">
        <div className="container_midless">
          <div className="title">
            <h1 className="align_center">{dataLivros.length} Cadastradas </h1>
          </div>
          <DataTable columns={columnsLivros} data={dataLivros} />{" "}
        </div>
        <FormularioLivro />
      </div>
    </div>
  );
};

export default Livro;
