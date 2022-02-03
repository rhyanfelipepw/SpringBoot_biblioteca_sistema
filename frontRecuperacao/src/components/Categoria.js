import React, { useEffect, useState } from "react";
import { encode } from "base-64";
import '../index.css';
import DataTable from "react-data-table-component";
import FormularioCategoria from "./formularios/formularioCategoria";

const Categoria = () => {
  const [dataCategoria, setData] = useState([]);

  let headers = new Headers();
  headers.append("Content-Type", "text/json");
  headers.set("Authorization", "Basic " + encode("rhyan" + ":" + "123"));
  useEffect(() => {
    fetch("http://192.168.1.6:8080/categoria/getTodos", {
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
  const columnsCategoria = [
    {
      name: "Nome",
      selector: (row) => row.nomeCategoria,
    },
    {
      name: "Prazo",
      selector: (row) => row.prazoCat,
    },
  ];
  return (
    <div className="sec__one">
      <div className="container_midless">
        <div className="title">
          <h1 className="align_center">{dataCategoria.length} Cadastradas </h1>
        </div>
        <DataTable columns={columnsCategoria} data={dataCategoria} />{" "}
      </div>
      <FormularioCategoria />
    </div>
  );
};

export default Categoria;
