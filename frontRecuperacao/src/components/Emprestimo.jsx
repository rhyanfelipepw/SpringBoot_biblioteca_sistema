import RpgForm from "./formularios/formularioEmprestimo.js";
import React, { useEffect, useState } from "react";
import { encode } from "base-64";
import "../index.css";
import DataTable from "react-data-table-component";
import FormularioLivro from "../components/formularios/formularioLivro";

const Emprestimo = () => {
  const [dataEmprestimo, setData] = useState([]);

  let headers = new Headers();
  headers.append("Content-Type", "text/json");
  headers.set("Authorization", "Basic " + encode("rhyan" + ":" + "123"));
  useEffect(() => {
    fetch("http://192.168.1.6:8080/emprestimo/buscarTodosNaoDevolvidos", {
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
  const onClick = (value) => {
    fetch("http://192.168.1.6:8080/emprestimo/devolver?idEmprestimo=" +value , {
      method: "GET",
      headers: headers,
   
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
    alert("Devolvido");
    window.location.reload(true);
  };
  const columnsLivros = [
    {
      name: "Autor",
      selector: (row) => row.pessoa.nome,
    },
    {
      name: "Data Emprestimo",
      selector: (row) => row.dataEmprestimo,
    },
    {
      name: "Data Devolucao",
      selector: (row) => row.dataDevolucao,
    },
    {
      name: "Livro",
      selector: (row) => row.exemplar.livro.nomeLivro,
    },

    {
      name: "Ação",
      selector: (row) => (
        <button onClick={() => onClick(row.idEmprestimo)}>Devolver</button>
      ),
    },
  ];
  return (
    <div className="sec__one">
      <div className="container_midless">
        <div className="title">
          <h1 className="align_center">{dataEmprestimo.length} Emprestimos </h1>
        </div>
        <DataTable columns={columnsLivros} data={dataEmprestimo} />{" "}
      </div>
      <RpgForm></RpgForm>
    </div>
  );
};

export default Emprestimo;
