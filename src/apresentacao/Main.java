package apresentacao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import model.Cliente;
import model.Contrato;
import model.Corretor;
import model.Imovel;
import model.Locacao;
import persistencia.ClienteDAO;
import persistencia.ContratoDAO;
import persistencia.CorretorDAO;
import persistencia.ImovelDAO;
import persistencia.LocacaoDAO;


public class Main {
    public static void main(String[] args) throws SQLException {
        @SuppressWarnings("resource")
        Scanner tec = new Scanner(System.in);
        int res = 0;

        do {  
            
        System.out.println("Bem vindo(a) à Conecta Imobiliária");
        System.out.println("Selecione a opção que deseja realizar: ");
        System.out.println("1. Cadastrar imóvel"); 
        System.out.println("2. Cadastrar cliente"); 
        System.out.println("3. Cadastrar corretor");
        System.out.println("4. Contrato de anúncio");
        System.out.println("5. Listar imóveis");
        System.out.println("6. Listar clientes");
        System.out.println("7. Listar corretores");
        System.out.println("8. Listar contratos");
        System.out.println("9. Fechar locação");
        System.out.println("10. Sair");
        res = tec.nextInt();
        tec.nextLine();

        ClienteDAO c = new ClienteDAO();
        CorretorDAO cor = new CorretorDAO();
        ContratoDAO cont = new ContratoDAO();    // inicializando as classes DAO
        ImovelDAO i = new ImovelDAO();
        LocacaoDAO loc = new LocacaoDAO();


        if (res == 1) {
            Cliente cliente = null; // instanciando objeto

            System.out.println("O proprietário do imóvel já possui cadastro ? Digite 'sim' ou 'não'");
            String r = tec.nextLine();

            if (r.equalsIgnoreCase("sim")) {
                System.out.println("Digite o CPF do cliente cadastrado:");
                String cpf = tec.nextLine();
                cliente = c.findByCpfCliente(cpf); // consulta o cliente pelo cpf

                if (cliente != null) {
                    System.out.println("Cliente encontrado: " + cliente.getNome());             
                } else {
                    System.out.println("Cliente não encontrado. Caso deseje cadastrá-lo, encerre a seção e cadastre o Cliente. \n");
                    break; 
                }

                System.out.println("Informe o CRECI do corretor responsável pelo imóvel:");
                String creci = tec.nextLine();
                Corretor corretor = cor.findByCreci(creci); // consulta corretor pelo creci
                if (corretor != null) {
                    System.out.println("Corretor encontrado: " + corretor.getNome());
                } else {
                    System.out.println("Corretor não encontrado com o creci fornecido.\n");
                }
                
                System.out.println("Digite o endereço do imóvel:");
                String endereco = tec.nextLine();
                System.out.println("Digite o tipo de imóvel:");
                String tipo = tec.nextLine();                                 // dados de entrada para o cadastrO do imovel
                System.out.println("Digite uma descrição do imóvel:");
                String descricao = tec.nextLine();
                System.out.println("Digite a área do imóvel:");
                double area = tec.nextDouble();
                System.out.println("Digite o valor de venda do imóvel:");
                double valor_venda = tec.nextDouble();
                System.out.println("Digite o valor de aluguel do imóvel:");
                double valor_aluguel = tec.nextDouble();
                System.out.println("Digite o ano de construção do imóvel:");
                int ano_construcao = tec.nextInt();
                tec.nextLine(); 

                Imovel imovel = new Imovel(cliente, corretor, endereco, tipo, descricao, area, valor_venda, valor_aluguel, ano_construcao); 
                i.create(imovel); // cadastro do imovel

                System.out.println("Imóvel cadastrado com sucesso!\n");
            
            } else {
            System.out.println("O propritário precisa estar cadastrado!\n");
        }


        // opção 2 de cadastrar cliente
        } else if (res==2) {
            System.out.println("Digite o nome do cliente:");
            String nome = tec.nextLine();
            System.out.println("Digite o email do cliente:");
            String email = tec.nextLine();                                  // dados de entrada para o cadastro de cliente
            System.out.println("Digite o telefone do cliente:");
            String tel = tec.nextLine();
            System.out.println("Digite a data de nascimento do cliente:");
            String data_nasc = tec.nextLine();
            System.out.println("Digite o CPF do cliente:");
            String cpf = tec.nextLine();
            System.out.println("Digite o endereço do cliente:");
            String endereco = tec.nextLine();

            Cliente cliente = new Cliente(nome, email, tel, data_nasc, cpf, endereco);
            c.create(cliente); // cadastro do cliente

            System.out.println("\nCliente cadastrado com sucesso!\n");


        // opção 3 de cadastrar corretor
        } else if (res==3) {
            System.out.println("Digite o nome do corretor:");
            String nome = tec.nextLine();                                       
            System.out.println("Digite o email do corretor:");                    // dados de entrada para o cadastro de corretor
            String email = tec.nextLine();
            System.out.println("Digite o telefone do corretor:");
            String tel = tec.nextLine();
            System.out.println("Digite o número CRECI do corretor:");
            String creci = tec.nextLine();

            Corretor corretor = new Corretor(nome, email, tel, creci);
            cor.create(corretor);  //cadastro do corretor
            
            System.out.println("\nCorretor cadastrado com sucesso!\n");
        
        
        // opção 4 de contrato    
        } else if (res == 4) {
        Corretor corretor = null;    // instanciando objetos 
        Cliente cliente = null;

        System.out.println("O cliente que deseja fazer o contrato, ja possui cadastro? Digite 'sim' ou 'não'");
        String r = tec.nextLine();
        if (r.equalsIgnoreCase("sim")) {                        //  consulta do cliente para contrato
            System.out.println("Digite o CPF do cliente cadastrado:");
            String cpf = tec.nextLine();
            cliente = c.findByCpfCliente(cpf);

            if (cliente != null) {
                System.out.println("Cliente encontrado: " + cliente.getNome());         
            } else {
                System.out.println("Cliente não encontrado. Caso deseje cadastrá-lo, encerre a seção e cadastre o Cliente. ");
            }
        }
        
        System.out.println("O imovel que fará parte do contrato ja esta cadastrado? Digite 'sim' ou 'não'" );  // consulta do imovel para cntrato
        Imovel imovel = null;
        r = tec.nextLine();
        if (r.equalsIgnoreCase("sim")) {
            System.out.println("Digite o ID do imóvel cadastrado:");
            int id_imovel = tec.nextInt();
            imovel = i.findById(id_imovel);
            
            if(imovel != null) {
                System.out.println("\nImóvel encontrado: "+ imovel.getDescricao()); 
                corretor = imovel.getCorretor();
                } else {
                    System.out.println("\nImóvel não encontrado. Deseja cadastrá-lo? Para cadastrar o imóvel, encerre esta seção e cadastre o imóvel");                    
                }
            }
            
            System.out.println("\nInsira a data de início do contrato:");  // dados de entrada para realizar o contrato
            tec.nextLine();
            String dataInicio = tec.nextLine();
            System.out.println("Digite o prazo final do contrato: ");
            String dataFinal = tec.nextLine();
            System.out.println("Informe a Comissão do Contrato em casas decimais:");
            double comissao = tec.nextDouble();

            Contrato contrato = new Contrato(cliente, imovel, corretor, dataInicio, dataFinal, comissao);
            cont.create(contrato); // criacao do contrato

            System.out.println("\nContrato criado com sucesso!\n");
        
        
        // opção 5 de Listar imóveis

        } else if (res == 5) {
            System.out.println("\nLista de imóveis: \n");   
            ArrayList<Imovel> lista = i.getAll();

            if (lista.isEmpty()) {
                System.out.println("Nenhum imóvel cadastrado.");  
            } else {
                for (Imovel imovel : lista) {
                    System.out.println(imovel);
                }
            }

            System.out.println("\nDeseja fazer alguma alteração? Digite 'sim' ou 'não'");  // manipulacao dos imoveis
            String r = tec.nextLine();

            if (r.equalsIgnoreCase("sim")) {
                System.out.println("\nEscolha a opção que deseja realizar:");
                System.out.println("1. Editar cadastro");
                System.out.println("2. Deletar cadastro");
                res = tec.nextInt();
                tec.nextLine(); 
                System.out.println("Digite o ID do imóvel");
                int id_imovel = tec.nextInt();
                tec.nextLine(); 
                Imovel imovel = i.findById(id_imovel); // consulta pelo id

                if (imovel == null) {
                    System.out.println("\nImóvel não encontrado.\n");

                } else if (res == 1) {  // Editar cadastro
                    System.out.println("\nImóvel encontrado: " + imovel.getDescricao());
                    System.out.println("\nDigite os novos dados para atualizar o imóvel:");
                    System.out.println("Digite o novo endereço do imóvel:");
                    String endereco = tec.nextLine();
                    System.out.println("Digite o novo tipo de imóvel:");
                    String tipo = tec.nextLine();
                    System.out.println("Digite uma nova descrição do imóvel:");
                    String descricao = tec.nextLine();
                    System.out.println("Digite a nova área do imóvel:");
                    double area = tec.nextDouble();
                    System.out.println("Digite o novo valor de venda do imóvel:");
                    double valorVenda = tec.nextDouble();
                    System.out.println("Digite o novo valor de aluguel do imóvel:");
                    double valorAluguel = tec.nextDouble();
                    System.out.println("Digite o novo ano de construção do imóvel:");
                    int anoConstrucao = tec.nextInt();
                    tec.nextLine(); 
                    // Atualizar as informações do imóvel
                    imovel.setEndereco(endereco);
                    imovel.setTipo(tipo);
                    imovel.setDescricao(descricao);
                    imovel.setArea(area);
                    imovel.setValorVenda(valorVenda);
                    imovel.setValorAluguel(valorAluguel);
                    imovel.setAnoConstrucao(anoConstrucao);
        
                    i.update(imovel);
                    System.out.println("\nImóvel atualizado com sucesso!\n");
                
                } else if (res == 2) { // Deletar cadastro
                    i.delete(imovel);
                    System.out.println("\nImóvel deletado com sucesso!\n");
                }
            }
        

        // opção 6 de listar clientes
        } else if(res == 6) {
            System.out.println("\nLista de clientes:\n");
            ArrayList<Cliente> lista = c.getAll();
            
            if (lista.isEmpty()) {
                System.out.println("\nNenhum cliente cadastrado.\n");
            } else {
                for (Cliente cliente : lista) {
                    System.out.println(cliente);
                }
            }
            
            System.out.println("\nDeseja fazer alguma alteração? Digite 'sim' ou 'não'"); // manipulacao de clientes
            String r = tec.nextLine();     
            if (r.equalsIgnoreCase("sim")) {
                System.out.println("\nEscolha a opção que deseja realizar:");
                System.out.println("1. Editar cadastro");                                  
                System.out.println("2. Deletar cadastro");
                res = tec.nextInt();
                tec.nextLine(); 
                System.out.println("Digite o ID do cliente");
                int id_cliente = tec.nextInt();
                tec.nextLine(); 
                Cliente cliente = c.findById(id_cliente); // consulta pelo id
                if (cliente == null) {
                    System.out.println("Cliente não encontrado.");
                
                } else if (res == 1) {  // Editar cadastro
                    System.out.println("\nCliente encontrado: " + cliente.getNome());
                    System.out.println("\nDigite os novos dados para atualizar o cliente:");
                    System.out.println("Digite o nome:");
                    String nome_cliente = tec.nextLine();
                    System.out.println("Digite o email:");
                    String email_cliente = tec.nextLine();
                    System.out.println("Digite o telefone:");
                    String tel = tec.nextLine();
                    System.out.println("Digite a data de nascimento:");
                    String data_nasc = tec.nextLine();
                    System.out.println("Digite o endereço:");
                    String endereco = tec.nextLine();
                    System.out.println("Digite o cpf:");
                    String cpf = tec.nextLine();
                
                    // Atualizar as informações do cliente
                    cliente.setNome(nome_cliente);
                    cliente.setDataNasc(data_nasc);
                    cliente.setEmail(email_cliente);
                    cliente.setTel(tel);
                    cliente.setEndereco(endereco);
                    cliente.setCpf(cpf);

                    c.update(cliente);
                    System.out.println("\nCliente atualizado com sucesso!\n");
                
                } else if (res == 2) { // Deletar cadastro  
                    c.delete(cliente);
                    System.out.println("\ncliente deletado com sucesso!\n");
                }
            }
        
        
        // opção 7 de listar corretores
        } else if(res == 7) {
            System.out.println("\nLista de corretores:\n");
            ArrayList<Corretor> lista = cor.getAll();              
            for (Corretor corretor: lista) {
                System.out.println(corretor);
            }


        // opção 8 de listar contratos
        } else if(res == 8) {
            System.out.println("\nLista de contratos:\n");
            ArrayList<Contrato> listaContratos = cont.getAll();
            
            if (listaContratos.isEmpty()) {
                System.out.println("\nNenhum contrato realizado.\n");
            } else {
                for (Contrato contrato : listaContratos) {
                    System.out.println(contrato);
                }
            
            System.out.println("\nDeseja fazer alguma alteração? Digite 'sim' ou 'não'"); // manipulacao de contratos
            String r = tec.nextLine();     
            if (r.equalsIgnoreCase("sim")) {
                System.out.println("\nEscolha a opção que deseja realizar:");
                System.out.println("1. Editar contrato");                                   
                System.out.println("2. Deletar contrato");
                res = tec.nextInt();
                tec.nextLine(); 
                System.out.println("Digite o ID do contrato");
                int id_contrato = tec.nextInt();
                tec.nextLine(); 
                Contrato contrato = cont.findById(id_contrato);  // consulta pelo id
                if (contrato == null) {
                    System.out.println("Contrato não encontrado.");
                
                } else if (res == 1) {  // Editar contrato
                    System.out.println("\nContrato encontrado em nome de " + contrato.getCliente().getNome());
                    System.out.println("\nDigite os novos dados para atualizar o contrato:");
                    System.out.println("Digite a data de início:");
                    String data_inicio = tec.nextLine();
                    System.out.println("Digite o prazo final do contrato:");
                    String data_fim = tec.nextLine();
                    System.out.println("Digite o valor da comissão do contrato em casas decimais:");
                    double comissao = tec.nextDouble();
                
                    // Atualizar as informações do contrato
                    contrato.setDataInicio(data_inicio);
                    contrato.setDataFim(data_fim);
                    contrato.setComissao(comissao);

                    cont.update(contrato);
                    System.out.println("\nContrato atualizado com sucesso!\n");
                
                } else if (res == 2) { // Deletar contrato  
                    cont.delete(contrato);
                    System.out.println("\nContrato deletado com sucesso!\n");
                }
            }
        }


            // opção 9  de locacao
        } else if(res == 9) {
            System.out.println("O cliente que deseja fazer a locação já possui cadastro? Digite 'sim' ou 'não: ");
            Cliente cliente = null;
            Imovel imovel = null;
            String r = tec.nextLine();

            if (r.equalsIgnoreCase("sim")) {
                System.out.println("Digite o CPF do cliente cadastrado:");
                String cpf = tec.nextLine();
                cliente = c.findByCpfCliente(cpf);  // Atribuindo o cliente encontrado à variável cliente
                    
                if (cliente != null) {
                    System.out.println("Cliente encontrado: " + cliente.getNome());
                    System.out.println("Digite o ID do imóvel que será locado:");
                    int id_imovel = tec.nextInt();
                    tec.nextLine(); 
                    imovel = i.findById(id_imovel); 

                    if (imovel != null) {
                        System.out.println("Imóvel encontrado! " + imovel.getDescricao());
                        System.out.println("Digite a data de início da locação:");
                        String dataInicio = tec.nextLine();
                        System.out.println("Digite a data de fim da locação:");
                        String dataFim = tec.nextLine();

                        Locacao locacao = new Locacao(cliente, imovel, dataInicio, dataFim);
                        loc.create(locacao);

                        System.out.println("\nlocação gerada com sucesso!\n");

                    } else {
                            System.out.println("\nPara realizar a locação, é necessário o cliente estar cadastrado:\n");
                        }
                    }
                }
            }
    
    
    
        } while (res!=10);

    }
}
         

