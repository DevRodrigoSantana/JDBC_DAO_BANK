package CaixaEletronico;
import javax.swing.JOptionPane;

	public class Sistema {
		
		static int globalsalve = 0;
		
	    public static void main(String[] args) {
	        boolean loggedIn = false;

	        while (!loggedIn) {
	            String opcaoInput = JOptionPane.showInputDialog(null,
	                    "Escolha uma opção:\n" +
	                            "1. Login\n" +
	                            "2. Criar conta\n" +
	                            "3. Sair");

	            int opcao = Integer.parseInt(opcaoInput);

	            switch (opcao) {
	                case 1:
	                    loggedIn = login();
	                    break;
	                case 2:
	                    cadastrarConta();
	                    break;
	                case 3:
	                    JOptionPane.showMessageDialog(null, "Saindo do sistema...");
	                    System.exit(0);
	                    break;
	                default:
	                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
	                    break;
	            }
	        }

	    
	        JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
	        realizarAcoes();
	    }
	    private static void extrato() {
	    	
	    	
	        
	        String senhaInput = JOptionPane.showInputDialog(null, "Digite a senha do cliente:");
	        String senha = senhaInput;
	        
	        Cliente cliente = DaoClienteConta.getClienteByCpf(globalsalve);
	        Conta conta = DaoClienteConta.getContaByCpf(globalsalve);
	        
	        if(!cliente.getSenha().equals(senha)){
	        	JOptionPane.showMessageDialog(null, "senha inválida.");
	        }
	        JOptionPane.showMessageDialog(null, "EXTRATO\n"+
	        									"Nome : " +cliente.getNome()+
	        									"\nCpf : " + cliente.getCpf()+
	        									"\nSaldoTotal : " +conta.getSaldo());
	        
	    }

	    private static boolean login() {
	        String cpfInput = JOptionPane.showInputDialog(null, "Digite o CPF do cliente:");
	        int cpf = Integer.parseInt(cpfInput);

	        String senhaInput = JOptionPane.showInputDialog(null, "Digite a senha do cliente:");
	        String senha = senhaInput;

	        Cliente cliente = DaoClienteConta.getClienteByCpf(cpf);
	        Conta conta = DaoClienteConta.getContaByCpf(cpf);

	        if (cliente == null || conta == null || !cliente.getSenha().equals(senha)) {
	            JOptionPane.showMessageDialog(null, "CPF ou senha inválidos.");
	            return false;
	        }

	        JOptionPane.showMessageDialog(null, "Bem-vindo, " + cliente.getNome() + "!");
	        JOptionPane.showMessageDialog(null, "Conta: " + conta.getNumeroConta());
	        JOptionPane.showMessageDialog(null, "Saldo: " + conta.getSaldo());
	        globalsalve = cpf;
	        return true;
	    }

	    private static void cadastrarConta() {
	        String cpfInput = JOptionPane.showInputDialog(null, "Digite o CPF do cliente:");
	        int cpf = Integer.parseInt(cpfInput);

	        String nome = JOptionPane.showInputDialog(null, "Digite o nome do cliente:");
	        String senha = JOptionPane.showInputDialog(null, "Digite a senha do cliente:");

	      
	        Cliente cliente = new Cliente(cpf, nome, senha);

	
	        Conta conta = new Conta(1, "Conta Corrente", 0.0);

	
	        DaoClienteConta.criarCliente(cliente);
	        DaoClienteConta.criarConta(conta, cpf);


	        JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
	    }

	    private static void realizarAcoes() {
	        while (true) {
	            String opcaoInput = JOptionPane.showInputDialog(null,
	                    "Escolha uma opção:\n" +
	                            "1. Depositar\n" +
	                            "2. Sacar\n" +
	                            "3. Transferir\n" +
	                            "4. Extrato\n"+
	                            "5. Sair");

	            int opcao = Integer.parseInt(opcaoInput);

	            switch (opcao) {
	                case 1:
	                    depositar();
	                    break;
	                case 2:
	                    sacar();
	                    break;
	                case 3:
	                    transferir();
	                    break;
	                    
	                case 4:
	                	extrato();
	                	break;
	                case 5:
	                    JOptionPane.showMessageDialog(null, "Saindo do sistema...");
	                    System.exit(0);
	                    break;
	                default:
	                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
	                    break;
	            }
	        }
	    }

	    private static void depositar() {
	        String cpfInput = JOptionPane.showInputDialog(null, "Digite o CPF do cliente:");
	        int cpf = Integer.parseInt(cpfInput);

	        String valorDepositoInput = JOptionPane.showInputDialog(null, "Digite o valor a depositar:");
	        double valorDeposito = Double.parseDouble(valorDepositoInput);

	        DaoClienteConta.depositar(cpf, valorDeposito);
	        JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso.");
	    }

	    private static void sacar() {
	        String cpfInput = JOptionPane.showInputDialog(null, "Digite o CPF do cliente:");
	        int cpf = Integer.parseInt(cpfInput);

	        String valorSaqueInput = JOptionPane.showInputDialog(null, "Digite o valor a sacar:");
	        double valorSaque = Double.parseDouble(valorSaqueInput);

	        DaoClienteConta.sacar(cpf, valorSaque);
	        JOptionPane.showMessageDialog(null, "Saque realizado com sucesso.");
	    }

	    private static void transferir() {
	        String cpfOrigemInput = JOptionPane.showInputDialog(null, "Digite o CPF do cliente de origem:");
	        int cpfOrigem = Integer.parseInt(cpfOrigemInput);

	        String cpfDestinoInput = JOptionPane.showInputDialog(null, "Digite o CPF do cliente de destino:");
	        int cpfDestino = Integer.parseInt(cpfDestinoInput);

	        String valorTransferenciaInput = JOptionPane.showInputDialog(null, "Digite o valor a transferir:");
	        double valorTransferencia = Double.parseDouble(valorTransferenciaInput);

	        DaoClienteConta.transferir(cpfOrigem, cpfDestino, valorTransferencia);
	        JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso.");
	    }
	}



