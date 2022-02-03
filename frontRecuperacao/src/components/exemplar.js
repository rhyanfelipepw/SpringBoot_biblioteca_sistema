import React, { useEffect, useState } from "react";
import { encode } from "base-64";
import "../index.css";
import DataTable from "react-data-table-component";
import FormularioExemplar from "../components/formularios/formularioExemplar";

const Exemplar = () => {
  const [dataExemplar, setData] = useState([]);



  const onSucess = () => {
    alert("Exemplar deletado");
    window.location.reload(true);
  };

  let headers = new Headers();
  headers.append("Content-Type", "text/json");
  headers.set("Authorization", "Basic " + encode("rhyan" + ":" + "123"));

  const onClick = (value) => {
    fetch("http://192.168.1.6:8080/exemplar/delete?idExemplar=" +value , {
      method: 'DELETE',
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


  useEffect(() => {
    fetch("http://192.168.1.6:8080/exemplar/buscarTodos", {
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
  const columnExemplares = [
    {
      name: "id",
      selector: (row) => row.idExemplar,
    },
    {
      name: "Nome Livro",
      selector: (row) => row.livro.nomeLivro,
    },
    {
      name: "Ação",
      selector: (row) => (
        <button onClick={() => onClick(row.idExemplar)}>deletar</button>
      ),
    },
  ];

  return (
    <div className="sec__one">
      <div className="sec__one">
        <div className="container_midless">
          <div className="title">
            <h1 className="align_center">{dataExemplar.length} Exemplares </h1>
          </div>
          <DataTable columns={columnExemplares} data={dataExemplar} />{" "}
        </div>
        <FormularioExemplar />
      </div>
    </div>
  );
};

export default Exemplar;
