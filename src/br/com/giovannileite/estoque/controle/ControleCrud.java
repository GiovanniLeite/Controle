package br.com.giovannileite.estoque.controle;



import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

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

/*
 * Version information (1.0)
 * 
 * Author (Giovanni Moraes de Oliveira Leite)
 */

//classe criada para realizar operacoes no banco
public class ControleCrud {

		EntityManagerFactory emf =	Persistence.createEntityManagerFactory("Controle");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		/*
		 1 - Cargo                    
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

		//-------------------------Cadastra o registro
		
		public void salvar(Object obj)//recebe o objeto correspondente a uma tabela
		{
			tx.begin(); //inicia a transação
			em.persist(obj);//salva o objeto no banco
			tx.commit();//termina a transação
		}
		
		public void salvarNotaEntrada(NotaEntradaControle obj,List<ProdutoNotaEntrada> produtoNota,List<Produto> auxiliarAtualizarProduto)
		{
			tx.begin(); //inicia a transação
			em.persist(obj);//salva o objeto no banco
			for(int i = 0;i < produtoNota.size();i++)//percorre a lista de produtos da nota
			{
				em.persist(produtoNota.get(i));//salva o objeto no banco
			}
			//Atualiza na tabela a quantidades de produtos
			for(int i = 0; i < auxiliarAtualizarProduto.size();i++)//percorre a lista de produtos que serao atualizados na tabela produto
			{
				em.merge(auxiliarAtualizarProduto.get(i));//atualiza objeto no banco
			}
			tx.commit();//termina a transação
		}
		
		public void salvarNotaSaida(NotaSaidaControle obj,List<ProdutoNotaSaida> produtoNota,List<Produto> auxiliarAtualizarProduto)
		{
			tx.begin(); //inicia a transação
			em.persist(obj);//salva o objeto no banco
			for(int i = 0;i < produtoNota.size();i++)//percorre a lista de produtos da nota
			{
				em.persist(produtoNota.get(i));//salva o objeto no banco
			}
			//Atualiza na tabela a quantidades de produtos
			for(int i = 0; i < auxiliarAtualizarProduto.size();i++)//percorre a lista de produtos que serao atualizados na tabela produto
			{
				em.merge(auxiliarAtualizarProduto.get(i));//atualiza objeto no banco
			}
			tx.commit();//termina a transação
		}
		
		//Altera o registro
		public void atualizar(Object obj)//recebe o objeto correspondente a uma tabela
		{
			tx.begin();//inicia a transação
			em.merge(obj);//Atualiza o registo no banco
			tx.commit();//termina a transação
		}
		
		public void atualizarNotaEntrada(NotaEntradaControle obj,List<ProdutoNotaEntrada> produtoNotaSalvar
				,List<ProdutoNotaEntrada> produtoNotaExcluir,List<Produto> auxiliarAtualizarProduto)
		{
			tx.begin(); //inicia a transação
			em.merge(obj);//salva o objeto no banco
			for(int i = 0;i < produtoNotaSalvar.size();i++)//percorre a lista de produtos da nota que serao salvos
			{
				em.merge(produtoNotaSalvar.get(i));//atualiza o objeto no banco
			}
			for(int i = 0;i < produtoNotaExcluir.size();i++)//percorre a lista de produtos da nota que serao excluidos
			{	
				em.remove(produtoNotaExcluir.get(i));//exclui o objeto no banco
			}
			
			//Atualiza na tabela quantidades de produtos
			for(int i = 0; i < auxiliarAtualizarProduto.size();i++)//percorre a lista de produtos que terao sua quantidade atualizada em estoque
			{
				em.merge(auxiliarAtualizarProduto.get(i));//atualiza objeto no banco
			}
			tx.commit();//termina a transação
		}
		
		public void atualizarNotaSaida(NotaSaidaControle obj,List<ProdutoNotaSaida> produtoNotaSalvar
				,List<ProdutoNotaSaida> produtoNotaExcluir,List<Produto> auxiliarAtualizarProduto)
		{
			tx.begin(); //inicia a transação
			em.merge(obj);//salva o objeto no banco
			for(int i = 0;i < produtoNotaSalvar.size();i++)//percorre a lista de produtos da nota que serao salvos
			{
				em.merge(produtoNotaSalvar.get(i));//atualiza o objeto no banco
			}
			for(int i = 0;i < produtoNotaExcluir.size();i++)//percorre a lista de produtos da nota que serao excluidos
			{
				em.remove(produtoNotaExcluir.get(i));//exclui do objeto no banco
			}
			
			//Atualiza na tabela quantidades de produtos
			for(int i = 0; i < auxiliarAtualizarProduto.size();i++)//percorre a lista de produtos que terao sua quantidade atualizada em estoque
			{
				em.merge(auxiliarAtualizarProduto.get(i));//atualiza objeto no banco
			}
			tx.commit();//termina a transação
		}
		
		//------------------------------Exclui o registro
		
		public void excluir(int entidade,int codigo)//recebe um numero correspondente a uma tabela,e outro correspondente a chave
		{
			switch(entidade)//recebe o numero correspondente a uma das tabelas
			{
				case 1:
				{
					Cargo1 cargo = em.find(Cargo1.class,codigo);//procura e pega o registro com base no seu codigo
					tx.begin();//inicia a transação
					em.remove(cargo);//remove o registro do banco
					tx.commit();//termina a transação
					break;
				}
				case 2:
				{
					Cidade cidade = em.find(Cidade.class,codigo);//procura e pega o registro com base no seu codigo
					tx.begin();//inicia a transação
					em.remove(cidade);//remove o registro do banco
					tx.commit();//termina a transação
					break;
				}
				case 3:
				{
					Cliente cliente = em.find(Cliente.class,codigo);//procura e pega o registro com base no seu codigo
					tx.begin();//inicia a transação
					em.remove(cliente);//remove o registro do banco
					tx.commit();//termina a transação
					break;
				}
				case 4:
				{
					Estado estado = em.find(Estado.class,codigo);//procura e pega o registro com base no seu codigo
					tx.begin();//inicia a transação
					em.remove(estado);//remove o registro do banco
					tx.commit();//termina a transação
					break;
				}
				case 5:
				{
					Fornecedor fornecedor = em.find(Fornecedor.class,codigo);//procura e pega o registro com base no seu codigo
					tx.begin();//inicia a transação
					em.remove(fornecedor);//remove o registro do banco
					tx.commit();//termina a transação
					break;
				}
				case 6:
				{
					Funcionario funcionario = em.find(Funcionario.class,codigo);//procura e pega o registro com base no seu codigo
					tx.begin();//inicia a transação
					em.remove(funcionario);//remove o registro do banco
					tx.commit();//termina a transação
					break;
				}
				case 7:
				{
					NotaEntradaControle notaEntrada = em.find(NotaEntradaControle.class,codigo);//procura e pega o registro com base no seu codigo
					tx.begin();//inicia a transação
					em.remove(notaEntrada);//remove o registro do banco
					tx.commit();//termina a transação
					break;
				}
				case 8:
				{
					NotaSaidaControle notaSaida = em.find(NotaSaidaControle.class,codigo);//procura e pega o registro com base no seu codigo
					tx.begin();//inicia a transação
					em.remove(notaSaida);//remove o registro do banco
					tx.commit();//termina a transação
					break;
				}
				case 9:
				{
					Pai pais = em.find(Pai.class,codigo);//procura e pega o registro com base no seu codigo
					tx.begin();//inicia a transação
					em.remove(pais);//remove o registro do banco
					tx.commit();//termina a transação
					break;
				}
				case 10:
				{
					Produto produto = em.find(Produto.class,codigo);//procura e pega o registro com base no seu codigo
					tx.begin();//inicia a transação
					em.remove(produto);//remove o registro do banco
					tx.commit();//termina a transação
					break;
				}
				case 11:
				{
					Usuario usuario = em.find(Usuario.class,codigo);//procura e pega o registro com base no seu codigo
					tx.begin();//inicia a transação
					em.remove(usuario);//remove o registro do banco
					tx.commit();//termina a transação
					break;
				}
				default:
				{
					break;
				}
			}
		}
		
		public void excluirNotaEntrada(int codigo,List<ProdutoNotaEntrada> produtoNota,List<Produto> AtualizarProduto)
		{
			NotaEntradaControle nota = em.find(NotaEntradaControle.class,codigo);//procura e pega o registro pelo codigo
			tx.begin(); //inicia a transação
			em.remove(nota);//remove o objeto no banco
			for(int i = 0;i < produtoNota.size();i++)
			{
				int codigoProduto = produtoNota.get(i).getCodigo();//pega o codigo do registro
				ProdutoNotaEntrada produto = em.find(ProdutoNotaEntrada.class,codigoProduto);//procura e pega o registro pelo codigo
				em.remove(produto);//remove o objeto no banco
			}
			for(int i = 0;i < AtualizarProduto.size();i++)
			{
				int codigoProduto = AtualizarProduto.get(i).getCodigo();//pega o codigo do registro
				Produto produto = em.find(Produto.class,codigoProduto);//procura e pega o registro pelo codigo
				em.merge(produto);//atualiza o produto no banco
			}
			tx.commit();//termina a transação
		}
		
		public void excluirNotaSaida(int codigo,List<ProdutoNotaSaida> produtoNota,List<Produto> AtualizarProduto)
		{
			NotaSaidaControle nota = em.find(NotaSaidaControle.class,codigo);//procura o registro pelo codigo
			tx.begin(); //inicia a transação
			em.remove(nota);//remove o objeto no banco
			for(int i = 0;i < produtoNota.size();i++)
			{
				int codigoProduto = produtoNota.get(i).getCodigo();//pega o codigo do registro
				ProdutoNotaSaida produto = em.find(ProdutoNotaSaida.class,codigoProduto);//procura e pega o registro pelo codigo
				em.remove(produto);//remove o objeto no banco

			}
			for(int i = 0;i < AtualizarProduto.size();i++)
			{
				int codigoProduto = AtualizarProduto.get(i).getCodigo();//pega o codigo do registro
				Produto produto = em.find(Produto.class,codigoProduto);//procura e pega o registro pelo codigo
				em.merge(produto);//atualiza o produto no banco
			}
			tx.commit();//termina a transação
		}
		
		
		//---------------------------CONSULTA DE REGISTROS DA TABELA
		//---------------------------Consulta todos os registros da tabela 
		public List<Cargo1> consultarTodosCargo(String ordem)
		{
			if(ordem.equals("codigo"))//ordenado por codigo
			{
				TypedQuery<Cargo1> query = em.createQuery("SELECT c FROM Cargo1 c ",Cargo1.class);//consulta todos os registros do banco 
				List<Cargo1> cargos = query.getResultList();//o list recebe todos os registros 
				return cargos;//retorna o list com os registros
			}
			else if(ordem.equals("nome"))//ordenado por nome
			{
				TypedQuery<Cargo1> query = em.createQuery("SELECT c FROM Cargo1 c order by nome",Cargo1.class);//consulta todos os registros do banco 
				List<Cargo1> cargos = query.getResultList();//o list recebe todos os registros 
				return cargos;//retorna o list com os registros
			}
			return null;
		}
		
		//Consulta todos os registros da tabela 
		public List<Cidade> consultarTodosCidade(String ordem)
		{
			if(ordem.equals("codigo"))//ordenado por codigo
			{
				TypedQuery<Cidade> query = em.createQuery("SELECT c FROM Cidade c",Cidade.class);//consulta todos os registros do banco 
				List<Cidade> cidades = query.getResultList();//o list recebe todos os registros 
				return cidades;//retorna o list com os registros
			}
			else if(ordem.equals("nome"))//ordenado por nome
			{
				TypedQuery<Cidade> query = em.createQuery("SELECT c FROM Cidade c order by nome",Cidade.class);//consulta todos os registros do banco
				List<Cidade> cidades = query.getResultList();//o list recebe todos os registros 
				return cidades;//retorna o list com os registros
			}
			else if(ordem.equals("estado"))//ordenado por estado
			{
				TypedQuery<Cidade> query = em.createQuery("SELECT c FROM Cidade c order by uf",Cidade.class);//consulta todos os registros do banco
				List<Cidade> cidades = query.getResultList();//o list recebe todos os registros 
				return cidades;//retorna o list com os registros
			}
			else if(ordem.equals("pais"))//ordenado por pais
			{
				TypedQuery<Cidade> query = em.createQuery("SELECT c FROM Cidade c order by pais",Cidade.class);//consulta todos os registros do banco
				List<Cidade> cidades = query.getResultList();//o list recebe todos os registros 
				return cidades;//retorna o list com os registros
			}
			return null;
		}
		
		//Consulta todos os registros da tabela 
		public List<Cliente> consultarTodosCliente(String ordem)
		{
			if(ordem.equals("codigo"))//ordenado por codigo
			{
				TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c",Cliente.class);//consulta todos os registros do banco
				List<Cliente> clientes = query.getResultList();//o list recebe todos os registros 
				return clientes;//retorna o list com os registros
			}
			else if(ordem.equals("nome"))//ordenado por nome
			{
				TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c order by nome",Cliente.class);//consulta todos os registros do banco
				List<Cliente> clientes = query.getResultList();//o list recebe todos os registros 
				return clientes;//retorna o list com os registros
			}
			return null;
		}
		
		//Consulta todos os registros da tabela 
		public List<Estado> consultarTodosEstado(String ordem)
		{
			if(ordem.equals("codigo"))//ordenado por codigo
			{
				TypedQuery<Estado> query = em.createQuery("SELECT e FROM Estado e",Estado.class);//consulta todos os registros do banco
				List<Estado> estados = query.getResultList();//o list recebe todos os registros
				return estados;//retorna o list com os registros
			}
			else if(ordem.equals("nome"))//ordenado por nome
			{
				TypedQuery<Estado> query = em.createQuery("SELECT e FROM Estado e order by nome",Estado.class);//consulta todos os registros do banco
				List<Estado> estados = query.getResultList();//o list recebe todos os registros
				return estados;//retorna o list com os registros
			}
			else if(ordem.equals("pais"))//ordenado por pais
			{
				TypedQuery<Estado> query = em.createQuery("SELECT e FROM Estado e order by pais",Estado.class);//consulta todos os registros do banco
				List<Estado> estados = query.getResultList();//o list recebe todos os registros
				return estados;//retorna o list com os registros
			}
			return null;
		}
		
		//Consulta todos os registros da tabela 
		public List<Fornecedor> consultarTodosFornecedor(String ordem)
		{
			if(ordem.equals("codigo"))//ordenado por codigo
			{
				TypedQuery<Fornecedor> query = em.createQuery("SELECT f FROM Fornecedor f",Fornecedor.class);//consulta todos os registros do banco
				List<Fornecedor> fornecedores = query.getResultList();//o list recebe todos os registros
				return fornecedores;//retorna o list com os registros
			}
			else if(ordem.equals("nome"))//ordenado por nome
			{
				TypedQuery<Fornecedor> query = em.createQuery("SELECT f FROM Fornecedor f order by nome",Fornecedor.class);//consulta todos os registros do banco
				List<Fornecedor> fornecedores = query.getResultList();//o list recebe todos os registros
				return fornecedores;//retorna o list com os registros
			}
			return null;
		}
		
		//Consulta todos os registros da tabela 
		public List<Funcionario> consultarTodosFuncionario(String ordem)
		{
			if(ordem.equals("codigo"))//ordenado por codigo
			{
				TypedQuery<Funcionario> query = em.createQuery("SELECT f FROM Funcionario f",Funcionario.class);//consulta todos os registros do banco
				List<Funcionario> fornecedores = query.getResultList();//o list recebe todos os registros
				return fornecedores;//retorna o list com os registros
			}
			else if(ordem.equals("nome"))//ordenado por nome
			{
				TypedQuery<Funcionario> query = em.createQuery("SELECT f FROM Funcionario f order by nome",Funcionario.class);//consulta todos os registros do banco
				List<Funcionario> fornecedores = query.getResultList();//o list recebe todos os registros
				return fornecedores;//retorna o list com os registros
			}
			return null;
		}
		
		//Consulta todos os registros da tabela 
		public List<NotaEntradaControle> consultarTodosNotaEntradaControle(String ordem)
		{
			if(ordem.equals("codigo"))//ordenado por codigo
			{
				TypedQuery<NotaEntradaControle> query = em.createQuery("SELECT n FROM NotaEntradaControle n",NotaEntradaControle.class);//consulta todos os registros do banco
				List<NotaEntradaControle> notas = query.getResultList();//o list recebe todos os registros
				return notas;//retorna o list com os registros
			}
			else if(ordem.equals("numeroNota"))//ordenado por numero da nota
			{
				TypedQuery<NotaEntradaControle> query = em.createQuery("SELECT n FROM NotaEntradaControle n order by numeroNota",NotaEntradaControle.class);//consulta todos os registros do banco
				List<NotaEntradaControle> notas = query.getResultList();//o list recebe todos os registros
				return notas;//retorna o list com os registros
			}
			if(ordem.equals("data"))//ordenado por data
			{
				TypedQuery<NotaEntradaControle> query = em.createQuery("SELECT n FROM NotaEntradaControle n order by dataCompra",NotaEntradaControle.class);//consulta todos os registros do banco
				List<NotaEntradaControle> notas = query.getResultList();//o list recebe todos os registros
				return notas;//retorna o list com os registros
			}
			else if(ordem.equals("fornecedor"))//ordenado por fornecedor
			{
				TypedQuery<NotaEntradaControle> query = em.createQuery("SELECT n FROM NotaEntradaControle n order by nomeFornecedor",NotaEntradaControle.class);//consulta todos os registros do banco
				List<NotaEntradaControle> notas = query.getResultList();//o list recebe todos os registros
				return notas;//retorna o list com os registros
			}
			return null;
		}

		//Consulta todos os registros da tabela 
		public List<NotaSaidaControle> consultarTodosNotaSaidaControle(String ordem)
		{
			if(ordem.equals("codigo"))//ordenado por codigo
			{
				TypedQuery<NotaSaidaControle> query = em.createQuery("SELECT n FROM NotaSaidaControle n",NotaSaidaControle.class);//consulta todos os registros do banco
				List<NotaSaidaControle> notas = query.getResultList();//o list recebe todos os registros
				return notas;//retorna o list com os registros
			}
			else if(ordem.equals("numeroNota"))//ordenado por numero da nota
			{
				TypedQuery<NotaSaidaControle> query = em.createQuery("SELECT n FROM NotaSaidaControle n order by numeroNota",NotaSaidaControle.class);//consulta todos os registros do banco
				List<NotaSaidaControle> notas = query.getResultList();//o list recebe todos os registros
				return notas;//retorna o list com os registros
			}
			if(ordem.equals("data"))//ordenado por data
			{
				TypedQuery<NotaSaidaControle> query = em.createQuery("SELECT n FROM NotaSaidaControle n order by dataVenda",NotaSaidaControle.class);//consulta todos os registros do banco
				List<NotaSaidaControle> notas = query.getResultList();//o list recebe todos os registros
				return notas;//retorna o list com os registros
			}
			else if(ordem.equals("cliente"))//ordenado por cliente
			{
				TypedQuery<NotaSaidaControle> query = em.createQuery("SELECT n FROM NotaSaidaControle n order by nomeCliente",NotaSaidaControle.class);//consulta todos os registros do banco
				List<NotaSaidaControle> notas = query.getResultList();//o list recebe todos os registros
				return notas;//retorna o list com os registros
			}
			return null;
		}
		
		//Consulta todos os registros da tabela 
		public List<Pai> consultarTodosPai(String ordem)
		{
			if(ordem.equals("codigo"))//ordenado por codigo
			{
				TypedQuery<Pai> query = em.createQuery("SELECT p FROM Pai p",Pai.class);//consulta todos os registros do banco
				List<Pai> paises = query.getResultList();//o list recebe todos os registros
				return paises;//retorna o list com os registros
			}
			else if(ordem.equals("nome"))//ordenado por nome
			{
				TypedQuery<Pai> query = em.createQuery("SELECT p FROM Pai p order by nome",Pai.class);//consulta todos os registros do banco
				List<Pai> paises = query.getResultList();//o list recebe todos os registros
				return paises;//retorna o list com os registros
			}
			return null;
		}
		
		//Consulta todos os registros da tabela 
		public List<Produto> consultarTodosProduto(String ordem)
		{
			if(ordem.equals("codigo"))//ordenado por codigo
			{
				TypedQuery query = em.createQuery("SELECT p FROM Produto p",Produto.class);//consulta todos os registros do banco
				List<Produto> produtos = query.getResultList();//o list recebe todos os registros
				return produtos;//retorna o list com os registros
			}
			else if(ordem.equals("nome"))//ordenado por nome
			{
				TypedQuery query = em.createQuery("SELECT p FROM Produto p order by nome",Produto.class);//consulta todos os registros do banco
				List<Produto> produtos = query.getResultList();//o list recebe todos os registros
				return produtos;//retorna o list com os registros
			}
			return null;
		}
		
		//Consulta todos os registros da tabela 
		public List<Usuario> consultarTodosUsuario(String ordem)
		{
			if(ordem.equals("codigo"))//ordenado por codigo
			{
				TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u",Usuario.class);//consulta todos os registros do banco
				List<Usuario> usuarios = query.getResultList();//o list recebe todos os registros
				return usuarios;//retorna o list com os registros
			}
			else if(ordem.equals("usuario"))//ordenado por usuario
			{
				TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u order by usuario",Usuario.class);//consulta todos os registros do banco
				List<Usuario> usuarios = query.getResultList();//o list recebe todos os registros
				return usuarios;//retorna o list com os registros
			}
			else if(ordem.equals("funcionario"))//ordenado por funcionario
			{
				TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u order by funcionario",Usuario.class);//consulta todos os registros do banco
				List<Usuario> usuarios = query.getResultList();//o list recebe todos os registros
				return usuarios;//retorna o list com os registros
			}
			return null;
		}
		
		//Consulta todos os registros da tabela 
		public List<ProdutoNotaEntrada> consultarTodosProdutoNotaEntrada()
		{
			TypedQuery<ProdutoNotaEntrada> query = em.createQuery("SELECT p FROM ProdutoNotaEntrada p",ProdutoNotaEntrada.class);//consulta todos os registros do banco
			List<ProdutoNotaEntrada> produtoEntrada = query.getResultList();//o list recebe todos os registros
			return produtoEntrada;//retorna o list com os registros
		}
				
		//Consulta todos os registros da tabela 
		public List<ProdutoNotaSaida> consultarTodosProdutoNotaSaida()
		{
			TypedQuery<ProdutoNotaSaida> query = em.createQuery("SELECT p FROM ProdutoNotaSaida p",ProdutoNotaSaida.class);//consulta todos os registros do banco
			List<ProdutoNotaSaida> produtoSaida = query.getResultList();//o list recebe todos os registros
			return produtoSaida;//retorna o list com os registros
		}
		
		//Consultar UM produto
		public Produto consultarProduto(int codigoProduto)
		{
			Produto produto = em.find(Produto.class,codigoProduto);//consulta o registro por codigo
			return produto;//retorna o registro
		}
		
		public void sair()//desconecta
		{
			em.close();
			emf.close();
		}
		
		//---------seta a mascara no textfield
		public MaskFormatter setMascara(String mascara)
		{
		    
			MaskFormatter mask = null;
		    try
		    {
		        mask = new MaskFormatter(mascara);                      
		    }
		    catch(java.text.ParseException ex)
		    {
		    	
		    }
		    return mask;
		}

}
