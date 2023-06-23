package CaixaEletronico;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoClienteConta {
    private static final String SELECT_CONTA_BY_CPF = "SELECT * FROM conta WHERE cpf = ?";
    private static final String SELECT_CLIENTE_BY_CPF = "SELECT * FROM cliente WHERE cpf = ?";
    private static final String INSERT_CLIENTE = "INSERT INTO cliente (cpf, nome, senha) VALUES (?, ?, ?)";
    private static final String INSERT_CONTA = "INSERT INTO conta (numeroConta, tipoConta, saldo, cpf) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_SALDO = "UPDATE conta SET saldo = ? WHERE cpf = ?";
    private static final String DELETE_CLIENTE_BY_CPF = "DELETE FROM cliente WHERE cpf = ?";
    private static final String DELETE_CONTA_BY_CPF = "DELETE FROM conta WHERE cpf = ?";
   
    public static Cliente getClienteByCpf(int cpf) {
        Connection conexao = FabricaConexao.getConexao();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cliente cliente = null;

        try {
            ps = conexao.prepareStatement(SELECT_CLIENTE_BY_CPF);
            ps.setInt(1, cpf);
            rs = ps.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");

                cliente = new Cliente(cpf, nome, senha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            FabricaConexao.close(conexao, ps, rs);
        }

        return cliente;
    }

    public static void criarCliente(Cliente cliente) {
        Connection conexao = FabricaConexao.getConexao();
        PreparedStatement psCliente = null;

        try {
            psCliente = conexao.prepareStatement(INSERT_CLIENTE);
            psCliente.setInt(1, cliente.getCpf());
            psCliente.setString(2, cliente.getNome());
            psCliente.setString(3, cliente.getSenha());
            psCliente.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            FabricaConexao.close(conexao, psCliente, null);
        }
    }

    public static void criarConta(Conta conta, int cpf) {
        Connection conexao = FabricaConexao.getConexao();
        PreparedStatement psConta = null;

        try {
            psConta = conexao.prepareStatement(INSERT_CONTA);
            psConta.setInt(1, conta.getNumeroConta());
            psConta.setString(2, conta.getTipoConta());
            psConta.setDouble(3, conta.getSaldo());
            psConta.setInt(4, cpf);
            psConta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            FabricaConexao.close(conexao, psConta, null);
        }
    }
    public static Conta getContaByCpf(int cpf) {
        Connection conexao = FabricaConexao.getConexao();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conta conta = null;

        try {
            ps = conexao.prepareStatement(SELECT_CONTA_BY_CPF);
            ps.setInt(1, cpf);
            rs = ps.executeQuery();

            if (rs.next()) {
                int numeroConta = rs.getInt("numeroConta");
                String tipoConta = rs.getString("tipoConta");
                double saldo = rs.getDouble("saldo");

                conta = new Conta(numeroConta, tipoConta, saldo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            FabricaConexao.close(conexao, ps, rs);
        }

        return conta;
    }
    public static void depositar(int cpf, double valor) {
        Conta conta = getContaByCpf(cpf);
        if (conta != null) {
            double novoSaldo = conta.getSaldo() + valor;

            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = null;

            try {
                ps = conexao.prepareStatement(UPDATE_SALDO);
                ps.setDouble(1, novoSaldo);
                ps.setInt(2, cpf);

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                FabricaConexao.close(conexao, ps, null);
            }
        }
    }

    public static void sacar(int cpf, double valor) {
        Conta conta = getContaByCpf(cpf);
        if (conta != null) {
            double novoSaldo = conta.getSaldo() - valor;
            if (novoSaldo >= 0) {
                Connection conexao = FabricaConexao.getConexao();
                PreparedStatement ps = null;

                try {
                    ps = conexao.prepareStatement(UPDATE_SALDO);
                    ps.setDouble(1, novoSaldo);
                    ps.setInt(2, cpf);

                    ps.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    FabricaConexao.close(conexao, ps, null);
                }
            } else {
                JOptionPane.showMessageDialog(null,"Saldo insuficiente para saque.");
            }
        }
    }

    public static void transferir(int cpfOrigem, int cpfDestino, double valor) {
        Conta contaOrigem = getContaByCpf(cpfOrigem);
        Conta contaDestino = getContaByCpf(cpfDestino);
        if (contaOrigem != null && contaDestino != null) {
            double novoSaldoOrigem = contaOrigem.getSaldo() - valor;
            double novoSaldoDestino = contaDestino.getSaldo() + valor;
            if (novoSaldoOrigem >= 0) {
                Connection conexao = FabricaConexao.getConexao();
                PreparedStatement psOrigem = null;
                PreparedStatement psDestino = null;

                try {
                    conexao.setAutoCommit(false);

                    psOrigem = conexao.prepareStatement(UPDATE_SALDO);
                    psOrigem.setDouble(1, novoSaldoOrigem);
                    psOrigem.setInt(2, cpfOrigem);
                    psOrigem.executeUpdate();

                    psDestino = conexao.prepareStatement(UPDATE_SALDO);
                    psDestino.setDouble(1, novoSaldoDestino);
                    psDestino.setInt(2, cpfDestino);
                    psDestino.executeUpdate();

                    conexao.commit();
                } catch (SQLException e) {
                    try {
                        conexao.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    e.printStackTrace();
                } finally {
                    try {
                        conexao.setAutoCommit(true);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    FabricaConexao.close(conexao, psOrigem, null);
                    FabricaConexao.close(null, psDestino, null);
                }
            } else {
                JOptionPane.showMessageDialog(null,"Saldo insuficiente para transferência.");
            }
        }
    }

    public static void excluirClienteConta(int cpf) {
        Connection conexao = FabricaConexao.getConexao();
        PreparedStatement psCliente = null;
        PreparedStatement psConta = null;

        try {
            conexao.setAutoCommit(false);

            psCliente = conexao.prepareStatement(DELETE_CLIENTE_BY_CPF);
            psCliente.setInt(1, cpf);
            psCliente.executeUpdate();

            psConta = conexao.prepareStatement(DELETE_CONTA_BY_CPF);
            psConta.setInt(1, cpf);
            psConta.executeUpdate();

            conexao.commit();
        } catch (SQLException e) {
            try {
                conexao.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conexao.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            FabricaConexao.close(conexao, psCliente, null);
            FabricaConexao.close(null, psConta, null);
        }
    }
}


