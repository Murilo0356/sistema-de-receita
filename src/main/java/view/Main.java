package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Enums.CategoriaReceita;
import Enums.TipoReceita;
import model.Dieta;
import model.Ingrediente;
import model.PlanoDeRefeicao;
import model.PlanoReceita;
import model.Receita;
import model.ReceitaIngrediente;
import model.Usuario;
import service.DietaService;
import service.IngredienteService;
import service.PlanoDeRefeicaoService;
import service.ReceitaService;
import service.UsuarioService;

public class Main {

    
     static Scanner scanner = new Scanner(System.in);
     static Usuario usuarioLogado = null; 

    private static UsuarioService usuarioService = new UsuarioService();
    private static ReceitaService receitaService = new ReceitaService();
    private static DietaService dietaService = new DietaService();
    private static IngredienteService ingredienteService = new IngredienteService();
    private static PlanoDeRefeicaoService planoService = new PlanoDeRefeicaoService();

    public static void main(String[] args) {
        
        System.out.println("#############################################");
        System.out.println("#      BEM-VINDO AO SISTEMA DE RECEITAS     #");
        System.out.println("#############################################");

        boolean rodando = true;

        while (rodando) {
            if (usuarioLogado == null) {
               
                boolean continuar = menuInicial();
                if (continuar == false) {
                    rodando = false;
                }
            } else {
                menuPrincipal();
            }
        }
        
        System.out.println("Sistema encerrado. Até logo!");
        scanner.close();
    }


    private static boolean menuInicial() {
        System.out.println("\n--- TELA INICIAL ---");
        System.out.println("1. Fazer Login");
        System.out.println("2. Cadastrar Novo Usuário");
        System.out.println("3. Sair");
        System.out.print("Escolha: ");
        
        String opcao = scanner.nextLine();

      
        if (opcao.equals("1")) {
            fazerLogin();
            return true; 
        } else if (opcao.equals("2")) {
            cadastrarUsuario();
            return true;
        } else if (opcao.equals("3")) {
            return false; 
        } else {
            System.out.println("Opção inválida! Tente novamente.");
            return true;
        }
    }

    private static void fazerLogin() {
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Usuario u = usuarioService.fazerLogin(email, senha);
        if (u != null) {
            usuarioLogado = u;
            System.out.println("\nLogin realizado com sucesso! Olá, " + u.getNome());
        } else {
            System.out.println("\nErro: E-mail ou senha incorretos.");
        }
    }

    private static void cadastrarUsuario() {
        System.out.println("\n--- NOVO CADASTRO ---");
        Usuario u = new Usuario();
        
        System.out.print("Nome completo: ");
        u.setNome(scanner.nextLine());
        System.out.print("E-mail: ");
        u.setEmail(scanner.nextLine());
        System.out.print("Senha: ");
        u.setSenha(scanner.nextLine());

        String msg = usuarioService.cadastrarUsuario(u);
        System.out.println(msg);
    }


    private static void menuPrincipal() {
        System.out.println("\n=================================");
        System.out.println(" MENU PRINCIPAL - " + usuarioLogado.getNome());
        System.out.println("=================================");
        System.out.println("1. Minhas Receitas / Nova Receita");
        System.out.println("2. Meus Planos de Refeição");
        System.out.println("3. Ver Relatórios do Sistema");
        System.out.println("0. Deslogar");
        System.out.print("Escolha: ");

        String opcao = scanner.nextLine();

        if (opcao.equals("1")) {
            menuReceitas();
        } else if (opcao.equals("2")) {
            menuPlanos();
        } else if (opcao.equals("3")) {
            menuRelatorios();
        } else if (opcao.equals("0")) {
            usuarioLogado = null;
            System.out.println("Você saiu da conta.");
        } else {
            System.out.println("Opção inválida.");
        }
    }


    private static void menuReceitas() {
        System.out.println("\n--- GESTÃO DE RECEITAS ---");
        System.out.println("1. Criar Nova Receita");
        System.out.println("2. Listar Todas as Receitas");
        System.out.println("3. Buscar Receita por Título");
        System.out.println("4. Atualizar Receita");  
        System.out.println("5. Remover Receita");    
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");

        String opcao = scanner.nextLine();

        if (opcao.equals("1")) {
            criarReceita();
            
        } else if (opcao.equals("2")) {
            listarReceitas();
            
        } else if (opcao.equals("3")) {
            System.out.print("Digite o termo de busca: ");
            String termo = scanner.nextLine();
            List<Receita> resultados = receitaService.buscarPorTitulo(termo);
            if (resultados.isEmpty()) {
                System.out.println("Nada encontrado.");
            } else {
                for (Receita r : resultados) {
                     System.out.println("ID: " + r.getId() + " | " + r.getTitulo());
                }
            }

        } else if (opcao.equals("4")) {
            atualizarReceita(); 

        } else if (opcao.equals("5")) {
            removerReceita();   
            
        } else if (opcao.equals("0")) {
            return;
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private static void listarReceitas() {
        List<Receita> lista = receitaService.listarTodas();
        System.out.println("\n--- LISTA DE RECEITAS (" + lista.size() + ") ---");
        for (Receita r : lista) {
            System.out.println("ID: " + r.getId() + " | " + r.getTitulo() + " (" + r.getCalorias() + " cal) - Autor: " + r.getAutor().getNome());
        }
    }

    private static void criarReceita() {
        System.out.println("\n--- NOVA RECEITA ---");
        Receita r = new Receita();
        r.setAutor(usuarioLogado);

        System.out.print("Título da Receita: ");
        r.setTitulo(scanner.nextLine());

        System.out.print("Descrição: ");
        r.setDescricao(scanner.nextLine());

        System.out.print("Passo a passo: ");
        r.setPassos(scanner.nextLine());

     
        System.out.println("Escolha a Categoria:");
        int i = 1;
        for (CategoriaReceita c : CategoriaReceita.values()) {
            System.out.println(i++ + ". " + c);
        }
        int catIndex = Integer.parseInt(scanner.nextLine()) - 1;
        r.setCategoria(CategoriaReceita.values()[catIndex]);

        System.out.println("Escolha o Tipo de Refeição:");
        i = 1;
        for (TipoReceita t : TipoReceita.values()) {
            System.out.println(i++ + ". " + t);
        }
        int tipoIndex = Integer.parseInt(scanner.nextLine()) - 1;
        r.setTipo(TipoReceita.values()[tipoIndex]);

        System.out.println("Escolha a Dieta adequada:");
        List<Dieta> dietas = dietaService.listarTodas();
        for (Dieta d : dietas) {
            System.out.println("ID: " + d.getId() + " - " + d.getDescricao());
        }
        System.out.print("Digite o ID da Dieta: ");
        long idDieta = Long.parseLong(scanner.nextLine());
        for(Dieta d : dietas) if(d.getId() == idDieta) r.setDieta(d);

        List<ReceitaIngrediente> ingredientesDaReceita = new ArrayList<>();
        boolean adicionando = true;
        
        while (adicionando) {
            System.out.print("\nNome do Ingrediente (ex: Ovo): ");
            String nomeIng = scanner.nextLine();
            
            Ingrediente ing = ingredienteService.buscarPorNome(nomeIng);
            
            if (ing == null) {
                System.out.println("Ingrediente não cadastrado! Vamos cadastrar.");
                System.out.print("Calorias (por 100g ou unidade): ");
                double cals = Double.parseDouble(scanner.nextLine());
                ingredienteService.criarIngrediente(nomeIng, cals, "Padrão");
                ing = ingredienteService.buscarPorNome(nomeIng);
            }

            ReceitaIngrediente item = new ReceitaIngrediente();
            item.setReceita(r);
            item.setIngrediente(ing);
            
            System.out.print("Quantidade usada: ");
            item.setQuantidade(Double.parseDouble(scanner.nextLine()));
            
            System.out.print("Unidade (ex: xícara, gramas): ");
            item.setUnidadeMedida(scanner.nextLine());
            
            ingredientesDaReceita.add(item);

            System.out.print("Adicionar outro ingrediente? (s/n): ");
            String resp = scanner.nextLine();
            if (!resp.equalsIgnoreCase("s")) {
                adicionando = false;
            }
        }
        
        r.setIngredientes(ingredientesDaReceita);
                String resultado = receitaService.cadastrarReceita(r);
        System.out.println(resultado);
        System.out.println("Total de Calorias calculada: " + r.getCalorias());
    }

    private static void menuPlanos() {
        System.out.println("\n--- MEUS PLANOS DE REFEIÇÃO ---");
        System.out.println("1. Criar Novo Plano Semanal");
        System.out.println("2. Ver Meus Planos");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");

        String opcao = scanner.nextLine();

        if (opcao.equals("1")) {
            System.out.print("Nome do Plano (ex: Dieta de Verão): ");
            String nome = scanner.nextLine();
            
            PlanoDeRefeicao plano = new PlanoDeRefeicao();
            plano.setNome(nome);
            
            System.out.println("Vamos adicionar uma receita para a Segunda-Feira.");
            listarReceitas();
            System.out.print("Digite o ID da Receita para adicionar: ");
            long idReceita = Long.parseLong(scanner.nextLine());
            
            Receita rSelecionada = new dao.ReceitaDao().buscarPorId(idReceita);
            
            if (rSelecionada != null) {
                List<PlanoReceita> itens = new ArrayList<>();
                PlanoReceita item = new PlanoReceita();
                item.setPlanoDeRefeicao(plano);
                item.setReceita(rSelecionada);
                item.setDiaDaSemana("Segunda-Feira");
                itens.add(item);
                
                plano.setItensPlano(itens);
                
                String res = planoService.criarPlano(plano, usuarioLogado);
                System.out.println(res);
            } else {
                System.out.println("Receita não encontrada.");
            }

        } else if (opcao.equals("2")) {
            List<PlanoDeRefeicao> meusPlanos = planoService.listarPorUsuario(usuarioLogado);
            if (meusPlanos == null || meusPlanos.isEmpty()) {
                System.out.println("Você não tem planos cadastrados.");
            } else {
                for (PlanoDeRefeicao p : meusPlanos) {
                    System.out.println("- " + p.getNome() + " (ID: " + p.getId() + ")");
                }
            }
        } else if (opcao.equals("0")) {
            return;
        } else {
            System.out.println("Opção inválida.");
        }
    }



    private static void atualizarReceita() {
        System.out.println("\n--- ATUALIZAR RECEITA ---");
        System.out.print("Digite o ID da receita que deseja alterar: ");
        String idDigitado = scanner.nextLine();

        if (!idDigitado.matches("\\d+")) { 
            System.out.println("ID inválido! Digite apenas números.");
            return;
        }

        long id = Long.parseLong(idDigitado);
        Receita r = receitaService.buscarPorId(id);

        if (r == null) {
            System.out.println("Receita não encontrada com esse ID.");
            return;
        }

        if (r.getAutor().getId() != usuarioLogado.getId()) {
            System.out.println("ERRO: Você só pode alterar receitas criadas por você!");
            return;
        }

        System.out.println("Título atual: " + r.getTitulo());
        System.out.print("Novo título (ou ENTER para manter): ");
        String novoTitulo = scanner.nextLine();
        if (!novoTitulo.isEmpty()) {
            r.setTitulo(novoTitulo);
        }

        System.out.println("Descrição atual: " + r.getDescricao());
        System.out.print("Nova descrição (ou ENTER para manter): ");
        String novaDesc = scanner.nextLine();
        if (!novaDesc.isEmpty()) {
            r.setDescricao(novaDesc);
        }
        
        System.out.println("Passos atuais: " + r.getPassos());
        System.out.print("Novos passos (ou ENTER para manter): ");
        String novosPassos = scanner.nextLine();
        if (!novosPassos.isEmpty()) {
            r.setPassos(novosPassos);
        }

     
        receitaService.atualizarReceita(r);
        System.out.println("Receita atualizada com sucesso!");
    }

    private static void removerReceita() {
        System.out.println("\n--- REMOVER RECEITA ---");
        System.out.print("Digite o ID da receita para excluir: ");
        String idDigitado = scanner.nextLine();

        if (!idDigitado.matches("\\d+")) {
            System.out.println("ID inválido! Digite apenas números.");
            return;
        }

        long id = Long.parseLong(idDigitado);
        Receita r = receitaService.buscarPorId(id);

        if (r == null) {
            System.out.println("Receita não encontrada.");
            return;
        }

  
        if (r.getAutor().getId() != usuarioLogado.getId()) {
            System.out.println("ERRO: Você só pode excluir suas próprias receitas!");
            return;
        }

        System.out.print("Tem certeza que deseja excluir '" + r.getTitulo() + "'? (s/n): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("s")) {
            // EXECUÇÃO DIRETA (Sem Try-Catch)
            receitaService.removerReceita(id);
            System.out.println("Receita excluída com sucesso.");
        } else {
            System.out.println("Operação cancelada.");
        }
    }
    private static void menuRelatorios() {
        System.out.println("\n--- RELATÓRIOS GERENCIAIS ---");
        System.out.println("1. Dietas Mais Populares");
        System.out.println("2. Receitas Mais Usadas em Planos");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        
        String op = scanner.nextLine();
        
        if (op.equals("1")) {
            List<Object[]> dados = dietaService.gerarRelatorioPopularidade();
            System.out.println("\nRANKING DE DIETAS:");
            System.out.println("Nome da Dieta \t| Qtd Receitas");
            System.out.println("------------------------------");
            for (Object[] linha : dados) {
                System.out.println(linha[0] + " \t\t| " + linha[1]);
            }
        } else if (op.equals("2")) {
            System.out.println("\nTOP 5 RECEITAS NOS PLANOS:");
            List<Receita> tops = new dao.ReceitaDao().buscarReceitasMaisPopulares(5);
            for (Receita r : tops) {
                System.out.println("- " + r.getTitulo() + " (Autor: " + r.getAutor().getNome() + ")");
            }
        } else if (op.equals("0")) {
            return;
        } else {
            System.out.println("Opção inválida.");
        }
    }
}