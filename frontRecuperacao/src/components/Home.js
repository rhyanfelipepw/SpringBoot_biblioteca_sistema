import React, { useEffect, useState } from 'react'
import '../index.css';
import DataTable from "react-data-table-component";
import { encode } from "base-64";

const Dashboard = () => {
        const [dataAtrasados, setData] = useState([]);
        const [dataEditora, setDataEditora] = useState([]);
        const [dataEmprestados, setEmprestados] = useState([]);
        let headers = new Headers();
        headers.append('Content-Type', 'text/json');
        headers.set('Authorization', 'Basic ' + encode("rhyan" + ":" + "123"));
        useEffect(()=>{
                fetch("http://192.168.1.6:8080/emprestimo/getAtrasados", {
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
        useEffect(()=> {
                fetch("http://192.168.1.6:8080/editora/buscarTodos", {
                        method: 'GET', headers: headers,
                }).then((response) => response.json())
                        .then(
                                (result) => {
                                        setDataEditora(result);
                                },
                                (error) => {
                                        window.alert(error);
                                }
                        )
        }, [])

        useEffect(()=> {
                fetch("http://192.168.1.6:8080/emprestimo/buscarTodos", {
                        method: 'GET', headers: headers,
                }).then((response) => response.json())
                        .then(
                                (result) => {
                                        setEmprestados(result);
                                },
                                (error) => {
                                        window.alert(error);
                                }
                        )
        }, [])
      
      

        const columnsAtrasados = [
                {
                        name: 'Nome',
                        selector: row => row.pessoa.nome,
                   
                },
                {
                        name: 'Livro',
                        selector: row => row.exemplar.livro.nomeLivro,
                
                },
                {
                        name: 'Data devolução',
                        selector: row => row.dataDevolucao,
                
                },
        
     
        ];
        const columnsEmprestados = [
                {
                        name: 'Nome',
                        selector: row => row.pessoa.nome,
                   
                },
                {
                        name: 'Livro',
                        selector: row => row.exemplar.livro.nomeLivro,
                
                },
                {
                        name: 'Data devolução',
                        selector: row => row.dataDevolucao,
                
                },      

        ];


        const columnsEditora = [
                {
                        name: 'Editora',
                        selector: row => row.nome,
                },
                {
                        name: 'Quantidade Livros',
                        selector: row => row.quantidadeLivros,
                },
    
        ];


        return (
                <div className="sec__one">
                            <div className='containerForm'>
                                <div className='title'><h1 className='align_center'>{dataEmprestados.length} Emprestados </h1></div>
                                    <DataTable
                                        columns={columnsEmprestados}
                                        data={dataEmprestados}
                                />    </div>
                        <div className='containerForm'>
                                <div className='title'><h1 className='align_center'>{dataAtrasados.length} Atrasados</h1></div>
                                <div className='paddingContainer'> </div><DataTable
                                        columns={columnsAtrasados}
                                        data={dataAtrasados}
                                />

                        </div>
                    
                        <div className='containerForm'>
                                <div className='title'><h1 className='align_center'>{dataEditora.length} Editoras</h1></div>
                                <div className='paddingContainer'>
                                </div><DataTable
                                        columns={columnsEditora}
                                        data={dataEditora}
                                />
                        </div>




                </div>
        )
}
export default Dashboard
