import FormularioPessoa from './formularioPessoa';
import Pessoa from '../Pessoa';
import {render , screen }from '@testing-library/react'

describe('formulario-component', () => {
    test('classe dos titulos', () => {
        render(<FormularioPessoa/>)
        const button = screen.findByRole('button', {
            name: /pesquisar/i
        });
        expect(button).toBeVisible;
    })

    test('inputs enabled', () => {
        render(<FormularioPessoa/>)
        const input = screen.findAllByRole('input', {
            name: /name/i
        });
        expect(input).toBeEnabled;
    })

    test('titulo inputs', () => {
        render(<FormularioPessoa/>)
        const title = screen.findAllByText("Numero Comercial")
        expect(title).toBeVisible;
    })

    test('dataTable funcionando', () => {
        render(<Pessoa/>)
        const dataTable = screen.findByRole("DataTable")
        expect(dataTable).toBeVisible;
    })
    test('formulario visivel', () => {
        render(<Pessoa/>)
        const formulario = screen.findByRole("FormularioPessoa")
        expect(formulario).toBeVisible;
    })
})