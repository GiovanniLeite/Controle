package br.com.giovannileite.estoque.controle;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.giovannileite.estoque.modelo.Cargo1;
import br.com.giovannileite.estoque.modelo.Cidade;
import br.com.giovannileite.estoque.modelo.Cliente;
import br.com.giovannileite.estoque.modelo.Estado;
import br.com.giovannileite.estoque.modelo.Fornecedor;
import br.com.giovannileite.estoque.modelo.Funcionario;
import br.com.giovannileite.estoque.modelo.NotaEntradaControle;
import br.com.giovannileite.estoque.modelo.NotaSaidaControle;
import br.com.giovannileite.estoque.modelo.Pai;
import br.com.giovannileite.estoque.modelo.Produto;
import br.com.giovannileite.estoque.modelo.ProdutoNotaEntrada;
import br.com.giovannileite.estoque.modelo.ProdutoNotaSaida;
import br.com.giovannileite.estoque.modelo.Usuario;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/*
 * Version information (1.0)
 * 
 * Author (Giovanni Moraes de Oliveira Leite)
 */

//classe criada para gerar relatorios
public class ControleRelatorio {

	/*
	 1 - Cargo1
	 2 - Cidade
	 3 - Cliente
	 4 - Estado
	 5 - Fornecedor
	 6 - Funcionario
	 7 - CadastroControleEntrada
	 8 - CadastroControleSaida
	 9 - Pai - Pais
	 10 - Produto
	 11 - Usuario
	 */
	
	private ControleCrud con = new ControleCrud();//classe que realiza operacoes no banco
	private String path = this.getClass().getClassLoader().getResource("").getPath();
	private String pathToReportPackage = this.path + "br/com/giovannileite/estoque/relatorio/";//caminho do arquivo
	
	//recebe o numero correspondente a uma tabela e uma string com a ordem que quer o relatorio
	public ControleRelatorio(int entidade,String ordem) throws Exception
	{
		
		switch(entidade)//tabela
		{
			case 1://Cargo
			{
				if(ordem.equals("codigo"))//ordenado por codigo
				{
					List<Cargo1> lista = con.consultarTodosCargo("codigo");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioCargo.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Cargos ordenado por Código");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				else if(ordem.equals("nome"))//ordenado por nome
				{
					List<Cargo1> lista = con.consultarTodosCargo("nome");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioCargo.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Cargos ordenado por Nome");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				
				break;
			}
			case 2://Cidade
			{
				if(ordem.equals("codigo"))//ordenado por codigo
				{
					List<Cidade> lista = con.consultarTodosCidade("codigo");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioCidade.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Cidades ordenado por Código");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				else if(ordem.equals("nome"))//ordenado por nome
				{
					List<Cidade> lista = con.consultarTodosCidade("nome");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioCidade.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Cidades ordenado por Nome");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				if(ordem.equals("estado"))//ordenado por estado
				{
					List<Cidade> lista = con.consultarTodosCidade("estado");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioCidade.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Cidades ordenado por Estado");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				else if(ordem.equals("pais"))//ordenado por pais
				{
					List<Cidade> lista = con.consultarTodosCidade("pais");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioCidade.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Cidades ordenado por País");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				
				break;
			}
			case 3://Cliente
			{
				if(ordem.equals("codigo"))//ordenado por codigo
				{
					List<Cliente> lista = con.consultarTodosCliente("codigo");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioCliente.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Clientes ordenado por Código");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				else if(ordem.equals("nome"))//ordenado por nome
				{
					List<Cliente> lista = con.consultarTodosCliente("nome");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioCliente.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Clientes ordenado por Nome");//coloca o titulo no relatorio
					viewer.setVisible(true);;//mostra o relatorio
				}
				
				break;
			}
			case 4://Estado
			{
				if(ordem.equals("codigo"))//ordenado por codigo
				{
					List<Estado> lista = con.consultarTodosEstado("codigo");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioEstado.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Estados ordenado por Código");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				else if(ordem.equals("nome"))//ordenado por nome
				{
					List<Estado> lista = con.consultarTodosEstado("nome");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioEstado.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Estados ordenado por Nome");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				else if(ordem.equals("pais"))//ordenado por pais
				{
					List<Estado> lista = con.consultarTodosEstado("pais");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioEstado.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Estados ordenado por País");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				
				break;
			}
			case 5://Fornecedor
			{
				if(ordem.equals("codigo"))//ordenado por codigo
				{
					List<Fornecedor> lista = con.consultarTodosFornecedor("codigo");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioFornecedor.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Fornecedores ordenado por Código");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				else if(ordem.equals("nome"))//ordenado por nome
				{
					List<Fornecedor> lista = con.consultarTodosFornecedor("nome");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioFornecedor.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Fornecedores ordenado por Nome");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				
				break;
			}
			case 6://Funcionario
			{
				if(ordem.equals("codigo"))//ordenado por codigo
				{
					List<Funcionario> lista = con.consultarTodosFuncionario("codigo");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioFuncionario.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Funcionários ordenado por Código");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				else if(ordem.equals("nome"))//ordenado por nome
				{
					List<Funcionario> lista = con.consultarTodosFuncionario("nome");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioFuncionario.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Funcionários ordenado por Nome");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				
				break;
			}
			case 7://NotaEntradaControle
			{
				if(ordem.equals("codigo"))//ordenado por codigo
				{
					List<NotaEntradaControle> lista = con.consultarTodosNotaEntradaControle("codigo");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioNotaEntrada.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Notas de Entrada ordenado por Código");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				else if(ordem.equals("fornecedor"))//ordenado por fornecedor
				{
					List<NotaEntradaControle> lista = con.consultarTodosNotaEntradaControle("fornecedor");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioNotaEntrada.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Notas de Entrada ordenado por Fornecedor");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				
				break;
			}
			case 8://NotaSaidaControle
			{
				if(ordem.equals("codigo"))//ordenado por codigo
				{
					List<NotaSaidaControle> lista = con.consultarTodosNotaSaidaControle("codigo");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioNotaSaida.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Notas de Saida ordenado por Código");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				else if(ordem.equals("cliente"))//ordenado por cliente
				{
					List<NotaSaidaControle> lista = con.consultarTodosNotaSaidaControle("cliente");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioNotaSaida.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Notas de Saida ordenado por Cliente");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				
				break;
			}
			case 9://Pais
			{
				if(ordem.equals("codigo"))//ordenado por codigo
				{
					List<Pai> lista = con.consultarTodosPai("codigo");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioPais.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Países ordenado por Código");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				else if(ordem.equals("nome"))//ordenado por nome
				{
					List<Pai> lista = con.consultarTodosPai("nome");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioPais.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Países ordenado por Nome");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				
				break;
			}
			case 10://Produto
			{
				if(ordem.equals("codigo"))//ordenado por codigo
				{
					List<Produto> lista = con.consultarTodosProduto("codigo");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioProduto.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Produtos ordenado por Código");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				else if(ordem.equals("nome"))//ordenado por nome
				{
					List<Produto> lista = con.consultarTodosProduto("nome");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioProduto.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Produtos ordenado por Nome");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				
				break;
			}
			case 11://Usuario
			{
				if(ordem.equals("codigo"))//ordenado por codigo
				{
					List<Usuario> lista = con.consultarTodosUsuario("codigo");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioUsuario.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Usuários ordenado por Código");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				else if(ordem.equals("usuario"))//ordenado por usuario
				{
					List<Usuario> lista = con.consultarTodosUsuario("usuario");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioUsuario.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Usuários ordenado por Usuário");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				else if(ordem.equals("funcionario"))//ordenado por funcionario
				{
					List<Usuario> lista = con.consultarTodosUsuario("funcionario");//consulta todos os registros
					con.sair();//desconecta
					
					//Cria o relatorio
					JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "relatorioUsuario.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
					JasperViewer viewer = new JasperViewer(print,false);
					
					viewer.setTitle("Relatório de Usuários ordenado por Funcionário");//coloca o titulo no relatorio
					viewer.setVisible(true);//mostra o relatorio
				}
				
				break;
			}
			default:
			{
				break;
			}
		}
	}
	
	
	
}
