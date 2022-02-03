import FormularioLivro from './formularioLivro';
import Livro from '../Livro';
import {render , screen, UserEvent }from '@testing-library/react'

describe('formulario-component', () => {

    test('dataTable não está vazia', () => {
        render(<Livro/>)
        const button = screen.queryAllByText("Não");
        expect(button).toBeEmptyDOMElement;
    })

        
    test('DropDown  com valor', () => {
        render(<FormularioLivro/>);
        const text = screen.queryAllByText(1);
        expect(text).toBeInTheDocument;
    })

    test('DropDown  com valor', () => {
        render(<FormularioLivro/>);
        const text = screen.queryAllByText(1);
        expect(text).toBeInTheDocument;
    })

    test('click  drop', () => {
        render(<FormularioLivro/>);
        const text = screen.queryAllByRole('dropdown');
        expect(text).toBeInTheDocument;
    })
    
    test('value drop', () => {
        render(<FormularioLivro/>);
        const text = screen.queryAllByRole('select', ()=> {
            name: /categoria/i
        });
        expect(text).toBeInTheDocument;
    })
    test('render componnent', () => {
        render(<FormularioLivro/>);
        const element = screen.getByText("Autor")
        expect(element).toBeInTheDocument();

    })

})