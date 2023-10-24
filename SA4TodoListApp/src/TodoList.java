import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class TodoList extends JFrame {
    private JPanel mainPanel;
    private JTextField taskInputField;
    private JButton addButton;
    private JList<String> taskList;
    private DefaultListModel<String> listModel;
    private JButton deleteButton;
    private JButton markDoneButton;
    private JComboBox<String> filterComboBox;
    private JButton clearCompletedButton;
    private List<Task> tasks;
    private JScrollBar scrollBar;
    private JLabel label;
    private JPanel buttonPanel;

    // Construtor da classe TodoList
    public TodoList() {
        // Configura a janela principal
        super("To-Do List App");
        // Impede o fechamento padrão da janela
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // Define o tamanho da janela
        this.setSize(600, 300);

        // Inicialização do painel principal
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Inicialização da lista de tarefas e do modelo da lista
        tasks = new ArrayList<>();
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);

        // Inicialização dos campos de entrada, botões e ComboBox
        taskInputField = new JTextField();
        addButton = new JButton("Adicionar");
        deleteButton = new JButton("Excluir");
        markDoneButton = new JButton("Concluir");
        filterComboBox = new JComboBox<>(new String[] { "Todas", "Ativas", "Concluídas" });
        clearCompletedButton = new JButton("Limpar Concluídas");
        scrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
        label = new JLabel("Tamanho: 100");

        // Configuração do painel de entrada
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(taskInputField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        // Configuração do painel de botões
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(deleteButton);
        buttonPanel.add(markDoneButton);
        buttonPanel.add(filterComboBox);
        buttonPanel.add(clearCompletedButton);
        buttonPanel.add(scrollBar);
        buttonPanel.add(label);

        // Adiciona os componentes ao painel principal
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(taskList), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adiciona o painel principal à janela
        this.add(mainPanel);

        // Adiciona listeners aos botões
        addButton.addActionListener(e -> {

            addTask();

        });

        deleteButton.addActionListener(e -> {
            // Quando o botão "Excluir" é pressionado
            deleteTask();

        });

        markDoneButton.addActionListener(e -> {
            // Quando o botão "Concluir" é pressionado
            markTaskDone();

        });

        filterComboBox.addItemListener(e -> {
            // Quando a seleção do ComboBox é alterada
            filterTasks();

        });

        clearCompletedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Quando o botão "Limpar Concluídas" é pressionado
                clearCompletedTasks();
            }
        });

        // Adiciona um KeyListener para a lista de tarefas para excluir com a tecla
        // "DEL"
        taskInputField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    // Quando a tecla "Entert" é pressionada na lista

                    // Adiciona a tarefa na lista e atualiza a interface
                    addTask();
                }
            }

        });
        taskList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_DELETE) {
                    // Quando a tecla "DEL" é pressionada na lista

                    // Remove a tarefa da lista e atualiza a interface
                    deleteTask();
                }

            }

        });

        // Adiciona um WindowListener para confirmar o fechamento da aplicação
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Quando a janela está prestes a ser fechada
                fecharJanela();
            }
        });

        // Adiciona um MouseListener para detectar cliques duplos na lista de tarefas
        taskList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {
                    editTask();
                }
            }
        });

        scrollBar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                TamanhoFrame();
            }

        });

        run();

    }

    // Função para adicionar uma nova tarefa à lista
    private void addTask() {
        // Obtém a descrição da tarefa do campo de entrada
        String taskDescription = taskInputField.getText().trim();
        if (!taskDescription.isEmpty()) {
            // Cria uma nova tarefa, adiciona à lista e atualiza a interface
            Task newTask = new Task(taskDescription);
            tasks.add(newTask);
            updateTaskList();
            // Limpa o campo de entrada
            taskInputField.setText("");
        }
    }

    // Função para excluir a tarefa selecionada da lista
    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < tasks.size()) {
            // Exibe uma caixa de diálogo de confirmação
            int option = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "Confirmação",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                // Remove a tarefa da lista e atualiza a interface
                tasks.remove(selectedIndex);
                updateTaskList();
            }
        }
    }

    // Função para marcar a tarefa selecionada como concluída
    private void markTaskDone() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < tasks.size()) {
            // Marca a tarefa como concluída e atualiza a interface
            Task task = tasks.get(selectedIndex);
            task.setDone(true);
            updateTaskList();
        }
    }

    // Função para filtrar as tarefas com base na seleção do ComboBox
    private void filterTasks() {
        // Obtém a seleção do ComboBox
        String filter = (String) filterComboBox.getSelectedItem();
        // Limpa o modelo da lista
        listModel.clear();
        // Adiciona as tarefas filtradas ao modelo
        for (Task task : tasks) {
            if (filter.equals("Todas") || (filter.equals("Ativas") && !task.isDone())
                    || (filter.equals("Concluídas") && task.isDone())) {
                listModel.addElement(task.getDescription());
            }
        }
    }

    // Função para limpar todas as tarefas concluídas da lista
    private void clearCompletedTasks() {
        // Cria uma lista de tarefas concluídas
        List<Task> completedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isDone()) {
                completedTasks.add(task);
            }
        }
        // Remove as tarefas concluídas da lista e atualiza a interface
        tasks.removeAll(completedTasks);
        updateTaskList();
    }

    // Função para atualizar a lista de tarefas na interface gráfica
    private void updateTaskList() {
        // Limpa o modelo da lista
        listModel.clear();
        // Adiciona as tarefas atualizadas ao modelo
        for (Task task : tasks) {
            listModel.addElement(task.getDescription() + (task.isDone() ? " (Concluída)" : ""));
        }
    }

    private void fecharJanela() {
        int option = JOptionPane.showConfirmDialog(null, "Deseja realmente fechar a aplicação?", "Confirmação",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            // Fecha a aplicação
            System.exit(0);
        }
    }

    // Função para editar a tarefa selecionada
    private void editTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < tasks.size()) {
            // Obtém a descrição atual da tarefa
            String currentDescription = tasks.get(selectedIndex).getDescription();

            // Pede ao usuário para editar a descrição
            String editedDescription = (String) JOptionPane.showInputDialog(null, "Editar Tarefa", "Edição de Tarefa",
                    JOptionPane.PLAIN_MESSAGE, null, null, currentDescription);

            // Atualiza a descrição se o usuário não cancelou
            if (editedDescription != null && !editedDescription.isEmpty()) {
                tasks.get(selectedIndex).setDescription(editedDescription);
                updateTaskList();
            }
        }
    }

    private void TamanhoFrame() {
        int valorScrollBar = scrollBar.getValue();

        int largura = 600;
        int altura = 300;

        int componentesAlt = 20;
        int componenteslar = 100;

        // Atualiza as dimensões dos botões
        addButton.setPreferredSize(new Dimension(componenteslar + valorScrollBar, componentesAlt + valorScrollBar));
        deleteButton.setPreferredSize(new Dimension(componenteslar + valorScrollBar, componentesAlt + valorScrollBar));
        markDoneButton
                .setPreferredSize(new Dimension(componenteslar + valorScrollBar, componentesAlt + valorScrollBar));
        clearCompletedButton
                .setPreferredSize(new Dimension(componenteslar + valorScrollBar, componentesAlt + valorScrollBar));
        filterComboBox
                .setPreferredSize(new Dimension(componenteslar + valorScrollBar, componentesAlt + valorScrollBar));

        // Atualiza a dimensão da label
        label.setPreferredSize(new Dimension(componenteslar, componentesAlt));
        // Atualiza o tamanho da fonte dos botões
        Font newFont = new Font("Arial", Font.PLAIN, 5 + valorScrollBar); // Altere o tamanho da fonte conforme o ScrollBar
                                                                           

        addButton.setFont(newFont);
        deleteButton.setFont(newFont);
        markDoneButton.setFont(newFont);
        clearCompletedButton.setFont(newFont);
        filterComboBox.setFont(newFont);

        label.setText("Tamanho: " + (valorScrollBar + 100) + "%");

        // Define as novas dimensões para o painel principal
        mainPanel.setPreferredSize(new Dimension(largura + (valorScrollBar * 9), altura + (valorScrollBar * 4)));
        mainPanel.revalidate(); // Revalida o layout para aplicar as alterações
        pack();
    }

    // Função para exibir a janela principal
    public void run() {
        this.setVisible(true);
    }

}