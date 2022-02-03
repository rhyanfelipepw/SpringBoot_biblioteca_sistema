import React, { useEffect, useState } from 'react'
import { encode } from "base-64";

const DropDownPessoa = () => {
    const[pessoas, setLivros] = useState([]);
    let headers = new Headers();
    headers.append('Content-Type', 'text/json');
    headers.set('Authorization', 'Basic ' + encode("rhyan" + ":" + "123"));
   
  return (
    <select id="state">
      <option value="">Selecione um livro</option>
      {pessoas.map((pessoa) =>{
        const {idPessoa, nome} = pessoa;
        return (<option value={idPessoa}>{nome}</option>)
      })}
    </select>
  );
};

export default DropDownPessoa;
