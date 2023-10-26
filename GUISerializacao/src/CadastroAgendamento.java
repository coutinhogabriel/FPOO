import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;

public class CadastroAgendamento extends JPanel{
    //atributos
    private JTextField inputData;
    private JTextField inputHora;
    private JComboBox<String> comboUsuarios;
    private JTextField inputDescricao;
    private DefaultTableModel tableModel;
    private JTable table;
    private List<Agenda> agendamentos = new ArrayList<>();
    private int linhaSelecionada = -1;

    public CadastroAgendamento() {
        File arquivo = new File("dados.txt");
        List<Usuario> usuarios = new ArrayList<>();
        if (arquivo.exists()) {
            usuarios = Serializacao.deserializar("dados.txt");
        }
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Data");
        tableModel.addColumn("Hora");
        tableModel.addColumn("Usuário");
        tableModel.addColumn("Descrição");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        inputData = new JTextField(8);
        inputHora = new JTextField(8);
        //Add meu comboBox
        comboUsuarios = new JComboBox<>();
        for (Usuario usuario : usuarios) {
            comboUsuarios.addItem(usuario.getNome());
        }
        inputDescricao = new JTextField(20);
        JButton cadastrarAgendaButton = new JButton("Cadastrar");
        JButton atualizarAgendaButton = new JButton("Atualizar");
        JButton apagarAgendaButton = new JButton("Apagar");
        JButton salvarAgendaButton = new JButton("Salvar");

        JPanel inputAgendaPanel = new JPanel();
        inputAgendaPanel.add(new JLabel("Data:"));
        inputAgendaPanel.add(inputData);
        inputAgendaPanel.add(new JLabel("Hora:"));
        inputAgendaPanel.add(inputHora);
        inputAgendaPanel.add(new JLabel("Usuário:"));
        inputAgendaPanel.add(comboUsuarios);
        inputAgendaPanel.add(new JLabel("Descricao:"));
        inputAgendaPanel.add(inputDescricao);
        inputAgendaPanel.add(cadastrarAgendaButton);
        inputAgendaPanel.add(atualizarAgendaButton);
        inputAgendaPanel.add(apagarAgendaButton);
        inputAgendaPanel.add(salvarAgendaButton);

        setLayout(new BorderLayout());
        add(inputAgendaPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);   

        //Tratamentos de eventos
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                linhaSelecionada = table.rowAtPoint(evt.getPoint());
            }
        });
        OperacoesAgendamento operacoes = new OperacoesAgendamento(agendamentos, tableModel, table);

        cadastrarAgendaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operacoes.cadastrarAgendamento(inputData.getText(), inputHora.getText(),(String)comboUsuarios.getSelectedItem(),inputDescricao.getText());
                inputData.setText("");
                inputHora.setText("");
                inputDescricao.setText("");
            }
        });

        atualizarAgendaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operacoes.atualizarAgendamento(linhaSelecionada, inputData.getText(), inputHora.getText(),(String)comboUsuarios.getSelectedItem(),inputDescricao.getText());
            }
        });

        apagarAgendaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operacoes.apagarAgendamento(linhaSelecionada);
            }
        });

    //    salvarButton.addActionListener(new ActionListener() {
    //         @Override
    //         public void actionPerformed(ActionEvent e) {
    //             operacoes.salvarUsuarios();
    //         }
    //     });
    }
    
}
