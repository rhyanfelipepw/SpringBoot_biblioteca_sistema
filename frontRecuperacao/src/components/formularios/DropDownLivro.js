import React, { useEffect, useState } from 'react'
import { encode } from "base-64";

const DropDownLivro = () => {
    const[livros, setLivros] = useState([]);
    let headers = new Headers();
    headers.append('Content-Type', 'text/json');
    headers.set('Authorization', 'Basic ' + encode("rhyan" + ":" + "123"));
    useEffect(()=>{
        fetch("http://192.168.1.6:8080/livro/buscarTodos", {
                method: 'GET', headers: headers,
        }).then((response) => response.json())
                .then(
                        (result) => {
                            setLivros(result);
                        },
                        (error) => {
                                window.alert(error);
                        }
                )
}, []);

  return (
    <select id="state">
      <option value="">Selecione um livro</option>
      {livros.map((livro) =>{
        const {idLivro, nomeLivro} = livro;
        return (<option value={idLivro}>{nomeLivro}</option>)
      })}
    </select>
  );
};

export default DropDownLivro;
