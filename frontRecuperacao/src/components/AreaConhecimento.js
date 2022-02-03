import React, { useEffect, useState } from "react";
import { encode } from "base-64";
import '../index.css';
import DataTable from "react-data-table-component";
import FormularioAreaConhecimento from "./formularios/formularioAreaConhecimento";

const AreaConhecimento = () => {
  const [dataAreaConhecimento, setData] = useState([]);

  let headers = new Headers();
  headers.append("Content-Type", "text/json");
  headers.set("Authorization", "Basic " + encode("rhyan" + ":" + "123"));
  useEffect(() => {
    fetch("http://192.168.1.6:8080/areaConhecimento/getTodos", {
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
  const columnsAreaConhecimento = [
    {
      name: "Nome",
      selector: (row) => row.nomeAreaConhecimento,
    },

  ];
  return (
    <div className="sec__one">
      <div className="container_midless">
        <div className="title">
          <h1 className="align_center">{dataAreaConhecimento.length} Cadastradas </h1>
        </div>
        <DataTable columns={columnsAreaConhecimento} data={dataAreaConhecimento} />{" "}
      </div>
      <FormularioAreaConhecimento />
    </div>
  );
};

export default AreaConhecimento;
