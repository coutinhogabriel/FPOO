package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import Controller.ClientesControl;
import Controller.ClientesDAO;
import Model.Clientes;

public class JanelaClientes extends JPanel {
    private JButton cadastrar, apagar, editar;
    private JTextField clintNome, clintContato, clintCPF;
    private List<Clientes> clientes;
    private JTable table;
    private DefaultTableModel tableModel;
    private int linhaSelecionada = -1;

    public JanelaClientes() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Clientes"));
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
        
        inputPanel.add(new JLabel("Nome"));
        clintNome = new JTextField(20);
        inputPanel.add(clintNome);

        inputPanel.add(new JLabel("Contato"));
        clintContato = new JTextField(20);
        inputPanel.add(clintContato);

        inputPanel.add(new JLabel("CPF"));
        clintCPF = new JTextField(20);
        inputPanel.add(clintCPF);

        add(inputPanel);

        JPanel botoes = new JPanel();
        botoes.add(cadastrar = new JButton("Cadastrar"));
        botoes.add(editar = new JButton("Editar"));
        botoes.add(apagar = new JButton("Apagar"));
        add(botoes);

        JScrollPane jSPane = new JScrollPane();
        add(jSPane);
        tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Contato", "CPF" });
        table = new JTable(tableModel);
        jSPane.setViewportView(table);

        new ClientesDAO().criaTabela();
        atualizar();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                linhaSelecionada = table.rowAtPoint(evt.getPoint());
                if (linhaSelecionada != -1) {
                    clintNome.setText((String) table.getValueAt(linhaSelecionada, 0));
                    clintContato.setText((String) table.getValueAt(linhaSelecionada, 1));
                    clintCPF.setText((String) table.getValueAt(linhaSelecionada, 2));
                }
            }
        });

        ClientesControl operacoes = new ClientesControl(clientes, tableModel, table);

        cadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operacoes.cadastrar(clintNome.getText(), clintContato.getText(), clintCPF.getText());
                clintNome.setText("");
                clintContato.setText("");
                clintCPF.setText("");
            }
        });

        editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operacoes.atualizar(clintNome.getText(), clintContato.getText(), clintCPF.getText());
                clintNome.setText("");
                clintContato.setText("");
                clintCPF.setText("");
            }
        });

        apagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operacoes.apagar(clintCPF.getText());
                clintNome.setText("");
                clintContato.setText("");
                clintCPF.setText("");
            }
        });
    }

    private void atualizar() {
        tableModel.setRowCount(0);
        clientes = new ClientesDAO().listarTodos();
        for (Clientes cliente : clientes) {
            tableModel.addRow(new Object[] { cliente.getNome(), cliente.getContato(), cliente.getCPF() });
        }
    }
}
