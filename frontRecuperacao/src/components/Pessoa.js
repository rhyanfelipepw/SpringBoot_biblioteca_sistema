import React, { useEffect, useState } from 'react'
import '../index.css';
import DataTable from "react-data-table-component";
import { encode } from "base-64";

import FormularioPessoa from "./formularios/formularioPessoa";

const Pessoa = () => {

       const [dataPessoas, setData] = useState([]);
    
       let headers = new Headers();
       headers.append('Content-Type', 'text/json');
       headers.set('Authorization', 'Basic ' + encode("rhyan" + ":" + "123"));
       useEffect(()=>{
               fetch("http://192.168.1.6:8080/pessoa/buscarTodas", {
                       method: 'GET', headers: headers,
               }).then((response) => response.json())
                       .then(
                               (result) => {
                                       setData(result);
                               },
                               (error) => {
                                       window.alert(error);
                               }
                       )
       }, []);
       const columnsPessoas = [
              {
                      name: 'Nome',
                      selector: row => row.nome,
                 
              },
              {
                      name: 'RG',
                      selector: row => row.rg,
              
              },
              {
                      name: 'CPF',
                      selector: row => row.cpf,
              
              },
              {
                     name: 'E-MAIL',
                     selector: row => row.email,
             
             },
             {
              name: 'TELEFONE',
              selector: row => row.telefone,
      
      },
      
   
      ];

  return (
    <div className="sec__one">
  
      <div className="sec__one">
             
        <div className="container_midless">
               
          <div className="title">
            <h1 className="align_center">
              {dataPessoas.length} Cadastradas{" "}
            </h1>
          </div>
          <DataTable columns={columnsPessoas} data={dataPessoas} />{" "}
        </div>
        <FormularioPessoa />
      </div>
     
    </div>
  );
};

export default Pessoa;
